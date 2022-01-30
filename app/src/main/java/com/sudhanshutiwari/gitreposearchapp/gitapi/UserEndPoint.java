package com.sudhanshutiwari.gitreposearchapp.gitapi;

import com.sudhanshutiwari.gitreposearchapp.modelclass.gitUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserEndPoint {

    @GET("/users/{user}")
    Call<gitUser> getTheUser(@Path("user") String user) ;
}
