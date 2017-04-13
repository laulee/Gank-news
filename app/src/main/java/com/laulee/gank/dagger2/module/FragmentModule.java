package com.laulee.gank.dagger2.module;

import android.app.Activity;

import com.laulee.gank.dagger2.scope.FragmentScope;
import com.trello.rxlifecycle.components.support.RxFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by laulee on 17/4/10.
 */

@Module
public class FragmentModule {
    private RxFragment fragment;

    public FragmentModule( RxFragment fragment ) {this.fragment = fragment;}

    @FragmentScope
    @Provides
    public Activity provideActivity() {
        return fragment.getActivity( );
    }
}
