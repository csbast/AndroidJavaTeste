package com.example.githubjavapop.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubjavapop.R;
import com.example.githubjavapop.databinding.ItemRowBinding;
import com.example.githubjavapop.domain.model.Repositories;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private final List<Repositories> repositoryList;
    //private final Context context;

    public Adapter(List<Repositories> repositoryList, Context context) {
        this.repositoryList = repositoryList;
        //this.context = context;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRowBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_row,
                parent,
                false
        );
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(view, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        final Repositories repo = repositoryList.get(position);
        holder.repositoryName.setText(repo.getName());
        holder.repositoryDescription.setText(repo.getDescription());
        holder.forkCount.setText(String.valueOf(repo.getForks_count()));
        holder.starCount.setText(String.valueOf(repo.getStargazers_count()));
//        Owner ownerObject = repo.getOwnerObject();
//        holder.userName.setText(String.valueOf(ownerObject.getLogin()));
        // Glide.with(context).load(repositoryList.get(position).getOwnerObject().getAvatar_url()).apply(RequestOptions.centerCropTransform()).into(holder.userAvatar);
    }

    @Override
    public int getItemCount() {
        if (repositoryList != null) {
            return repositoryList.size();
        }
        return 0;
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
    }



    public void updateList(List<Repositories> repositoriesList) {
        this.repositoryList.addAll(repositoriesList);
        notifyDataSetChanged();
    }

}
