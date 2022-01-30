package com.sudhanshutiwari.gitreposearchapp.modelclass;

import com.google.gson.annotations.SerializedName;

public class UserGitHubRepo {

    @SerializedName("name")
    private String repoName;

    @SerializedName("description")
    private String repoDescription;

    @SerializedName("language")    
    private String repoLanguage;

    @SerializedName("open_issues_count")
    private String repoIssues;

    @SerializedName("stargazers_count")
    private String starGazers;

    public String getStarGazers() {
        return starGazers;
    }

    public void setStarGazers(String starGazers) {
        this.starGazers = starGazers;
    }

    public UserGitHubRepo(String repoName, String repoDescription, String repoLanguage, String repoIssues, String starGazers) {
        this.repoName = repoName;
        this.repoDescription = repoDescription;
        this.repoLanguage = repoLanguage;
        this.repoIssues = repoIssues;
        this.starGazers = starGazers;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoDescription() {
        return repoDescription;
    }

    public void setRepoDescription(String repoDescription) {
        this.repoDescription = repoDescription;
    }

    public String getRepoLanguage() {
        return repoLanguage;
    }

    public void setRepoLanguage(String repoLanguage) {
        this.repoLanguage = repoLanguage;
    }

    public String getRepoIssues() {
        return repoIssues;
    }

    public void setRepoIssues(String repoIssues) {
        this.repoIssues = repoIssues;
    }
}
