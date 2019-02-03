package com.testtasks.taxiordersservice.ui.base;

import android.view.View;

import com.bluelinelabs.conductor.Controller;
import com.testtasks.taxiordersservice.ui.MainActivityCallback;
import com.testtasks.taxiordersservice.utils.NetworkUtils;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

public abstract class BaseController extends Controller implements ViewI {
    protected MainActivityCallback mainActivityCallback;
    protected View.OnClickListener refreshButtonListener;

    @Override
    public void showMessage(String message) {
        mainActivityCallback.showSnackbar(message);
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getActivity().getString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        if (getPresenter() != null) {
            getPresenter().onDetach();
        }
    }
}
