package com.w3ma.noteapp.notelist;


import com.w3ma.noteapp.datasource.local.db.Note;
import com.w3ma.noteapp.mvp.BasePresenter;
import com.w3ma.noteapp.mvp.BaseView;

import java.util.List;

/**
 * Created by Emanuele on 21/07/2016.
 */
public interface NoteListContract {

    interface View extends BaseView<Presenter> {

        void setNoteList(List<Note> noteList);

        void showNoteDetails(Note note);
    }

    interface Presenter extends BasePresenter {

        void onListItemClick(Note note);
    }
}
