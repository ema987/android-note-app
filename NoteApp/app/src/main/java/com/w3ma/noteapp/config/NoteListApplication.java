package com.w3ma.noteapp.config;

import android.app.Application;

import com.w3ma.noteapp.network.injection.NetworkModule;

/**
 * Created by Emanuele on 20/07/2016.
 */
public class NoteListApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule()).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
