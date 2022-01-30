package com.sudhanshutiwari.gitreposearchapp.modelclass;

import com.google.gson.annotations.SerializedName;

public class gitUser {

    @SerializedName("name")
    private  String name ;

    @SerializedName("followers")
    private  String followers ;

    @SerializedName("following")
    private  String following ;

    @SerializedName("avatar_url")
    private  String avator ;

    @SerializedName("public_repos")
    private String userRepo;



    public gitUser(String name, String followers, String following, String avator, String userRepo) {
        this.name = name;
        this.followers = followers;
        this.following = following;
        this.avator = avator;
        this.userRepo = userRepo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(String userRepo) {
        this.userRepo = userRepo;
    }

}
