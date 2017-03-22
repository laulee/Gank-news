package com.laulee.gank.ui.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.laulee.gank.R;
import com.laulee.gank.base.BaseFragment;
import com.laulee.gank.base.BasePresenter;
import com.laulee.gank.presenter.UniteFragmentPresenter;
import com.laulee.gank.ui.android.fragment.UniteFragment;
import com.laulee.gank.ui.fuli.fragment.FuliFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by laulee on 17/2/27.
 */

public class GankFragment extends BaseFragment {

    @BindView(R.id.gank_fragment_layout_viewpager)
    ViewPager gankViewPager;
    @BindView(R.id.gank_fragment_layout_tablayout)
    TabLayout tabLayout;
    FragmentAdapter fragmentPagerAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_gank_layout;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initParams() {
        gankViewPager.setOffscreenPageLimit( 3 );
        fragmentPagerAdapter = new FragmentAdapter( getFragmentManager( ), initFragment( ) );
        gankViewPager.setAdapter( fragmentPagerAdapter );
        tabLayout.setupWithViewPager( gankViewPager );
    }

    @Override
    protected void initView( View rootView ) {

    }

    private List<Fragment> initFragment() {
        List<Fragment> fragmentList = new ArrayList<>( );
        UniteFragment androidFragment = new UniteFragment( );
        Bundle androidBundle = new Bundle( );
        androidBundle.putString( UniteFragmentPresenter.TECH, UniteFragmentPresenter.TECH_ANDROID );
        androidFragment.setArguments( androidBundle );
        UniteFragment iosFragment = new UniteFragment( );
        Bundle iosBundle = new Bundle( );
        iosBundle.putString( UniteFragmentPresenter.TECH, UniteFragmentPresenter.TECH_IOS );
        iosFragment.setArguments( iosBundle );
        UniteFragment webFragment = new UniteFragment( );
        Bundle webBundle = new Bundle( );
        webBundle.putString( UniteFragmentPresenter.TECH, UniteFragmentPresenter.TECH_WEB );
        webFragment.setArguments( webBundle );
        fragmentList.add( androidFragment );
        fragmentList.add( iosFragment );
        fragmentList.add( webFragment );
        fragmentList.add( new FuliFragment( ) );
        return fragmentList;
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad( );
    }
}
