package com.testtasks.taxiordersservice.di;

import android.app.Application;
import android.content.Context;

import com.testtasks.taxiordersservice.service.PhotoCacheService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Context provideAppContext() {
        return application;
    }

    @Provides
    @Singleton
    PhotoCacheService providePhotoCacheService() {
        return new PhotoCacheService();
    }
}
