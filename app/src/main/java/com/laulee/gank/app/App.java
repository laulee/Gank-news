package com.laulee.gank.app;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.laulee.gank.dagger2.component.AppComponent;
import com.laulee.gank.dagger2.component.DaggerAppComponent;
import com.laulee.gank.dagger2.module.AppModule;

/**
 * Created by laulee on 17/3/1.
 */

public class App extends Application {

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;
    private static App instance;

    public static synchronized App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder( ).appModule( new AppModule( instance ) ).build( );
    }

    @Override
    public void onCreate() {
        super.onCreate( );
        instance = this;
        //初始化屏幕宽高
        getScreenSize( );
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this
                .getSystemService( Context.WINDOW_SERVICE );
        DisplayMetrics dm = new DisplayMetrics( );
        Display display = windowManager.getDefaultDisplay( );
        display.getMetrics( dm );
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if( SCREEN_WIDTH > SCREEN_HEIGHT ) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }
}
