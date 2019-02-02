package com.testtasks.taxiordersservice.di;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.testtasks.taxiordersservice.data.APIService;
import com.testtasks.taxiordersservice.data.PreferenceManager;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ModelModule {
    private final static String BASE_URL = "http://www.roxiemobile.ru/careers/test/";
    private final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    @Provides
    PreferenceManager providePreferenceManager(Context appContext){
        return PreferenceManager.getInstance(appContext);
    }

    @Provides
    Retrofit provideRetrofitBuilder() {
        Gson gson = new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .create();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.MINUTES)
                .readTimeout(20,TimeUnit.MINUTES).build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build();
    }

    @Provides
    APIService provideAPIService(Retrofit retrofitBuilder) {
        return retrofitBuilder.create(APIService.class);
    }
}
