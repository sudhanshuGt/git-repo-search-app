package com.sudhanshutiwari.gitreposearchapp.gitapi;

import com.sudhanshutiwari.gitreposearchapp.modelclass.UserGitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubRepoEndPoint {

    @GET("/users/{user}/repos")
    Call<List<UserGitHubRepo>> getRepo(@Path("user") String name );
}
