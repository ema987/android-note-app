package com.w3ma.noteapp.ui.customview.userinfoviewheader;

import com.w3ma.noteapp.config.ApplicationComponent;
import com.w3ma.noteapp.config.injection.ActivityScope;

import dagger.Component;

/**
 * Created by Emanuele on 26/07/2016.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = UserInfoHeaderModule.class)
public interface UserInfoHeaderComponent {

    UserInfoHeaderContract.View provideUserInfoHeaderContractView();

    void inject(UserInfoHeaderView userInfoHeaderView);
}
