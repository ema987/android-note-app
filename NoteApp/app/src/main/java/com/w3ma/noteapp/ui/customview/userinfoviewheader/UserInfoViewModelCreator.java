package com.w3ma.noteapp.ui.customview.userinfoviewheader;

import com.w3ma.noteapp.network.model.user.UserInfo;

import java.text.DateFormat;

/**
 * Created by Emanuele on 26/07/2016.
 */
public class UserInfoViewModelCreator {

    private final DateFormat dateFormat = DateFormat.getDateTimeInstance();

    public UserInfoViewModel createUserInfoViewModel(final UserInfo userInfo) {
        final UserInfoViewModel userInfoViewModel = new UserInfoViewModel();
        userInfoViewModel.setName(userInfo.getName());
        userInfoViewModel.setAvatarLink(userInfo.getAvatarLink());
        userInfoViewModel.setLastLoginDate(dateFormat.format(userInfo.getLastLoginDate()));
        return userInfoViewModel;
    }
}
