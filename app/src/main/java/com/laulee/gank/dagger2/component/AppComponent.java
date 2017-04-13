package com.laulee.gank.dagger2.component;

import com.laulee.gank.app.App;
import com.laulee.gank.dagger2.module.AppModule;
import com.laulee.gank.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by laulee on 17/4/10.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    //提供app
    App getApplicationContext();

    //提供网络请求
    RetrofitHelper retrofitHelper();
}
