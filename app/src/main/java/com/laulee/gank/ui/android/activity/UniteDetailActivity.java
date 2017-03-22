package com.laulee.gank.ui.android.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.laulee.gank.R;
import com.laulee.gank.app.Constants;
import com.laulee.gank.base.BaseActivity;
import com.laulee.gank.base.BasePresenter;
import com.laulee.gank.bean.GankEntity;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;

/**
 * Created by laulee on 17/3/14.
 */

public class UniteDetailActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.wv_android_detail)
    WebView webView;
    GankEntity gankEntity;

    @Override
    protected void initParams() {
        gankEntity = getIntent( ).getParcelableExtra( Constants.GANK_INFO );
        if( gankEntity == null )
            return;
        toolbar.setTitle( gankEntity.getDesc( ) );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        configWebView( );
    }

    /**
     * 配置webview
     */
    private void configWebView() {
        WebSettings settings = webView.getSettings( );
        settings.setJavaScriptEnabled( true );
        settings.setLoadWithOverviewMode( true );
        settings.setLayoutAlgorithm( WebSettings.LayoutAlgorithm.SINGLE_COLUMN );
        settings.setSupportZoom( true );
        webView.setWebViewClient( new WebViewClient( ) {
            @Override
            public boolean shouldOverrideUrlLoading( WebView view, String url ) {
                view.loadUrl( url );
                return true;
            }
        } );
        webView.setWebChromeClient( new WebChromeClient( ) {

            @Override
            public void onProgressChanged( WebView webView, int i ) {
                super.onProgressChanged( webView, i );
            }

            @Override
            public void onReceivedTitle( WebView webView, String s ) {
                super.onReceivedTitle( webView, s );
                setTitle( s );
            }
        } );
        webView.loadUrl( gankEntity.getUrl( ) );
    }

    @Override
    protected int setContentViewId() {
        return R.layout.android_detail;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
