package com.w3ma.noteapp.datasource.injection;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.w3ma.noteapp.business.util.Constants;
import com.w3ma.noteapp.business.util.GsonUtil;
import com.w3ma.noteapp.cache.DeviceCacheManager;
import com.w3ma.noteapp.datasource.NoteDataSource;
import com.w3ma.noteapp.datasource.NoteRepository;
import com.w3ma.noteapp.datasource.local.LocalRepository;
import com.w3ma.noteapp.datasource.local.db.DaoMaster;
import com.w3ma.noteapp.datasource.local.db.DaoSession;
import com.w3ma.noteapp.datasource.local.db.NoteDao;
import com.w3ma.noteapp.datasource.remote.RemoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Emanuele on 05/08/16.
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public NoteDataSource provideNotesDataSource(final RemoteRepository remoteRepository, final LocalRepository localRepository, final SharedPreferences sharedPreferences, final DeviceCacheManager deviceCacheManager) {
        return new NoteRepository(remoteRepository, localRepository, sharedPreferences, deviceCacheManager);
    }

    @Provides
    @Singleton
    public RemoteRepository provideRemoteRepository(final Context context) {
        return new RemoteRepository(context);
    }

    @Provides
    @Singleton
    public LocalRepository provideLocalRepository(final NoteDao noteDao) {
        return new LocalRepository(noteDao);
    }

    @Provides
    @Singleton
    public NoteDao provideNoteDao(final Context context) {
        final DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, Constants.NAME_APP_DB, null);
        final SQLiteDatabase db = helper.getWritableDatabase();
        final DaoMaster daoMaster = new DaoMaster(db);
        final DaoSession daoSession = daoMaster.newSession();
        return daoSession.getNoteDao();
    }

    @Provides
    @Singleton
    public DeviceCacheManager provideDeviceCacheManager(final Context context, final GsonUtil gsonUtil) {
        return new DeviceCacheManager(context, gsonUtil);
    }
}