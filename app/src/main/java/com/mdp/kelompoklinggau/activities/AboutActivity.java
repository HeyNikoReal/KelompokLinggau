package com.mdp.kelompoklinggau.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mdp.kelompoklinggau.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setTitle("About");
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