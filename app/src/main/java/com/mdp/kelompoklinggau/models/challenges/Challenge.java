package com.mdp.kelompoklinggau.models.challenges;

public class Challenge {
    public String name;
    public String description;
    public int maxLosses;
    public GameMode gameMode;
    public int maxWins;
    public String iconUrl;
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxLosses() {
        return maxLosses;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public int getMaxWins() {
        return maxWins;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
