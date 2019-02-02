package com.testtasks.taxiordersservice.ui.base;

import androidx.annotation.StringRes;

public interface ViewI {

    void initView();

    PresenterI getPresenter();

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();
}
