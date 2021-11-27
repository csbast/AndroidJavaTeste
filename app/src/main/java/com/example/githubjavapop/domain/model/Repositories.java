package com.example.githubjavapop.domain.model;

import com.google.gson.annotations.SerializedName;

public class Repositories {

    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String full_name;
    @SerializedName("OwnerObject")
    private Owner OwnerObject;
    @SerializedName("html_url")
    private String html_url;
    @SerializedName("description")
    private String description;
    @SerializedName("stargazers_count")
    private float stargazers_count;
    @SerializedName("forks_count")
    private float forks_count;

    public Repositories(String name, String full_name, String description, String htmlUrl, float stargazers_count, float forks_count, Owner userObject) {
        this.description = description;
        this.forks_count = forks_count;
        this.html_url = htmlUrl;
        this.name = name;
        this.OwnerObject = userObject;
        this.full_name = full_name;
        this.stargazers_count = stargazers_count;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public Owner getOwnerObject() {
        return OwnerObject;
    }

    public void setOwnerObject(Owner ownerObject) {
        OwnerObject = ownerObject;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getDescription() {
        return description;
    }

    public float getStargazers_count() {
        return stargazers_count;
    }

    public float getForks_count() {
        return forks_count;
    }

}
