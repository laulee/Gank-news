package com.laulee.gank.bean;

import java.util.List;

/**
 * Created by laulee on 17/3/13.
 */

public class GankEntity {

    /**
     * _id : 58c264e6421aa90f13178640
     * createdAt : 2017-03-10T16:33:42.304Z
     * desc : 基于 RecyclerView 实现的横向滑动组件，超级漂亮和实用。
     * images : ["http://img.gank.io/3eaa6a41-b7bc-44ba-8663-818c34e636af","http://img.gank
     * .io/64a30537-7ef8-4d0a-a188-1ec1bd2e7e1b"]
     * publishedAt : 2017-03-13T12:37:59.782Z
     * source : web
     * type : Android
     * url : https://github.com/yarolegovich/DiscreteScrollView
     * used : true
     * who : Yaroslav
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() { return _id;}

    public void set_id( String _id ) { this._id = _id;}

    public String getCreatedAt() { return createdAt;}

    public void setCreatedAt( String createdAt ) { this.createdAt = createdAt;}

    public String getDesc() { return desc;}

    public void setDesc( String desc ) { this.desc = desc;}

    public String getPublishedAt() { return publishedAt;}

    public void setPublishedAt( String publishedAt ) { this.publishedAt = publishedAt;}

    public String getSource() { return source;}

    public void setSource( String source ) { this.source = source;}

    public String getType() { return type;}

    public void setType( String type ) { this.type = type;}

    public String getUrl() { return url;}

    public void setUrl( String url ) { this.url = url;}

    public boolean isUsed() { return used;}

    public void setUsed( boolean used ) { this.used = used;}

    public String getWho() { return who;}

    public void setWho( String who ) { this.who = who;}

    public List<String> getImages() { return images;}

    public void setImages( List<String> images ) { this.images = images;}
}
