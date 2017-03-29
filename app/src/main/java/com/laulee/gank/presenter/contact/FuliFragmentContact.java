package com.laulee.gank.presenter.contact;

import com.laulee.commonsdk.base.BaseView;
import com.laulee.gank.bean.GankEntity;

import java.util.List;

/**
 * Created by laulee on 17/3/22.
 */

public class FuliFragmentContact {
    public interface View extends BaseView {
        void showContent( List<GankEntity> gankItemEntities );

        void showError( String message );
    }

}
