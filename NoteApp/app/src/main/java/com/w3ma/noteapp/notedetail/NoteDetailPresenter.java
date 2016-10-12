package com.w3ma.noteapp.notedetail;

import android.content.Context;

import com.w3ma.noteapp.R;
import com.w3ma.noteapp.business.util.GsonUtil;
import com.w3ma.noteapp.config.ApplicationComponent;
import com.w3ma.noteapp.datasource.NoteDataSource;
import com.w3ma.noteapp.datasource.local.db.Note;

import javax.inject.Inject;

/**
 * Created by Emanuele on 20/07/2016.
 */
public class NoteDetailPresenter implements NoteDetailContract.Presenter {

    private final NoteDetailContract.View view;
    private final Context context;
    @Inject
    protected GsonUtil gsonUtil;
    @Inject
    protected NoteDataSource noteDataSource;
    @Inject
    NoteDetailViewModelCreator noteDetailViewModelCreator;
    private Long noteId;

    public NoteDetailPresenter(final Context context, final NoteDetailContract.View view) {
        this.view = view;
        this.context = context;
        ApplicationComponent.Injector.getComponent(context).inject(this);
    }

    @Override
    public void onResume() {
        noteDataSource.find(noteId, new NoteDataSource.LoadNoteCallbacks() {
            @Override
            public void onNoteLoaded(final Note note) {
                view.setTitle(note.getTitle());
                view.showDetails(noteDetailViewModelCreator.createViewModel(note));
                view.showContent();
            }

            @Override
            public void onDataNotAvailable(final String message) {
                view.setTitle(context.getString(R.string.title_note_detail));
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

    }

    @Override
    public void setNote(final Long noteId) {
        this.noteId = noteId;
    }
}
