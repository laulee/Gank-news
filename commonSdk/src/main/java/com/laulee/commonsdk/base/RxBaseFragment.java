package com.laulee.commonsdk.base;

import com.laulee.commonsdk.utils.CreateUtil;

/**
 * Created by laulee on 17/2/27.
 */

public abstract class RxBaseFragment<P extends RxPresenter> extends BaseFragment
        implements BaseView {

    protected P mPresenter;

    @Override
    protected void initParams() {
        mPresenter = CreateUtil.createT( this, 0 );
        if( mPresenter != null )
            mPresenter.attachView( this );
    }

    @Override
    public void onDestroy() {
        super.onDestroy( );
        if( mPresenter != null )
            mPresenter.destoryView( );
    }
}
