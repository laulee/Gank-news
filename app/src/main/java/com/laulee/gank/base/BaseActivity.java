package com.laulee.gank.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.laulee.gank.app.AppConfig;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by laulee on 16/12/18.
 */

public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity
        implements BaseView {

    protected P mPresenter; //presenter 对象
    private Unbinder unbinder;

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        mPresenter = createPresenter( );
        if( mPresenter != null )
            mPresenter.attachView( this ); //presenter与view建立关联
        setContentView( setContentViewId( ) );
        unbinder = ButterKnife.bind( this );
        init( );
    }

    /**
     * 初始化数据
     */
    private void init() {
        //将activity加入到集合中
        AppConfig.addActivity( this );
        initParams( );
    }

    /**
     * 初始化参数
     */
    protected abstract void initParams();

    //设置布局界面ID
    protected abstract int setContentViewId();

    //创建presenter对象
    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy( );
        unbinder.unbind( );
        if( mPresenter != null )
            mPresenter.destoryView( );
    }
}
