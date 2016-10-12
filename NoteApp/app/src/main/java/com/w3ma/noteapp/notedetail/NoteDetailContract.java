package com.w3ma.noteapp.notedetail;

import com.w3ma.noteapp.mvp.BasePresenter;
import com.w3ma.noteapp.mvp.BaseView;

/**
 * Created by Emanuele on 21/07/2016.
 */
public interface NoteDetailContract {

    interface View extends BaseView<Presenter> {

        void showDetails(NoteDetailViewModel noteDetailViewModel);

        void setTitle(String name);
    }

    interface Presenter extends BasePresenter {

        void setNote(Long noteId);
    }
}
