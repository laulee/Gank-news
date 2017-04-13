package com.laulee.gank.ui.unite.fragment;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.laulee.gank.R;
import com.laulee.gank.app.Constants;
import com.laulee.gank.base.BaseRecyclerAdapter;
import com.laulee.gank.base.RxBaseFragment;
import com.laulee.gank.bean.GankEntity;
import com.laulee.gank.presenter.UniteFragmentPresenter;
import com.laulee.gank.presenter.contact.UntieFragmentContact;
import com.laulee.gank.ui.unite.activity.UniteDetailActivity;
import com.laulee.gank.ui.unite.adapter.UniteAdapter;
import com.laulee.gank.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by laulee on 17/2/27.
 */

public class UniteFragment extends RxBaseFragment<UniteFragmentPresenter>
        implements UntieFragmentContact.AndroidFragmentView {

    @BindView(R.id.fragment_unite_swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.fragment_unite_image)
    ImageView ivBlur;
    @BindView(R.id.fragment_unite_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_unite_appbar)
    AppBarLayout appBarLayout;
    private UniteAdapter androidAdapter;
    private List<GankEntity> gankItemEntityList = new ArrayList<>( );
    private String tech;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_unite_layout;
    }

    @Override
    protected void initInject() {
        getFragmentComponent( ).inject( this );
    }

    @Override
    protected void initParams() {
        tech = getArguments( ).getString( UniteFragmentPresenter.TECH );
        mPresenter.getData( tech, 1 );
        mPresenter.getImage( 20 );
    }

    @Override
    protected void initView( View rootView ) {
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext( ) ) );
        appBarLayout.addOnOffsetChangedListener( new AppBarLayout.OnOffsetChangedListener( ) {
            @Override
            public void onOffsetChanged( AppBarLayout appBarLayout, int verticalOffset ) {
                if( verticalOffset >= 0 ) {
                    swipeRefreshLayout.setEnabled( true );
                } else {
                    swipeRefreshLayout.setEnabled( false );
                    float rate = (float) ( SystemUtil.dp2px( getContext(),
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
                mPresenter.getData( tech, 1 );
            }
        } );

        androidAdapter = new UniteAdapter( gankItemEntityList );
        androidAdapter.setIOnItemClickListener(
                new BaseRecyclerAdapter.IOnItemClickListener<GankEntity>( ) {
                    @Override
                    public void onItemClick( View view, GankEntity entity, int position ) {
                        Intent intent = new Intent( getActivity( ), UniteDetailActivity.class );
                        intent.putExtra( Constants.GANK_INFO, entity );
                        startActivity( intent );
                    }
                } );
        recyclerView.setAdapter( androidAdapter );
    }

    @Override
    public void showContent( List<GankEntity> gankItemEntities ) {
        if( swipeRefreshLayout.isRefreshing( ) ) {
            swipeRefreshLayout.setRefreshing( false );
        }
        androidAdapter.addList( gankItemEntities );
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
