package com.example.githubjavapop;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.githubjavapop.databinding.ActivityMainBinding;
import com.example.githubjavapop.presentation.home.HomeViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
////        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        toolbar = binding.toolbar;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
         }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int itemId = item.getItemId();
        if (itemId == R.id.menuFilterItem) {
            return true;
        } else if (itemId == R.id.menuSortByNameItem) {
            homeViewModel.sortByName();
            return true;
        } else if (itemId == R.id.menuSortByStarsItem) {
            homeViewModel.sortByStars();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}