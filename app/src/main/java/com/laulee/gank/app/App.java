package com.laulee.gank.app;

import android.app.Application;

/**
 * Created by laulee on 17/3/1.
 */

public class App extends Application {

    private static App instance;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate( );
        instance = this;
    }
}
