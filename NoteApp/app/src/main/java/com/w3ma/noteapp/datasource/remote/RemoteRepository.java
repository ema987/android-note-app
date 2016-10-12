package com.w3ma.noteapp.datasource.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.w3ma.noteapp.business.exception.UnsupportedOperationException;
import com.w3ma.noteapp.config.ApplicationComponent;
import com.w3ma.noteapp.datasource.NoteDataSource;
import com.w3ma.noteapp.datasource.local.db.Note;
import com.w3ma.noteapp.network.clients.MyServerClient;
import com.w3ma.noteapp.network.clients.MyServerClientMockImpl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Emanuele on 05/08/16.
 */
public class RemoteRepository implements NoteDataSource {

    @Inject
    @Named(MyServerClientMockImpl.NAME)
    protected MyServerClient myServerClient;

    public RemoteRepository(final Context context) {
        ApplicationComponent.Injector.getComponent(context).inject(this);
    }

    @Override
    public void findAll(@NonNull final LoadNoteListCallbacks callbacks) {
        myServerClient.getNoteList(new MyServerClient.MyServerClientListener<List<Note>>() {
            @Override
            public void onSuccess(final List<Note> noteList) {
                callbacks.onNotesLoaded(noteList);
            }

            @Override
            public void onFailure(final String message) {
                callbacks.onDataNotAvailable(message);
            }

            @Override
            public void onStart() {
                callbacks.onStart();
            }
        });
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(@NonNull final Note note) {
        //not used
    }

    @Override
    public void find(@NonNull final Long id, @NonNull final LoadNoteCallbacks callbacks) {
        myServerClient.getNote(id, new MyServerClient.MyServerClientListener<Note>() {
            @Override
            public void onSuccess(final Note note) {
                callbacks.onNoteLoaded(note);
            }

            @Override
            public void onFailure(final String message) {
                callbacks.onDataNotAvailable(message);
            }

            @Override
            public void onStart() {
                callbacks.onStart();
            }
        });
    }

}
