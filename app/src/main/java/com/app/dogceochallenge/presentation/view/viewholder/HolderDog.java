package com.app.dogceochallenge.presentation.view.viewholder;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.dogceochallenge.domain.model.DogModel;
import com.app.dogceochallenge.databinding.LayoutItemDogBinding;

public class HolderDog extends RecyclerView.ViewHolder {
    private LayoutItemDogBinding binding;

    public HolderDog(@NonNull LayoutItemDogBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(DogModel model) {
        binding.setModel(model);
        binding.executePendingBindings();
    }
}
