package com.example.githubjavapop;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
        MenuItem searchItem =  menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnCloseListener(() -> {
            Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast. LENGTH_SHORT).show();
            return false;
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                homeViewModel.filterList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int itemId = item.getItemId();
        if (itemId == R.id.menuSortByNameItem) {
            homeViewModel.sortByName();
            return true;
        } else if (itemId == R.id.menuSortByStarsItem) {
            homeViewModel.sortByStars();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}