package com.example.githubjavapop.presentation.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.githubjavapop.data.network.ApiClient;
import com.example.githubjavapop.data.network.ApiService;
import com.example.githubjavapop.domain.model.Repositories;
import com.example.githubjavapop.domain.model.RepositoriesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Repositories>> repositoriesList;

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    public HomeViewModel() {
    }

    public MutableLiveData<List<Repositories>> getRepositoriesList() {
        if (repositoriesList == null) {
            repositoriesList = new MutableLiveData<>();
        }
        return repositoriesList;
    }

    public void setupNetwork() {
        isLoading.postValue(true);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<RepositoriesResponse> call = apiService.getItems();
        call.enqueue(new Callback<RepositoriesResponse>() {
            @Override
            public void onResponse(@NonNull Call<RepositoriesResponse> call, @NonNull Response<RepositoriesResponse> response) {
                if (response.body() != null) {
                    RepositoriesResponse repositoriesResponse = response.body();
                    List<Repositories> repositoriesList = mapRepositoryFromResponse(repositoriesResponse);
                    HomeViewModel.this.repositoriesList.setValue(repositoriesList);
                }
                Log.d("HomeFragment", "Response = " + repositoriesList);
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<RepositoriesResponse> call, @NonNull Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
                isLoading.postValue(false);
            }
        });
    }

    private List<Repositories> mapRepositoryFromResponse(RepositoriesResponse repositoriesResponse) {
        return repositoriesResponse.getRepositories();
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }


    //    public void sortByname(List<Repositories> repositoriesList) {
//        if (repositoriesList != null) {
//            repositoriesList = repositoriesList.sort(Comparator.comparing(Repositories::getName));
//            this.repositoriesList.setValue(repositoriesList);
//        }
//    }
//
//    public void sortByStars(List<Repositories> repositoriesList) {
//        if (repositoriesList != null) {
//            repositoriesList = repositoriesList.stream()
//                    .sorted(Comparator.comparing(Repositories::getStargazers_count))
//                    .collect(Collectors.toList());
//            this.repositoriesList.setValue(repositoriesList);
//        }
//    }
}
