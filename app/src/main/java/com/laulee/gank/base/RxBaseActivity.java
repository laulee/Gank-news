package com.laulee.gank.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

import com.laulee.gank.app.App;
import com.laulee.gank.app.AppConfig;
import com.laulee.gank.dagger2.component.ActivityComponent;
import com.laulee.gank.dagger2.component.DaggerActivityComponent;
import com.laulee.gank.dagger2.module.ActivityModule;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by laulee on 16/12/18.
 */

public abstract class RxBaseActivity<P extends RxPresenter> extends RxAppCompatActivity
        implements BaseView {

    @Inject
    protected P mPresenter; //presenter 对象
    
    private Unbinder unbinder;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( setContentViewId( ) );
        initInject();
        mPresenter.attachView(this);
        unbinder = ButterKnife.bind( this );
        //将activity加入到集合中
        AppConfig.addActivity( this );
        initParams( );
    }

    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent( App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule( this );
    }

    protected abstract void initInject();

    //布局ID
    protected abstract int setContentViewId();

    protected abstract void initParams();

    @Override
    protected void onDestroy() {
        super.onDestroy( );
        if( unbinder != null )
            unbinder.unbind( );
        if( mPresenter != null )
            mPresenter.destoryView( );
        AppConfig.removeActivity( this );
    }

    @Override
    public void changeNightMode( boolean state ) {
        if( state ) {
            AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_YES );
        } else
            AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_NO );
        this.recreate( );
    }
}
