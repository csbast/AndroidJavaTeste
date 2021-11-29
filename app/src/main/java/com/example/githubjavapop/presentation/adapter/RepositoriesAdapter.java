package com.example.githubjavapop.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.githubjavapop.R;
import com.example.githubjavapop.databinding.ItemRowBinding;
import com.example.githubjavapop.domain.model.Owner;
import com.example.githubjavapop.domain.model.Repositories;

import java.util.ArrayList;
import java.util.List;

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.MyViewHolder> {

    private final List<Repositories> repositoryList = new ArrayList<>();

    public RepositoriesAdapter() {
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoriesAdapter.MyViewHolder holder, int position) {
        final Repositories item = repositoryList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView repositoryName, repositoryDescription, forkCount, starCount, userName, fullName;
        public ImageView forkIcon, starIcon, userAvatar;

        public MyViewHolder(@NonNull View itemView, ItemRowBinding binding) {
            super(itemView);
            repositoryDescription = itemView.findViewById(R.id.repositoryDescriptionTextView);
            repositoryName = itemView.findViewById(R.id.repositoryNameTextView);
            forkCount = itemView.findViewById(R.id.forkCountTextView);
            starCount = itemView.findViewById(R.id.starCountTextView);
            userName = itemView.findViewById(R.id.userNameTextView);
            fullName = itemView.findViewById(R.id.fullNameTextView);
            forkIcon = itemView.findViewById(R.id.forkIconImageView);
            starIcon = itemView.findViewById(R.id.starIconImageView);
            userAvatar = itemView.findViewById(R.id.userAvatarImageView);
        }

        public void bind(Repositories item) {

            repositoryName.setText(item.getName());
            repositoryDescription.setText(item.getDescription());
            forkCount.setText(String.valueOf(item.getForksCount()));
            starCount.setText(String.valueOf(item.getStargazersCount()));
            Owner ownerObject = item.getOwner();
            userName.setText(String.valueOf(ownerObject.getLogin()));
            fullName.setText(String.valueOf(item.getFullName()));
            Glide.with(itemView.getContext()).load(item.getOwner().getAvatarUrl()).apply(RequestOptions.circleCropTransform()).into(userAvatar);

        }
    }

    public void updateList(List<Repositories> repositoriesList) {
        this.repositoryList.clear();
        this.repositoryList.addAll(repositoriesList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RepositoriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRowBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_row,
                parent,
                false
        );
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(view, binding);
    }
}
