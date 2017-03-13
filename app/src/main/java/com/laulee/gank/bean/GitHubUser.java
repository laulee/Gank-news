package com.laulee.gank.bean;

/**
 * Created by laulee on 17/3/1.
 */

public class GitHubUser {

    /**
     * login : laulee
     * id : 22267711
     * avatar_url : https://avatars0.githubusercontent.com/u/22267711?v=3
     * gravatar_id :
     * url : https://api.github.com/users/laulee
     * html_url : https://github.com/laulee
     * followers_url : https://api.github.com/users/laulee/followers
     * following_url : https://api.github.com/users/laulee/following{/other_user}
     * gists_url : https://api.github.com/users/laulee/gists{/gist_id}
     * starred_url : https://api.github.com/users/laulee/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/laulee/subscriptions
     * organizations_url : https://api.github.com/users/laulee/orgs
     * repos_url : https://api.github.com/users/laulee/repos
     * events_url : https://api.github.com/users/laulee/events{/privacy}
     * received_events_url : https://api.github.com/users/laulee/received_events
     * type : User
     * site_admin : false
     * name : null
     * company : null
     * blog : null
     * location : null
     * email : null
     * hireable : null
     * bio : null
     * public_repos : 3
     * public_gists : 0
     * followers : 0
     * following : 0
     * created_at : 2016-09-18T02:16:31Z
     * updated_at : 2017-01-11T14:09:22Z
     */

    private String login;
    private int id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private boolean site_admin;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String hireable;
    private String bio;
    private int public_repos;
    private int public_gists;
    private int followers;
    private int following;
    private String created_at;
    private String updated_at;

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public String getType() {
        return type;
    }

    public boolean isSite_admin() {
        return site_admin;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getBlog() {
        return blog;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getHireable() {
        return hireable;
    }

    public String getBio() {
        return bio;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public int getPublic_gists() {
        return public_gists;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
