package com.testtasks.taxiordersservice.ui.base;

import com.testtasks.taxiordersservice.Application;
import com.testtasks.taxiordersservice.data.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter implements PresenterI {

    @Inject
    protected DataManager dataManager;
    @Inject
    protected CompositeDisposable compositeDisposable;

    public BasePresenter() {
        Application.getComponent().inject(this);
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
    }
}
