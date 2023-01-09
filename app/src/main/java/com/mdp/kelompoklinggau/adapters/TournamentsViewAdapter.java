package com.mdp.kelompoklinggau.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mdp.kelompoklinggau.R;
import com.mdp.kelompoklinggau.models.tournaments.Item;

import java.util.ArrayList;
import java.util.List;

public class TournamentsViewAdapter extends RecyclerView.Adapter<TournamentsViewAdapter.ViewHolder> {
    private List<Item> itemList = new ArrayList<>();

    public TournamentsViewAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public TournamentsViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tournament_view, parent, false);
        ViewHolder holder = new ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TournamentsViewAdapter.ViewHolder holder, int position) {
        Item s = itemList.get(position);
        holder.tvjudul.setText(s.getTitle());
        holder.tvtour.setText(String.valueOf(s.getTournamentLevel()));
        holder.tvrank.setText(String.valueOf(s.getMaxTopRewardRank()));
        holder.tvexp.setText(String.valueOf(s.getMinExpLevel()));
        holder.tvlose.setText(String.valueOf(s.getMaxLosses()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvjudul, tvexp, tvtour, tvrank, tvlose;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvjudul = itemView.findViewById(R.id.tv_tournament);
            tvexp = itemView.findViewById(R.id.tv_exp);
            tvlose = itemView.findViewById(R.id.tv_lose);
            tvrank = itemView.findViewById(R.id.tv_rank);
            tvtour = itemView.findViewById(R.id.tv_tourlevel);
        }
    }
}
