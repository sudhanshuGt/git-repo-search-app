package com.sudhanshutiwari.gitreposearchapp.gitapi;

import com.sudhanshutiwari.gitreposearchapp.modelclass.repoIssuesM;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface gitRepoIssues {

    @GET("repos/{user}/{repoName}/issues")
    Call<List<repoIssuesM>> getRepoIssues(@Path("user") String name, @Path("repoName") String repoName);
}
