package com.mdp.kelompoklinggau.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.mdp.kelompoklinggau.R;
import com.mdp.kelompoklinggau.adapters.TournamentsViewAdapter;
import com.mdp.kelompoklinggau.api.APIRequestData;
import com.mdp.kelompoklinggau.api.RetroServer;
import com.mdp.kelompoklinggau.models.tournaments.Item;
import com.mdp.kelompoklinggau.models.tournaments.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TournamentsActivity extends AppCompatActivity {

    BottomNavigationView navigasibawah;
    RecyclerView rvtournament;
    LinearLayoutManager linearlayoutmanager;
    List<Item> listtournament;
    TournamentsViewAdapter adaptertournament;
    ProgressBar pbtugas;
    TextView teks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournaments);

        setTitle("Tournaments");

        navigasibawah = findViewById(R.id.bnv_navigasi_bawah);
        rvtournament = findViewById(R.id.rv_tournament);
        linearlayoutmanager = new LinearLayoutManager(TournamentsActivity.this);
        rvtournament.setLayoutManager(linearlayoutmanager);
        pbtugas = findViewById(R.id.pb_tugas3);
        teks = findViewById(R.id.teks3);

        retrievetournament();

        navigasibawah.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_cards:
                        startActivity(new Intent(TournamentsActivity.this, CardsActivity.class));
                        return true;
                    case R.id.menu_challenges:
                        startActivity(new Intent(TournamentsActivity.this, ChallengesActivity.class));
                        return true;
                    case R.id.menu_tournaments:
                        return true;
                }
                return false;
            }
        });
    }

    private void retrievetournament() {
        pbtugas.setVisibility(View.VISIBLE);
        teks.setVisibility(View.VISIBLE);
        APIRequestData arddata = RetroServer.getRetrofit().create(APIRequestData.class);
        Call<Root> retri = arddata.gettournaments("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImZkZjQ5YzFlLTU0MGQtNDFkMi05NTgzLWNjMTBhYTA0ZWU3MyIsImlhdCI6MTY3MzI3Njc1MSwic3ViIjoiZGV2ZWxvcGVyLzFlYWUzYWQyLTE5ZmMtNzRiYi1iNzAyLTk3Yzk5MTU4NDBmMSIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyIxNzUuMTU4LjM3LjIwOCIsIjE3NS4xNTguMzcuMTk4Il0sInR5cGUiOiJjbGllbnQifV19.vsmrnWMfp9H8HeDFnG76b3P3F1kTlM4yXlzcABymIQ1EMYazc3j6_NBwDGeIjC3nelrmuCUZXY0qV-JzL6C8ZQ");
        retri.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                listtournament = response.body().getItems();
                adaptertournament = new TournamentsViewAdapter(listtournament);
                rvtournament.setAdapter(adaptertournament);
                pbtugas.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(TournamentsActivity.this, "Gagal Menghubungi Server , " + t.getMessage(), Toast.LENGTH_SHORT).show();
                pbtugas.setVisibility(View.INVISIBLE);
            }
        });
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