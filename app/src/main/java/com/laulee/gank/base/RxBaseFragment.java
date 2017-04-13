package com.laulee.gank.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laulee.gank.app.App;
import com.laulee.gank.dagger2.component.DaggerFragmentComponent;
import com.laulee.gank.dagger2.component.FragmentComponent;
import com.laulee.gank.dagger2.module.FragmentModule;
import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by laulee on 17/2/27.
 */

public abstract class RxBaseFragment<P extends RxPresenter> extends RxFragment implements BaseView {

    @Inject
    protected P mPresenter;

    private View rootView;
    private boolean isCreate = false;
    private Unbinder unbinder;
    private String FRAGMENT_STATE_SAVE_IS_HIDDEN = "fragment_state_save_status";

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
        initInject( );
        return rootView;
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder( ).appComponent( App.getAppComponent( ) )
                .fragmentModule( getFragmentModule( ) ).build( );
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule( this );
    }

    //初始化注入
    protected abstract void initInject();

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        //解决重启重叠问题
        resolveOverlapping( savedInstanceState );
    }

    @Override
    public void onViewCreated( View view, @Nullable Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );
        mPresenter.attachView( this );
        unbinder = ButterKnife.bind( this, view );
        initView( view );
        if( savedInstanceState == null ) {
            if( !isHidden( ) ) {
                isCreate = true;
                lazyLoad( );
            }
        } else {
            if( !savedInstanceState.getBoolean( FRAGMENT_STATE_SAVE_IS_HIDDEN ) ) {
                isCreate = true;
                lazyLoad( );
            }
        }
    }

    private void resolveOverlapping( Bundle savedInstanceState ) {
        if( savedInstanceState != null ) {
            boolean isHidden = savedInstanceState.getBoolean( FRAGMENT_STATE_SAVE_IS_HIDDEN );
            FragmentTransaction ft = getFragmentManager( ).beginTransaction( );
            if( isHidden ) {
                ft.hide( this );
            } else {
                ft.show( this );
            }
            ft.commit( );
        }
    }

    @Override
    public void onHiddenChanged( boolean hidden ) {
        super.onHiddenChanged( hidden );
        if( !hidden && !isCreate ) {
            isCreate = true;
            lazyLoad( );
        }
    }

    protected void lazyLoad() {
        initParams( );
    }

    protected abstract void initView( View view );

    protected abstract void initParams();

    @Override
    public void onDestroy() {
        super.onDestroy( );
        if( unbinder != null )
            unbinder.unbind( );
        if( mPresenter != null )
            mPresenter.destoryView( );
    }

    @Override
    public void changeNightMode( boolean isNight ) {

    }

    @Override
    public void onSaveInstanceState( Bundle outState ) {
        super.onSaveInstanceState( outState );
        outState.putBoolean( FRAGMENT_STATE_SAVE_IS_HIDDEN, isHidden( ) );
    }
}
