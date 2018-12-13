package com.kinocode.muhasabahsholat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kinocode.muhasabahsholat.api.ApiClient;
import com.kinocode.muhasabahsholat.api.ApiInterface;
import com.kinocode.muhasabahsholat.model.Items;
import com.kinocode.muhasabahsholat.model.JadwalSholat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvNamaDaerah, tvTanggal, tvSubuhTime, tvTerbitTime, tvDzuhurTime, tvAsharTime, tvMaghribTime, tvIsyaTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNamaDaerah = (TextView) findViewById(R.id.tv_nama_daerah);
        tvTanggal = (TextView) findViewById(R.id.tv_tanggal);
        tvSubuhTime = (TextView) findViewById(R.id.tv_subuh_time);
        tvTerbitTime = (TextView) findViewById(R.id.tv_terbit_time);
        tvDzuhurTime = (TextView) findViewById(R.id.tv_dzuhur_time);
        tvAsharTime = (TextView) findViewById(R.id.tv_ashar_time);
        tvMaghribTime = (TextView) findViewById(R.id.tv_maghrib_time);
        tvIsyaTime = (TextView) findViewById(R.id.tv_isya_time);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Items> call = apiInterface.getJadwalSholat("yogyakarta");
        call.enqueue(new Callback<Items>() {
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                Log.d("MainActivity ","Error: "+response.body().getItems());
                List<JadwalSholat> jadwalSholat = response.body().getItems();
                JadwalSholat(jadwalSholat);

            }

            @Override
            public void onFailure(Call<Items> call, Throwable t) {
                Log.d("Error Load ", "" + t.getMessage());
            }
        });
    }

    private void JadwalSholat(List<JadwalSholat> jadwalSholat){
        for (JadwalSholat time : jadwalSholat){
            tvNamaDaerah.setText("Yogyakarta");
            tvTanggal.setText(time.getTanggal());
            tvSubuhTime.setText(time.getSubuh());
            tvTerbitTime.setText(time.getTerbit());
            tvDzuhurTime.setText(time.getDzuhur());
            tvAsharTime.setText(time.getAshar());
            tvMaghribTime.setText(time.getMaghrib());
            tvIsyaTime.setText(time.getIsya());
        }
    }
}
