package com.example.githubjavapop.presentation.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubjavapop.R;
import com.example.githubjavapop.databinding.FragmentHomeBinding;
import com.example.githubjavapop.presentation.adapter.RepositoriesAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RepositoriesAdapter repositoriesAdapter;
    private HomeViewModel viewModel;

    public HomeFragment() {

    }

    private AlertDialog buildDialog() {

        return new AlertDialog.Builder(binding.getRoot().getContext())
                .setTitle(R.string.connection_failed)
                .setMessage(R.string.dialog_connection_error_msg)
                .setIcon(R.drawable.ic_alert_dialog)
                .create();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        setupObservers();
        setupRecyclerView();
        loadRepositories();
        return binding.getRoot();

    }

    private void setupObservers() {
        setupListObserver();
        setupLoadingObserver();
        setupConnectionFailureDialogObserver();
    }

    private void setupConnectionFailureDialogObserver() {
        viewModel.getConnectionFailureDialog().observe(getViewLifecycleOwner(), show -> {
            AlertDialog dialog = buildDialog();
            if (show) {
                dialog.show();
            } else {
                dialog.hide();
            }
        });
    }

    private void setupLoadingObserver() {
        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                showLoading();
            } else {
                hideLoading();
            }
        });
    }

    private void setupListObserver() {
        viewModel.getRepositoriesList().observe(getViewLifecycleOwner(), list -> repositoriesAdapter.updateList(list));
    }

    private void loadRepositories() {
        viewModel.loadRepositories();
    }

    private void hideLoading() {
        binding.progressBar.setVisibility(View.INVISIBLE);
    }

    private void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = binding.recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        repositoriesAdapter = new RepositoriesAdapter();
        recyclerView.setAdapter(repositoriesAdapter);
    }
}