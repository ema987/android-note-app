package com.w3ma.noteapp.datasource;

import android.support.annotation.NonNull;

import com.w3ma.noteapp.datasource.local.db.Note;

import java.util.List;

/**
 * Created by Emanuele on 29/07/16.
 */
public interface NoteDataSource {

    void findAll(@NonNull LoadNoteListCallbacks callbacks);

    void deleteAll();

    void save(@NonNull Note note);

    void find(@NonNull Long id, @NonNull LoadNoteCallbacks callbacks);

    interface LoadNoteListCallbacks extends BaseCallbacks {

        void onNotesLoaded(final List<Note> noteList);

    }

    interface LoadNoteCallbacks extends BaseCallbacks {

        void onNoteLoaded(final Note note);

    }

    interface BaseCallbacks {

        void onDataNotAvailable(final String message);

        void onStart();
    }
}
