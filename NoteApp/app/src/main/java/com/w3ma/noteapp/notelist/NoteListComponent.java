package com.w3ma.noteapp.notelist;

import com.w3ma.noteapp.config.ApplicationComponent;
import com.w3ma.noteapp.config.injection.ActivityScope;

import dagger.Component;

/**
 * Created by Emanuele on 20/07/2016.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = NoteListModule.class)
public interface NoteListComponent {

    void inject(NoteListActivity noteListActivity);
}
