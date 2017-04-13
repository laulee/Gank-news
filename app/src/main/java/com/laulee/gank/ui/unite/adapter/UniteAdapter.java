package com.laulee.gank.ui.unite.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.laulee.gank.R;
import com.laulee.gank.base.BaseRecyclerAdapter;
import com.laulee.gank.bean.GankEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by laulee on 17/2/28.
 */

public class UniteAdapter extends BaseRecyclerAdapter<GankEntity, UniteAdapter.ViewHolder> {

    public UniteAdapter( List<GankEntity> datas ) {
        super( datas );
    }

    @Override
    protected ViewHolder createViewHolder( View view ) {
        return new ViewHolder( view );
    }

    @Override
    protected int layoutResId() {
        return R.layout.item_tech;
    }

    @Override
    protected void onBindViewHolder( ViewHolder holder, GankEntity entity ) {
        holder.title.setText( entity.getDesc() );
        holder.author.setText( entity.getWho() );
        holder.time.setText( entity.getCreatedAt( ) );
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_tech_title)
        TextView title;
        @BindView(R.id.tv_tech_author)
        TextView author;
        @BindView(R.id.tv_tech_time)
        TextView time;

        public ViewHolder( View itemView ) {
            super( itemView );
            ButterKnife.bind( this,itemView );
        }
    }
}
