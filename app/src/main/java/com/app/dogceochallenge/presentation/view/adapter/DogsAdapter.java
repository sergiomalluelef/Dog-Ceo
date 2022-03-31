package com.app.dogceochallenge.presentation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.dogceochallenge.domain.model.DogModel;
import com.app.dogceochallenge.databinding.LayoutItemDogBinding;
import com.app.dogceochallenge.presentation.view.viewholder.HolderDog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sergio Muñoz
 * on 29-03-2022.
 * Encargado de dibujar lista de razas y subrazas disponibles.
 */
public class DogsAdapter extends RecyclerView.Adapter implements Filterable {
    private final Context context;
    private OnClick onClickListener;
    private final List<DogModel> items;
    private List<DogModel> itemsFilter;
    private Filter mFilter;
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

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    List<DogModel> FilteredArrList = new ArrayList<>();
                    if (itemsFilter == null || itemsFilter.isEmpty()) {
                        itemsFilter = new ArrayList<>(items);
                    }
                    if (constraint == null) {
                        results.count = itemsFilter.size();
                        results.values = itemsFilter;
                    } else {
                        Locale locale = Locale.getDefault();
                        String sConstraint = constraint.toString().trim().toLowerCase(locale);
                        if (sConstraint.length() == 0) {
                            results.count = itemsFilter.size();
                            results.values = itemsFilter;
                        } else {
                            for (int i = 0; i < itemsFilter.size(); i++) {
                                DogModel model = itemsFilter.get(i);
                                String name = model.getName();
                                String subName = model.getSubName();
                                if (name.toLowerCase(locale).contains(sConstraint)
                                        || subName.toLowerCase(locale).contains(sConstraint)
                                ) {
                                    FilteredArrList.add(model);
                                }
                            }
                            results.count = FilteredArrList.size();
                            results.values = FilteredArrList;

                        }
                    }
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    items.clear();
                    items.addAll((ArrayList<DogModel>) results.values);
                    notifyDataSetChanged();
                }
            };
        }
        return mFilter;
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
