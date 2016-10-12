package com.w3ma.noteapp.datasource;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;

import com.w3ma.noteapp.business.util.Constants;
import com.w3ma.noteapp.cache.DeviceCacheManager;
import com.w3ma.noteapp.datasource.local.db.Note;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Emanuele on 29/07/16.
 */
public class NoteRepository implements NoteDataSource {

    private final NoteDataSource noteRemoteDataSource;
    private final NoteDataSource noteLocalDataSource;
    private final SharedPreferences sharedPreferences;
    private final long aHourInMs = 30 * 1000;
    private final DeviceCacheManager deviceCacheManager;
    private List<Note> cachedNoteList;
    private LongSparseArray<Note> cachedNoteSparseArray;

    public NoteRepository(@NonNull final NoteDataSource noteRemoteDataSource,
                          @NonNull final NoteDataSource noteLocalDataSource,
                          @NonNull final SharedPreferences sharedPreferences, @NonNull final DeviceCacheManager deviceCacheManager) {
        this.noteRemoteDataSource = checkNotNull(noteRemoteDataSource);
        this.noteLocalDataSource = checkNotNull(noteLocalDataSource);
        this.sharedPreferences = sharedPreferences;
        this.deviceCacheManager = deviceCacheManager;
    }

    @Override
    public void findAll(@NonNull final LoadNoteListCallbacks callbacks) {
        checkNotNull(callbacks);

        if (isCacheValid(CacheType.NOTE_LIST)) {
            if (cachedNoteList != null) {
                callbacks.onNotesLoaded(new ArrayList<>(cachedNoteList));
            } else {
                // Query the local storage if available. If not, query the network.
                getNotesFromLocalDataSource(callbacks);
            }
        } else {
            // If the cache is dirty we need to fetch new data from the network.
            getNotesFromRemoteDataSource(callbacks);
        }
    }

    private void getNotesFromLocalDataSource(final LoadNoteListCallbacks callbacks) {
        noteLocalDataSource.findAll(new LoadNoteListCallbacks() {

            @Override
            public void onNotesLoaded(final List<Note> noteList) {
                refreshCache(noteList);
                callbacks.onNotesLoaded(new ArrayList<>(noteList));
            }

            @Override
            public void onDataNotAvailable(final String message) {
                getNotesFromRemoteDataSource(callbacks);
            }

            @Override
            public void onStart() {
                callbacks.onStart();
            }
        });
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("not implemented in the app");
    }

    @Override
    public void save(@NonNull final Note note) {
        throw new UnsupportedOperationException("not implemented in the app");
    }

    @Override
    public void find(@NonNull final Long id, @NonNull final LoadNoteCallbacks callbacks) {
        checkNotNull(callbacks);

        // Respond immediately with cache if available and not dirty
        final Note note = getCachedNoteWithId(id);

        if (isCacheValid(CacheType.NOTE_ITEM, id)) {
            if (note != null) {
                callbacks.onNoteLoaded(note);
            } else {
                // Query the local storage if available. If not, query the network.
                getNoteFromLocalDataSource(id, callbacks);
            }
        } else {
            // If the cache is dirty we need to fetch new data from the network.
            getNoteFromRemoteDataSource(id, callbacks);
        }

    }

    private Note getCachedNoteWithId(@NonNull final Long id) {
        if (cachedNoteSparseArray != null) {
            return cachedNoteSparseArray.get(id);
        }
        return null;
    }

    private void getNoteFromLocalDataSource(final Long id, final LoadNoteCallbacks callbacks) {
        noteLocalDataSource.find(id, new LoadNoteCallbacks() {
            @Override
            public void onNoteLoaded(final Note note) {
                if (note.getCreationDate() == null) {
                    onDataNotAvailable(null);
                } else {
                    refreshCache(note);
                    callbacks.onNoteLoaded(note);
                }
            }

            @Override
            public void onDataNotAvailable(final String message) {
                getNoteFromRemoteDataSource(id, callbacks);
            }

            @Override
            public void onStart() {
                callbacks.onStart();
            }
        });
    }

    private void getNoteFromRemoteDataSource(final Long id, final LoadNoteCallbacks callbacks) {
        noteRemoteDataSource.find(id, new LoadNoteCallbacks() {
            @Override
            public void onNoteLoaded(final Note note) {
                refreshCache(note);
                refreshLocalDataSource(note);
                callbacks.onNoteLoaded(note);
            }

            @Override
            public void onDataNotAvailable(final String message) {
                callbacks.onDataNotAvailable(message);
            }

            @Override
            public void onStart() {
                callbacks.onStart();
            }
        });
    }

    private void refreshCache(final List<Note> noteList) {
        if (cachedNoteList == null) {
            cachedNoteList = new LinkedList<>();
        }
        cachedNoteList.clear();
        cachedNoteList.addAll(noteList);
    }

    private void refreshCache(final Note note) {
        if (cachedNoteSparseArray == null) {
            cachedNoteSparseArray = new LongSparseArray<>();
        }
        cachedNoteSparseArray.put(note.getId(), note);
    }

    private void refreshLocalDataSource(final Note note) {
        noteLocalDataSource.save(note);
        deviceCacheManager.setNoteLastServerHitTime(note);
    }

    private void refreshLocalDataSource(final List<Note> noteList) {
        noteLocalDataSource.deleteAll();
        for (final Note note : noteList) {
            noteLocalDataSource.save(note);
        }
        sharedPreferences.edit().putLong(Constants.KEY_SP_LAST_SERVER_HIT_TIME, System.currentTimeMillis()).apply();
    }

    private void getNotesFromRemoteDataSource(@NonNull final LoadNoteListCallbacks callbacks) {
        noteRemoteDataSource.findAll(new LoadNoteListCallbacks() {
            @Override
            public void onNotesLoaded(final List<Note> noteList) {
                refreshCache(noteList);
                refreshLocalDataSource(noteList);
                callbacks.onNotesLoaded(new ArrayList<>(cachedNoteList));
            }

            @Override
            public void onDataNotAvailable(final String message) {
                callbacks.onDataNotAvailable(message);
            }

            @Override
            public void onStart() {
                callbacks.onStart();
            }
        });
    }

    private boolean isCacheValid(final CacheType cacheType) {
        return isCacheValid(cacheType, null);
    }

    private boolean isCacheValid(final CacheType cacheType, final Long id) {
        final long now = System.currentTimeMillis();
        long lastServerHitTime;
        switch (cacheType) {
            case NOTE_LIST:
                lastServerHitTime = sharedPreferences.getLong(Constants.KEY_SP_LAST_SERVER_HIT_TIME, 0);
                break;
            case NOTE_ITEM:
                lastServerHitTime = deviceCacheManager.getNoteLastServerHitTime(id);
                break;
            default:
                return false;
        }
        return (now - aHourInMs) < lastServerHitTime;
    }

    private enum CacheType {NOTE_LIST, NOTE_ITEM}
}
