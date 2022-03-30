package com.app.dogceochallenge.presentation.view.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.dogceochallenge.R;
import com.app.dogceochallenge.databinding.LayoutItemDogImageBinding;
import com.bumptech.glide.Glide;

public class HolderDogImage extends RecyclerView.ViewHolder {
    private LayoutItemDogImageBinding binding;

    public HolderDogImage(@NonNull LayoutItemDogImageBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(String url) {
        Glide.with(binding.getRoot())
                .load(url)
                .placeholder(R.drawable.ic_pets)
                .into(binding.imgImage);
    }
}
