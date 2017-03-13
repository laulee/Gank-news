package com.laulee.gank.ui.android;

import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.laulee.gank.R;
import com.laulee.gank.base.BaseFragment;
import com.laulee.gank.base.BaseRecyclerAdapter;
import com.laulee.gank.bean.GankEntity;
import com.laulee.gank.presenter.AndroidFragmentPresenter;
import com.laulee.gank.presenter.contact.AndroidFragmentContact;
import com.laulee.gank.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laulee on 17/2/27.
 */

public class AndroidFragment extends BaseFragment<AndroidFragmentPresenter>
        implements AndroidFragmentContact.AndroidFragmentView {

    SwipeRefreshLayout swipeRefreshLayout;
    ImageView ivBlur;
    RecyclerView recyclerView;
    private AndroidAdapter androidAdapter;
    private AppBarLayout appBarLayout;
    private List<GankEntity> gankItemEntityList = new ArrayList<>( );

    @Override
    protected int setLayoutId() {
        return R.layout.android_fragment_layout;
    }

    @Override
    protected AndroidFragmentPresenter createPresenter() {
        return new AndroidFragmentPresenter( );
    }

    @Override
    protected void initParams() {
        mPresenter.getData( );
        mPresenter.getImage( );
    }

    @Override
    protected void initView( View rootView ) {
        swipeRefreshLayout = (SwipeRefreshLayout) rootView
                .findViewById( R.id.android_fragment_swipe_layout );
        ivBlur = (ImageView) rootView.findViewById( R.id.android_fragment_image );
        recyclerView = (RecyclerView) rootView.findViewById( R.id.android_fragment_recycler );
        appBarLayout = (AppBarLayout) rootView.findViewById( R.id.android_fragment_appbar );
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext( ) ) );
        appBarLayout.addOnOffsetChangedListener( new AppBarLayout.OnOffsetChangedListener( ) {
            @Override
            public void onOffsetChanged( AppBarLayout appBarLayout, int verticalOffset ) {
                if( verticalOffset >= 0 ) {
                    swipeRefreshLayout.setEnabled( true );
                } else {
                    swipeRefreshLayout.setEnabled( false );
                    float rate = (float) ( SystemUtil.dp2px( getContext( ),
                                                             256 ) + verticalOffset * 2 ) /
                            SystemUtil
                            .dp2px( getContext( ), 256 );
                    if( rate >= 0 )
                        ivBlur.setAlpha( rate );
                }
            }
        } );
        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener( ) {
            @Override
            public void onRefresh() {
                mPresenter.getData( );
            }
        } );
    }

    @Override
    public void showContent( List<GankEntity> gankItemEntities ) {
        if( swipeRefreshLayout.isRefreshing( ) ) {
            swipeRefreshLayout.setRefreshing( false );
        }
        androidAdapter = new AndroidAdapter( gankItemEntities );
        androidAdapter.setIOnItemClickListener(
                new BaseRecyclerAdapter.IOnItemClickListener<GankEntity>( ) {
                    @Override
                    public void onItemClick( View view, GankEntity entity, int position ) {
                        Toast.makeText( getContext( ), entity.getDesc( ), Toast.LENGTH_LONG )
                                .show( );
                    }
                } );
        recyclerView.setAdapter( androidAdapter );
    }

    @Override
    public void showError( String message ) {
        if( swipeRefreshLayout.isRefreshing( ) ) {
            swipeRefreshLayout.setRefreshing( false );
        }
        Toast.makeText( getContext( ), message, Toast.LENGTH_LONG ).show( );
    }

    @Override
    public void showGirlImage( String url ) {
        Glide.with( getActivity( ) ).load( url ).into( ivBlur );
    }
}
