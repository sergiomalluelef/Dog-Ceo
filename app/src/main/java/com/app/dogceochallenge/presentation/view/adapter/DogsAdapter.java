package com.app.dogceochallenge.presentation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.dogceochallenge.domain.model.DogModel;
import com.app.dogceochallenge.databinding.LayoutItemDogBinding;
import com.app.dogceochallenge.presentation.view.viewholder.HolderDog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 * Encargado de dibujar lista de razas y subrazas disponibles.
 */
public class DogsAdapter extends RecyclerView.Adapter{
    private final Context context;
    private OnClick onClickListener;
    private final List<DogModel> items;
    public DogsAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutItemDogBinding binding = LayoutItemDogBinding.inflate(LayoutInflater.from(context),viewGroup, false);
        binding.getRoot().setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new HolderDog(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DogModel item = getItem(position);
        if (item != null) {
            ((HolderDog) holder).bind(item);
            holder.itemView.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(view -> {
                if(onClickListener != null){
                    onClickListener.click(item.getName());
                }
            });
        } else {
            holder.itemView.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    private DogModel getItem(int position) {
        DogModel model = null;
        try {
            model = items.get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    public void setOnClickListener(OnClick onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClick {
        void click(String name);
    }

    public void updateList(List<DogModel> newlist) {
        items.clear();
        items.addAll(newlist);
        notifyDataSetChanged();
    }
}
