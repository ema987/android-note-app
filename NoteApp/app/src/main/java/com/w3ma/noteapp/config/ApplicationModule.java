package com.w3ma.noteapp.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.w3ma.noteapp.business.util.Constants;
import com.w3ma.noteapp.business.util.GsonUtil;
import com.w3ma.noteapp.notedetail.NoteDetailViewModelCreator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Emanuele on 20/07/2016.
 */
@Module
public class ApplicationModule {

    private final Context context;

    ApplicationModule(final Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    public GsonUtil provideGsonUtil() {
        return new GsonUtil();
    }

    @Provides
    public NoteDetailViewModelCreator provideNoteDetailViewModelCreator() {
        return new NoteDetailViewModelCreator();
    }

    @Provides
    public SharedPreferences provideSharedPreferences() {
        return context.getSharedPreferences(Constants.NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }
}
