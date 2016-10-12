package com.w3ma.noteapp.datasource.local;

import android.support.annotation.NonNull;

import com.w3ma.noteapp.datasource.NoteDataSource;
import com.w3ma.noteapp.datasource.local.db.Note;
import com.w3ma.noteapp.datasource.local.db.NoteDao;

/**
 * Created by Emanuele on 05/08/16.
 */
public class LocalRepository implements NoteDataSource {

    private final NoteDao noteDao;

    public LocalRepository(final NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public void findAll(@NonNull final LoadNoteListCallbacks callbacks) {
        callbacks.onStart();
        callbacks.onNotesLoaded(noteDao.loadAll());
    }

    @Override
    public void deleteAll() {
        noteDao.deleteAll();
    }

    @Override
    public void save(@NonNull final Note note) {
        noteDao.insertOrReplace(note);
    }

    @Override
    public void find(@NonNull final Long id, @NonNull final LoadNoteCallbacks callbacks) {
        callbacks.onStart();
        callbacks.onNoteLoaded(noteDao.load(id));
    }

}
