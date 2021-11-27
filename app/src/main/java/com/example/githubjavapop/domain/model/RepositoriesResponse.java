package com.example.githubjavapop.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepositoriesResponse {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<Repositories> repositories;

    public List<Repositories> getRepositories() {
        return repositories;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public void setRepositories(List<Repositories> repositories) {
        this.repositories = repositories;
    }
}
