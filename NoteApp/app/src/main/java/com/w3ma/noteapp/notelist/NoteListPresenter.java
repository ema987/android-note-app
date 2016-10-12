package com.w3ma.noteapp.notelist;

import android.content.Context;

import com.w3ma.noteapp.config.ApplicationComponent;
import com.w3ma.noteapp.datasource.NoteDataSource;
import com.w3ma.noteapp.datasource.local.db.Note;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Emanuele on 21/07/2016.
 */
public class NoteListPresenter implements NoteListContract.Presenter {

    private final NoteListContract.View view;
    private final List<Note> noteList;
    @Inject
    protected NoteDataSource noteDataSource;

    public NoteListPresenter(final Context context, final NoteListContract.View view) {
        this.view = view;
        ApplicationComponent.Injector.getComponent(context).inject(this);
        noteList = new LinkedList<>();
    }

    public void onResume() {
        noteDataSource.findAll(new NoteDataSource.LoadNoteListCallbacks() {
            @Override
            public void onNotesLoaded(final List<Note> noteList) {
                NoteListPresenter.this.noteList.clear();
                NoteListPresenter.this.noteList.addAll(noteList);
                view.setNoteList(noteList);
                view.showContent();
            }

            @Override
            public void onDataNotAvailable(final String message) {
                view.showError(message);
                view.showContent();
            }

            @Override
            public void onStart() {
                view.showLoading();
            }
        });
    }

    @Override
    public void onCreate() {
        view.setNoteList(noteList);
    }


    @Override
    public void onListItemClick(final Note note) {
        view.showNoteDetails(note);
    }
}
