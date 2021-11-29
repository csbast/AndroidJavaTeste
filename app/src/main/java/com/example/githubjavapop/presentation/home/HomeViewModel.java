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

    public MutableLiveData<Boolean> connectionFailureDialog = new MutableLiveData<>(false);

    public HomeViewModel() {
    }

    public MutableLiveData<List<Repositories>> getRepositoriesList() {
        if (repositoriesList == null) {
            repositoriesList = new MutableLiveData<>();
        }
        return repositoriesList;
    }

    public void loadRepositories() {
        if (repositoriesList.getValue() == null || repositoriesList.getValue().size() < 30) {
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
                    isLoading.postValue(false);
                }

                @Override
                public void onFailure(@NonNull Call<RepositoriesResponse> call, @NonNull Throwable t) {
                    Log.d("TAG", "Response = " + t.toString());
                    isLoading.postValue(false);
                    connectionFailureDialog.postValue(true);
                }
            });
        }
    }

    /**
     * Reconheço que essa não seria a melhor implementação, com o kotlin poderiamos utilizar
     * Coroutines para resolver o problema de concorrência, no Java poderiamos utilizar RxJava mas infelizmente não tive tempo de compreender o funcionamento da biblioteca dentro do prazo
     *
     */
    public void loadSortedRepositories(String query) {
        isLoading.postValue(true);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<RepositoriesResponse> call = apiService.getItems();
        call.enqueue(new Callback<RepositoriesResponse>() {
            @Override
            public void onResponse(@NonNull Call<RepositoriesResponse> call, @NonNull Response<RepositoriesResponse> response) {
                if (response.body() != null) {
                    RepositoriesResponse repositoriesResponse = response.body();
                    List<Repositories> filtredList = filterMyList(mapRepositoryFromResponse(repositoriesResponse), query);
                    HomeViewModel.this.repositoriesList.setValue(filtredList);
                }
                Log.d("HomeFragment", "Response = " + repositoriesList);
                isLoading.postValue(false);
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


    public void sortByName() {
        if (repositoriesList.getValue() != null) {
            repositoriesList.getValue().sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
            this.repositoriesList.setValue(repositoriesList.getValue());
        }
    }

    private List<Repositories> filterMyList(List<Repositories> list, String query) {
        if (list != null) {
            list.removeIf((Repositories repository) ->
                    !repository.getOwner().getLogin().toLowerCase().contains(query.toLowerCase())
                            && !repository.getName().toLowerCase().contains(query.toLowerCase())
            );
        }
        return list;
    }

    public void filterList(String query) {
        if (repositoriesList.getValue() != null && repositoriesList.getValue().size() < 30) {
            loadSortedRepositories(query);
        } else {
            List<Repositories> filtredList = filterMyList(repositoriesList.getValue(), query);
            HomeViewModel.this.repositoriesList.setValue(filtredList);
        }
    }

    public void sortByStars() {
        if (this.repositoriesList.getValue() != null) {
            repositoriesList.getValue().sort((o1, o2) -> Integer.compare(o2.getStargazersCount(), o1.getStargazersCount()));
            this.repositoriesList.setValue(repositoriesList.getValue());
        }
    }

    public MutableLiveData<Boolean> getConnectionFailureDialog() {
        return connectionFailureDialog;
    }
}
