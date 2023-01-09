package com.mdp.kelompoklinggau.api;

import com.mdp.kelompoklinggau.models.challenges.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface APIRequestData {
    @GET("cards")
    Call<com.mdp.kelompoklinggau.models.cards.Root> getcards(@Header("Authorization") String token);

    @GET("challenges")
    Call<List<Root>> getchallenges(@Header("Authorization") String token);

    @GET("globaltournaments")
    Call<com.mdp.kelompoklinggau.models.tournaments.Root> gettournaments(@Header("Authorization") String token);
}
