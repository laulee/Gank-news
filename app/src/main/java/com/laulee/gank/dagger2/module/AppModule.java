package com.laulee.gank.dagger2.module;

import com.laulee.gank.app.App;
import com.laulee.gank.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by laulee on 17/4/10.
 */

@Module
public class AppModule {
    private App application;

    public AppModule( App application ) {
        this.application = application;
    }

    @Singleton
    @Provides
    public App provideApplicationContext() {
        return application;
    }

    @Singleton
    @Provides
    public RetrofitHelper provideRetrofitHelper() {
        return new RetrofitHelper( );
    }
}
