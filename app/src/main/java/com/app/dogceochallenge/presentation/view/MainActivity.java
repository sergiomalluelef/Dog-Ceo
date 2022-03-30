package com.app.dogceochallenge.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.app.dogceochallenge.databinding.ActivityMainBinding;
import com.app.dogceochallenge.presentation.view.adapter.DogsAdapter;
import com.app.dogceochallenge.presentation.viewmodel.DogsListViewModel;
/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 * Activity principal encargado de mostrar lista de razas y subrazas.
 */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private DogsListViewModel viewModel;
    private DogsAdapter adapter;

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
            viewModel.getListDogs();
        });

        viewModel.getListDogs();

    }
}