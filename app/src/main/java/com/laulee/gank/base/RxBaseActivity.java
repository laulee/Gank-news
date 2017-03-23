package com.laulee.gank.base;

import com.laulee.gank.utils.CreateUtil;

/**
 * Created by laulee on 16/12/18.
 */

public abstract class RxBaseActivity<P extends RxPresenter> extends BaseActivity
        implements BaseView {

    protected P mPresenter; //presenter 对象

    @Override
    protected void initParams() {
        mPresenter = CreateUtil.createT( this, 0 );
        if( mPresenter != null )
            mPresenter.attachView( this ); //presenter与view建立关联
    }

    @Override
    protected void onDestroy() {
        super.onDestroy( );
        if( mPresenter != null )
            mPresenter.destoryView( );
    }
}
