package com.laulee.gank.dagger2.component;

import com.laulee.gank.dagger2.module.FragmentModule;
import com.laulee.gank.dagger2.scope.FragmentScope;
import com.laulee.gank.ui.fuli.fragment.FuliFragment;
import com.laulee.gank.ui.main.fragment.SettingFragment;
import com.laulee.gank.ui.unite.fragment.UniteFragment;

import dagger.Component;

/**
 * Created by laulee on 17/4/10.
 */

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {

    void inject( SettingFragment settingFragment );

    void inject( UniteFragment uniteFragment );

    void inject( FuliFragment fuliFragment );
}
