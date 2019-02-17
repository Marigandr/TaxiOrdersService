package com.testtasks.taxiordersservice;

import android.content.Context;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
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
        initPicasso();
    }

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initPicasso() {
        Picasso.Builder builder = new Picasso.Builder(getApplicationContext());
        builder.downloader(new OkHttp3Downloader(getApplicationContext(), Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
    }
}
