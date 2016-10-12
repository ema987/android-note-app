package com.w3ma.noteapp.ui.customview.userinfoviewheader;

import android.content.Context;

import com.w3ma.noteapp.config.ApplicationComponent;
import com.w3ma.noteapp.network.clients.MyServerClient;
import com.w3ma.noteapp.network.clients.MyServerClientMockImpl;
import com.w3ma.noteapp.network.model.user.UserInfo;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Emanuele on 26/07/2016.
 */
public class UserInfoHeaderPresenter implements UserInfoHeaderContract.Presenter {

    final UserInfoHeaderContract.View view;
    @Inject
    @Named(MyServerClientMockImpl.NAME)
    protected MyServerClient myServerClient;

    public UserInfoHeaderPresenter(final Context context, final UserInfoHeaderContract.View view) {
        this.view = view;
        ApplicationComponent.Injector.getComponent(context).inject(this);
    }

    @Override
    public void onResume() {
        myServerClient.getUserInfo(new MyServerClient.MyServerClientListener<UserInfo>() {
            @Override
            public void onSuccess(final UserInfo userInfo) {
                final UserInfoViewModelCreator userInfoViewModelCreator = new UserInfoViewModelCreator();
                view.showUserInfo(userInfoViewModelCreator.createUserInfoViewModel(userInfo));
                view.showContent();
            }

            @Override
            public void onFailure(final String message) {
                view.showError(message);
                view.showContent();
            }

            @Override
            public void onStart() {
                view.showLoading();
            }
        });
    }

    @Override
    public void onCreate() {

    }

}
