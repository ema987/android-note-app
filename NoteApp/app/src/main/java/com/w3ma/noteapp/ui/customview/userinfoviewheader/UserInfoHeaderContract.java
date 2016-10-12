package com.w3ma.noteapp.ui.customview.userinfoviewheader;

import com.w3ma.noteapp.mvp.BasePresenter;
import com.w3ma.noteapp.mvp.BaseView;

/**
 * Created by Emanuele on 26/07/2016.
 */
public interface UserInfoHeaderContract {

    interface View extends BaseView<Presenter> {

        void showUserInfo(UserInfoViewModel userInfoViewModel);
    }

    interface Presenter extends BasePresenter {

    }
}
