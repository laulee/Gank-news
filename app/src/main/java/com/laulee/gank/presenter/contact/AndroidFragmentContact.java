package com.laulee.gank.presenter.contact;

import com.laulee.gank.base.BaseView;
import com.laulee.gank.bean.GankEntity;

import java.util.List;

/**
 * Created by laulee on 17/2/27.
 */

public class AndroidFragmentContact {

    public interface AndroidFragmentView extends BaseView {
        void showContent( List<GankEntity> gankItemEntities );

        void showError( String message );

        void showGirlImage( String url );
    }

}
