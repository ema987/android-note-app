package com.w3ma.noteapp.notedetail;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Emanuele on 20/07/2016.
 */
@Module
public class NoteDetailModule {

    private final NoteDetailContract.View view;

    public NoteDetailModule(final NoteDetailContract.View view) {
        this.view = view;
    }

    @Provides
    NoteDetailContract.View provideNoteDetailContractView() {
        return view;
    }

    @Provides
    NoteDetailContract.Presenter provideNoteDetailContractPresenter(final Context context) {
        return new NoteDetailPresenter(context, view);
    }
    
}
