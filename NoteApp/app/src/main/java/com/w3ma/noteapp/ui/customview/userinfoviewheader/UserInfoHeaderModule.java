package com.w3ma.noteapp.ui.customview.userinfoviewheader;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Emanuele on 26/07/2016.
 */
@Module
public class UserInfoHeaderModule {

    private final UserInfoHeaderContract.View view;

    public UserInfoHeaderModule(final UserInfoHeaderContract.View view) {
        this.view = view;
    }

    @Provides
    UserInfoHeaderContract.View provideUserInfoHeaderContractView() {
        return view;
    }

    @Provides
    UserInfoHeaderContract.Presenter provideUserInfoHeaderContractPresenter(final Context context) {
        return new UserInfoHeaderPresenter(context, view);
    }
}
