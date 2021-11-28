package com.example.githubjavapop.domain.model;

import com.google.gson.annotations.SerializedName;

public class Repositories {

    private String name;
    @SerializedName("full_name")
    private String fullName;
    private Owner owner;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("description")
    private String description;
    @SerializedName("stargazers_count")
    private float stargazersCount;
    @SerializedName("forks_count")
    private float forksCount;

    public Repositories(String name, String fullName, String description, String htmlUrl, float stargazersCount, float forksCount, Owner userObject) {
        this.description = description;
        this.forksCount = forksCount;
        this.htmlUrl = htmlUrl;
        this.name = name;
        this.owner = userObject;
        this.fullName = fullName;
        this.stargazersCount = stargazersCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStargazersCount() {
        return Math.round(stargazersCount);
    }

    public void setStargazersCount(float stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public float getForksCount() {
        return forksCount;
    }

    public void setForksCount(float forksCount) {
        this.forksCount = forksCount;
    }
}

