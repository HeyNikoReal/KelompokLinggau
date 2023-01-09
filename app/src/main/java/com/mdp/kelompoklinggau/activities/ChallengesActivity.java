package com.mdp.kelompoklinggau.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.mdp.kelompoklinggau.R;
import com.mdp.kelompoklinggau.adapters.ChallengesViewAdapter;
import com.mdp.kelompoklinggau.api.APIRequestData;
import com.mdp.kelompoklinggau.api.RetroServer;
import com.mdp.kelompoklinggau.models.challenges.Challenge;
import com.mdp.kelompoklinggau.models.challenges.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengesActivity extends AppCompatActivity {

    BottomNavigationView navigasibawah;
    List<Challenge> listchallenge;
    ChallengesViewAdapter adapterchallenge;
    LinearLayoutManager linearlayoutmanager;
    RecyclerView rvchallenge;
    ProgressBar pbtugas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        setTitle("Challenges");

        rvchallenge = findViewById(R.id.rv_challenge);
        navigasibawah = findViewById(R.id.bnv_navigasi_bawah);
        linearlayoutmanager = new LinearLayoutManager(ChallengesActivity.this);
        rvchallenge.setLayoutManager(linearlayoutmanager);
        pbtugas = findViewById(R.id.pb_tugas2);

        retrievechallenges();

        navigasibawah.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_cards:
                        Intent intent = new Intent(ChallengesActivity.this, CardsActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.menu_challenges:
                        return true;
                    case R.id.menu_tournaments:
                        startActivity(new Intent(ChallengesActivity.this, TournamentsActivity.class));
                        return true;
                }
                return false;
            }
        });
    }

    private void retrievechallenges() {
        pbtugas.setVisibility(View.VISIBLE);
        APIRequestData arddata = RetroServer.getRetrofit().create(APIRequestData.class);
        Call<List<Challenge>> proses = arddata.getchallenges("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImRkYjk1NDBmLTFhOWItNDg5ZS04NDkxLTk1ODNiZGZkMjYwZSIsImlhdCI6MTY3MzE1NzE4Niwic3ViIjoiZGV2ZWxvcGVyLzFlYWUzYWQyLTE5ZmMtNzRiYi1iNzAyLTk3Yzk5MTU4NDBmMSIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyIxNzUuMTU4LjM3LjIwOCJdLCJ0eXBlIjoiY2xpZW50In1dfQ.LzYBuCMsbunQH0NaY0M6u2dMoA4TFyJA2gXl0hdYPlhv8sU7TpBlRnsJfYknfaQbhUzNpeGAekiHlJ9i0QhTTg");

        proses.enqueue(new Callback<List<Challenge>>() {
            @Override
            public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                listchallenge = response.body();
                adapterchallenge = new ChallengesViewAdapter(listchallenge);
                rvchallenge.setAdapter(adapterchallenge);
                pbtugas.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Challenge>> call, Throwable t) {
                Toast.makeText(ChallengesActivity.this, "Gagal Menghubungi Server , " + t.getMessage(), Toast.LENGTH_SHORT).show();
                pbtugas.setVisibility(View.INVISIBLE);
            }
        });


//        proses.enqueue(new Callback<List<Root>>() {
//            @Override
//            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
//                listchallenge = response.body();
//                adapterchallenge = new ChallengesViewAdapter(listchallenge);
//                rvchallenge.setAdapter(adapterchallenge);
//                pbtugas.setVisibility(View.INVISIBLE);
//                Toast.makeText(ChallengesActivity.this, "RESPONSE, " + response.body(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<Root>> call, Throwable t) {
//                Toast.makeText(ChallengesActivity.this, "Gagal Menghubungi Server , " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("CEK : ", t.getMessage());
//                pbtugas.setVisibility(View.INVISIBLE);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opsi_atas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_location) {
            Uri location = Uri.parse("geo:0,0?q=Universitas Multi Data Palembang");
            Intent intent = new Intent(Intent.ACTION_VIEW, location);
            startActivity(intent);

        } else if (item.getItemId() == R.id.menu_help) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel: +6281271590161"));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}