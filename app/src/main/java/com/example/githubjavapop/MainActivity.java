package com.example.githubjavapop;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.githubjavapop.databinding.ActivityMainBinding;
import com.example.githubjavapop.presentation.home.HomeViewModel;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActivityMainBinding binding;
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        switch (item.getItemId()) {
            case R.id.menuFilterItem:
                return true;
            case R.id.menuSortByNameItem:
                return true;
            case R.id.menuSortByStarsItem:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}