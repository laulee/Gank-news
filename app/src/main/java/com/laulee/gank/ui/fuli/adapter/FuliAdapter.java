package com.laulee.gank.ui.fuli.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.laulee.gank.R;
import com.laulee.gank.app.App;
import com.laulee.gank.base.BaseRecyclerAdapter;
import com.laulee.gank.bean.GankEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by laulee on 17/2/28.
 */

public class FuliAdapter extends BaseRecyclerAdapter<GankEntity, FuliAdapter.ViewHolder> {

    public FuliAdapter( List<GankEntity> datas ) {
        super( datas );
    }

    @Override
    protected ViewHolder createViewHolder( View view ) {
        return new ViewHolder( view );
    }

    @Override
    protected int layoutResId() {
        return R.layout.item_fuli;
    }

    @Override
    public int getItemViewType(int position) {
        return Math.round((float) App.SCREEN_WIDTH / (float) datas.get(position).getHeight() * 10f);
    }

    @Override
    protected void onBindViewHolder( ViewHolder holder, GankEntity entity ) {
        //存在记录的高度时先Layout再异步加载图片
        if( entity.getHeight( ) > 0 ) {
            ViewGroup.LayoutParams layoutParams = holder.picture.getLayoutParams( );
            layoutParams.height = entity.getHeight( );
        }

        Glide.with( App.getInstance( ).getApplicationContext( ) ).load( entity.getUrl( ) )
                .asBitmap( ).diskCacheStrategy( DiskCacheStrategy.ALL )
                .into( new SimpleTarget<Bitmap>( App.SCREEN_WIDTH / 2, App.SCREEN_WIDTH / 2 ) {
                    @Override
                    public void onResourceReady( Bitmap resource,
                            GlideAnimation<? super Bitmap> glideAnimation ) {
                        if( holder.getAdapterPosition( ) != RecyclerView.NO_POSITION ) {
                            if( entity.getHeight( ) <= 0 ) {
                                int width = resource.getWidth( );
                                int height = resource.getHeight( );
                                int realHeight = ( App.SCREEN_WIDTH / 2 ) * height / width;
                                entity.setHeight( realHeight );
                                ViewGroup.LayoutParams lp = holder.picture.getLayoutParams( );
                                lp.height = realHeight;
                            }
                            holder.picture.setImageBitmap( resource );
                        }
                    }
                } );
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_fuli_picture)
        ImageView picture;

        public ViewHolder( View itemView ) {
            super( itemView );
            ButterKnife.bind( this, itemView );
        }
    }
}
