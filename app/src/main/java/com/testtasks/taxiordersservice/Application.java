package com.testtasks.taxiordersservice;

import android.content.Context;

import com.testtasks.taxiordersservice.di.AppComponent;
import com.testtasks.taxiordersservice.di.ApplicationModule;
import com.testtasks.taxiordersservice.di.DaggerAppComponent;

import androidx.multidex.MultiDex;

public class Application extends android.app.Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
