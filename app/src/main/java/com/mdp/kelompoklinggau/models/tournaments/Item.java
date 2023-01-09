package com.mdp.kelompoklinggau.models.tournaments;

public class Item {
    public String title;
    public int maxTopRewardRank;
    public int maxLosses;
    public int minExpLevel;
    public int tournamentLevel;

    public String getTitle() {
        return title;
    }

    public int getMaxTopRewardRank() {
        return maxTopRewardRank;
    }

    public int getMaxLosses() {
        return maxLosses;
    }

    public int getMinExpLevel() {
        return minExpLevel;
    }

    public int getTournamentLevel() {
        return tournamentLevel;
    }
}
