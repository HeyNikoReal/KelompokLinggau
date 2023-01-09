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
import com.mdp.kelompoklinggau.models.challenges.Challenge;
import com.mdp.kelompoklinggau.models.challenges.Root;

import java.util.ArrayList;
import java.util.List;

public class ChallengesViewAdapter extends RecyclerView.Adapter<ChallengesViewAdapter.ViewHolder> {
    private List<Root> challengeList = new ArrayList<>();
    
    public ChallengesViewAdapter(List<Root> challengeList) {
        this.challengeList = challengeList;
    }

    @NonNull
    @Override
    public ChallengesViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.challenge_view, parent, false);
        ViewHolder holder = new ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Challenge x = challengeList.get(position).getChallenges().get(0);
        holder.tvjudul.setText(x.getName());
        holder.tvwin.setText(String.valueOf(x.getMaxWins()));
        holder.tvlose.setText(String.valueOf(x.getMaxLosses()));
        holder.tvmode.setText(x.getGameMode().getName());
        holder.tvdeksripsi.setText(x.getDescription());
        Glide.with(holder.itemView.getContext()).load(x.getIconUrl()).
                apply(new RequestOptions().override(500, 500)).
                into(holder.ivfoto2);
    }

    @Override
    public int getItemCount() {
        return challengeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvjudul, tvwin, tvlose, tvdeksripsi, tvmode;
        ImageView ivfoto2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvjudul = itemView.findViewById(R.id.tv_challenge);
            tvwin = itemView.findViewById(R.id.tv_maxwin);
            tvlose = itemView.findViewById(R.id.tv_maxlose);
            tvmode = itemView.findViewById(R.id.tv_mode);
            tvdeksripsi = itemView.findViewById(R.id.tv_deksripsi);
            ivfoto2 = itemView.findViewById(R.id.iv_foto2);
        }
    }
}
