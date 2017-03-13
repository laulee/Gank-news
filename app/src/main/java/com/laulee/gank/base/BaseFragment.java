package com.laulee.gank.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by laulee on 17/2/27.
 */

public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements BaseView {

    protected P mPresenter;
    private View rootView;
    private boolean isCreate = false;
    private boolean isVisible = false;
    private Unbinder unbinder;

    protected abstract int setLayoutId();

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState ) {
        if( rootView == null ) {
            rootView = inflater.inflate( setLayoutId( ), container, false );
        }

        ViewGroup viewGroup = (ViewGroup) rootView.getParent( );
        if( viewGroup != null ) {
            viewGroup.removeView( rootView );
        }
        return rootView;
    }

    @Override
    public void onViewCreated( View view, @Nullable Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );
        configPresenter( );
        unbinder = ButterKnife.bind(this, view );
        initView( view );
        initParams( );
        isCreate = true;
        lazyLoad( );
    }

    private void configPresenter() {
        mPresenter = createPresenter( );
        if( mPresenter != null )
            mPresenter.attachView( this );
    }

    protected abstract P createPresenter();

    protected abstract void initParams();

    protected abstract void initView( View rootView );

    @Override
    public void onDestroy() {
        super.onDestroy( );
        unbinder.unbind( );
        if( mPresenter != null )
            mPresenter.destoryView( );
    }

    @Override
    public void onHiddenChanged( boolean hidden ) {
        super.onHiddenChanged( hidden );
        if( !hidden ) {
            isVisible = true;
            lazyLoad( );
        }
    }

    protected void lazyLoad() {
        if( !isCreate || !isVisible( ) ) {
            return;
        }
    }
}
