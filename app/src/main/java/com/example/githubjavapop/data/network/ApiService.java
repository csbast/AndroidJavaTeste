package com.example.githubjavapop.data.network;

import com.example.githubjavapop.domain.model.RepositoriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    Call<RepositoriesResponse> getItems();

}
