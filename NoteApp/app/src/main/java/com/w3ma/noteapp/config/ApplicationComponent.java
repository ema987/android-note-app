package com.w3ma.noteapp.config;

import android.content.Context;

import com.w3ma.noteapp.business.util.GsonUtil;
import com.w3ma.noteapp.datasource.injection.RepositoryModule;
import com.w3ma.noteapp.datasource.remote.RemoteRepository;
import com.w3ma.noteapp.network.injection.NetworkModule;
import com.w3ma.noteapp.notedetail.NoteDetailPresenter;
import com.w3ma.noteapp.notelist.NoteListPresenter;
import com.w3ma.noteapp.ui.customview.userinfoviewheader.UserInfoHeaderPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Emanuele on 20/07/2016.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, RepositoryModule.class})
public interface ApplicationComponent {

    Context provideContext();

    GsonUtil provideGsonUtil();

    void inject(NoteListPresenter noteListPresenter);

    void inject(NoteDetailPresenter noteDetailPresenter);

    void inject(UserInfoHeaderPresenter userInfoHeaderPresenter);

    void inject(RemoteRepository remoteRepository);

    class Injector {
        public static ApplicationComponent getComponent(final Context c) {
            return ((NoteListApplication) c.getApplicationContext()).getApplicationComponent();
        }

        public ApplicationComponent createComponent(final NoteListApplication noteListApplication) {
            final ApplicationComponent build = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(noteListApplication))
                    .networkModule(new NetworkModule())
                    .repositoryModule(new RepositoryModule())
                    .build();
            return build;
        }

    }
}
