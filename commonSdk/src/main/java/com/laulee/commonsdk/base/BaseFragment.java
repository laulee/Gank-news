package com.laulee.commonsdk.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by laulee on 17/3/23.
 */

public abstract class BaseFragment extends RxFragment {

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
        unbinder = ButterKnife.bind( this, view );
        initView( view );
        initParams( );
        isCreate = true;
        lazyLoad( );
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

    protected abstract void initView( View view );

    protected abstract void initParams();

    @Override
    public void onDestroy() {
        super.onDestroy( );
        if( unbinder != null )
            unbinder.unbind( );
    }
}
