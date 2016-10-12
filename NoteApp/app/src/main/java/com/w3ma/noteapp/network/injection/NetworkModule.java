package com.w3ma.noteapp.network.injection;

import android.content.Context;

import com.w3ma.noteapp.business.util.GsonUtil;
import com.w3ma.noteapp.network.clients.MyServerClient;
import com.w3ma.noteapp.network.clients.MyServerClientImpl;
import com.w3ma.noteapp.network.clients.MyServerClientMockImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Emanuele on 22/07/2016.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    @Named(MyServerClientImpl.NAME)
    public MyServerClient provideMyServerClient(final GsonUtil gsonUtil) {
        return new MyServerClientImpl(gsonUtil);
    }

    @Provides
    @Singleton
    @Named(MyServerClientMockImpl.NAME)
    public MyServerClient provideMyServerClientMock(final Context context, final GsonUtil gsonUtil) {
        return new MyServerClientMockImpl(context, gsonUtil);
    }
}
