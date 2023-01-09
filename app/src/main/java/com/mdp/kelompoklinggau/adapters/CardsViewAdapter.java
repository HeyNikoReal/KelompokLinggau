package com.mdp.kelompoklinggau.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mdp.kelompoklinggau.R;
import com.mdp.kelompoklinggau.models.cards.Item;

import java.util.ArrayList;
import java.util.List;

public class CardsViewAdapter extends RecyclerView.Adapter<CardsViewAdapter.ViewHolder> {
    private List<Item> itemList = new ArrayList<>();

    public CardsViewAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public CardsViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        ViewHolder holder = new ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item c = itemList.get(position);
        holder.tvnama.setText(c.getName());
        holder.tvlevel.setText(String.valueOf(c.getMaxLevel()));
        Glide.with(holder.itemView.getContext()).load(c.getIconUrls().getMedium()).
                apply(new RequestOptions().override(500, 500)).
                into(holder.ivfoto);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvnama, tvlevel;
        ImageView ivfoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvnama = itemView.findViewById(R.id.tv_nama);
            tvlevel = itemView.findViewById(R.id.tv_level);
            ivfoto = itemView.findViewById(R.id.iv_foto);
        }
    }
}
