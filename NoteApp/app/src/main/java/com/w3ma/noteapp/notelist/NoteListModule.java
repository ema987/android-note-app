package com.w3ma.noteapp.notelist;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Emanuele on 20/07/2016.
 */
@Module
public class NoteListModule {

    private final NoteListContract.View view;

    public NoteListModule(final NoteListContract.View view) {
        this.view = view;
    }

    @Provides
    NoteListContract.View provideNoteListContractView() {
        return view;
    }

    @Provides
    NoteListContract.Presenter provideNoteListContractPresenter(final Context context) {
        return new NoteListPresenter(context, view);
    }
}
