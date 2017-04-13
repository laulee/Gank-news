package com.laulee.gank.ui.fuli.activity;

import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.laulee.gank.R;
import com.laulee.gank.app.Constants;
import com.laulee.gank.base.BaseActivity;
import com.laulee.gank.bean.GankEntity;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by laulee on 17/3/14.
 */

public class FuliDetailActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.iv_girl_detail)
    ImageView imageView;
    GankEntity gankEntity;
    private PhotoViewAttacher mPhotoViewAttacher;

    @Override
    protected void initParams() {
        gankEntity = getIntent( ).getParcelableExtra( Constants.GANK_INFO );
        if( gankEntity == null )
            return;
        toolbar.setTitle( gankEntity.getType() );
        setSupportActionBar( toolbar );
        getSupportActionBar( ).setDisplayHomeAsUpEnabled( true );
        getSupportActionBar( ).setDisplayShowHomeEnabled( true );
        toolbar.setNavigationOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick( View view ) {
                finish( );
            }
        } );
        if( gankEntity != null && !TextUtils.isEmpty( gankEntity.getUrl( ) ) ) {
            Glide.with( getApplicationContext() ).load(  gankEntity.getUrl( ) ).asBitmap( ).into( new SimpleTarget<Bitmap>( ) {
                @Override
                public void onResourceReady( Bitmap resource,
                        GlideAnimation<? super Bitmap> glideAnimation ) {
                    imageView.setImageBitmap( resource );
                    mPhotoViewAttacher = new PhotoViewAttacher( imageView);
                }
            } );
        }
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_fuli_detail;
    }
}
