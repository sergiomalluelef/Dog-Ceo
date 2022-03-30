package com.app.dogceochallenge.presentation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.dogceochallenge.databinding.LayoutItemDogImageBinding;
import com.app.dogceochallenge.presentation.view.viewholder.HolderDogImage;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 * Encargado de dibujar lista de imagenes asociadas a una raza.
 */
public class DogsImageAdapter extends RecyclerView.Adapter{
    private final Context context;
    private final List<String> items;
    public DogsImageAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutItemDogImageBinding binding = LayoutItemDogImageBinding.inflate(LayoutInflater.from(context),viewGroup, false);
        binding.getRoot().setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new HolderDogImage(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String url = getItem(position);
        if (url != null) {
            ((HolderDogImage) holder).bind(url);
            holder.itemView.setVisibility(View.VISIBLE);
        } else {
            holder.itemView.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    private String getItem(int position) {
        String url = null;
        try {
            url = items.get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    public void updateList(List<String> newlist) {
        items.clear();
        items.addAll(newlist);
        notifyDataSetChanged();
    }
}
