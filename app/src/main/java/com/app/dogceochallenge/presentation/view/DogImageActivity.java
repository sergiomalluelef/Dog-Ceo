package com.app.dogceochallenge.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.app.dogceochallenge.databinding.ActivityDogImageBinding;
import com.app.dogceochallenge.presentation.view.adapter.DogsImageAdapter;
import com.app.dogceochallenge.presentation.viewmodel.DogsListImageViewModel;
/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 * Activity principal encargado de mostrar imagenes de razas y subrazas.
 */
public class DogImageActivity extends AppCompatActivity {

    private static final String URL = "URL";
    private ActivityDogImageBinding binding;
    private DogsListImageViewModel viewModel;
    private DogsImageAdapter adapter;

    public static Intent getIntent(Context context, String url) {
        Intent intent = new Intent(context, DogImageActivity.class);
        Bundle args = new Bundle();
        args.putString(URL, url);
        intent.putExtras(args);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDogImageBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(DogsListImageViewModel.class);
        binding.setViewModel(viewModel);
        setContentView(binding.getRoot());
        Bundle args = getIntent().getExtras();
        if (args != null) {
            viewModel.setName(args.getString(URL));
        }
        init();
    }

    private void init() {
        Toolbar toolbar = binding.bar.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        binding.bar.toolbarTitle.setText(viewModel.getName().toUpperCase());
        adapter = new DogsImageAdapter(this);
        binding.rvDogs.setAdapter(adapter);
        viewModel.getNotifyList().observe(this, response -> {
            if (response != null) {
                binding.refresh.setRefreshing(false);
                adapter.updateList(response);
                viewModel.notifyList(null);
            }
        });
        binding.refresh.setOnRefreshListener(() -> {
            viewModel.getListDogsImage();
        });
        viewModel.getListDogsImage();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}