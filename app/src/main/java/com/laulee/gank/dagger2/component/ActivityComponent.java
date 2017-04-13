package com.laulee.gank.dagger2.component;

import android.app.Activity;

import com.laulee.gank.dagger2.module.ActivityModule;
import com.laulee.gank.dagger2.scope.ActivityScope;
import com.laulee.gank.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by laulee on 17/4/10.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);
}
