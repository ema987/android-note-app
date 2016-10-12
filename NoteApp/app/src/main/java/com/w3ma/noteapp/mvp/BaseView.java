package com.w3ma.noteapp.mvp;

/**
 * Created by Emanuele on 21/07/2016.
 */
public interface BaseView<T extends BasePresenter> {

    void showLoading();

    void showContent();

    void showError(String message);
}
