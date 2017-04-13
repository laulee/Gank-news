package com.laulee.gank.dagger2.module;

import android.app.Activity;

import com.laulee.gank.dagger2.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by laulee on 17/4/10.
 */

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule( Activity activity ) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    public Activity provideActivity() {
        return activity;
    }
}
