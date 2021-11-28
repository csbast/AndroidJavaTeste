package com.example.githubjavapop.domain.model;

public class Owner {

    private final String login;
    private final String avatar_url;

    public  Owner(String userName, String avatarUrl){
        this.avatar_url = avatarUrl;
        this.login = userName;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}
