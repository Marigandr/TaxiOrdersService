package com.testtasks.taxiordersservice.di;

import com.testtasks.taxiordersservice.data.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class PresenterModule {

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return new DataManager();
    }
}
