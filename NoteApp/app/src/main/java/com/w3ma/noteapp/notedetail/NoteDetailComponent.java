package com.w3ma.noteapp.notedetail;

import com.w3ma.noteapp.config.ApplicationComponent;
import com.w3ma.noteapp.config.injection.ActivityScope;

import dagger.Component;

/**
 * Created by Emanuele on 20/07/2016.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = NoteDetailModule.class)
public interface NoteDetailComponent {

    void inject(NoteDetailActivity noteDetailActivity);

    void inject(NoteDetailFragment noteDetailFragment);
}
