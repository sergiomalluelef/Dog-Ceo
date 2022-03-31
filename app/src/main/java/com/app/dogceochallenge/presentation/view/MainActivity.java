package com.app.dogceochallenge.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.app.dogceochallenge.R;
import com.app.dogceochallenge.databinding.ActivityMainBinding;
import com.app.dogceochallenge.presentation.view.adapter.DogsAdapter;
import com.app.dogceochallenge.presentation.viewmodel.DogsListViewModel;
/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 * Activity principal encargado de mostrar lista de razas y subrazas.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private DogsListViewModel viewModel;
    private DogsAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(DogsListViewModel.class);
        binding.setViewModel(viewModel);
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        Toolbar toolbar = binding.bar.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        binding.bar.toolbarTitle.setText("DOG CEO");

        adapter = new DogsAdapter(this);
        adapter.setOnClickListener(name -> {
            startActivity(DogImageActivity.getIntent(this, name));
        });
        binding.rvDogs.setAdapter(adapter);
        viewModel.getNotifyList().observe(this, response -> {
            if (response != null) {
                binding.refresh.setRefreshing(false);
                adapter.updateList(response);
                viewModel.notifyList(null);
            }
        });

        binding.refresh.setOnRefreshListener(() -> {
            resetSearchView();
            viewModel.getListDogs();
        });

        viewModel.getListDogs();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Buscar");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange: " + newText);
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        searchView.setIconified(false);
        return true;
    }

    /**
     * Restablece el buscador
     */
    public void resetSearchView() {
        if(searchView != null){
            searchView.setQuery("", false);
        }
    }


}