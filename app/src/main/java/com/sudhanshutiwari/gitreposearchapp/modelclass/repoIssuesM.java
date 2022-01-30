package com.sudhanshutiwari.gitreposearchapp.modelclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class repoIssuesM {


    @SerializedName("title")
    private String repoIssuesTitle;

    @SerializedName("number")
    private String repoIssuesNumber;

    @SerializedName("body")
    @Expose
    private String repoIssuesBy;


    public repoIssuesM(String repoIssuesTitle, String repoIssuesNumber, String repoIssuesBy) {
        this.repoIssuesTitle = repoIssuesTitle;
        this.repoIssuesNumber = repoIssuesNumber;
        this.repoIssuesBy = repoIssuesBy;
    }

    public String getRepoIssuesTitle() {
        return repoIssuesTitle;
    }

    public void setRepoIssuesTitle(String repoIssuesTitle) {
        this.repoIssuesTitle = repoIssuesTitle;
    }

    public String getRepoIssuesNumber() {
        return repoIssuesNumber;
    }

    public void setRepoIssuesNumber(String repoIssuesNumber) {
        this.repoIssuesNumber = repoIssuesNumber;
    }

    public String getRepoIssuesBy() {
        return repoIssuesBy;
    }

    public void setRepoIssuesBy(String repoIssuesBy) {
        this.repoIssuesBy = repoIssuesBy;
    }
}
