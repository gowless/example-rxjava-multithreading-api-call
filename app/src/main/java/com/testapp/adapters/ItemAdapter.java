package com.testapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.testapp.databinding.ItemBinding;
import com.testapp.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyAdapter> {
    @Override
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);

    }

    public ItemAdapter(List<Item> data, Context context) {
        this.data = data;
        this.context = context;
    }


    public void setData(List<Item> data) {
        this.data = data;
    }

    public List<Item> data;
    Context context;
    private LayoutInflater layoutInflater;



    @NonNull
    @Override
    public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ItemBinding itemBinding = ItemBinding.inflate(layoutInflater, parent, false);
        return new MyAdapter(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter holder, int position) {
        holder.bind(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyAdapter extends RecyclerView.ViewHolder {
        private ItemBinding itemBinding;
        public MyAdapter(@NonNull ItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Item item){
            this.itemBinding.setViewModel(item);
        }

    }
}
