package com.kinocode.muhasabahsholat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kinocode.muhasabahsholat.api.ApiClient;
import com.kinocode.muhasabahsholat.api.ApiInterface;
import com.kinocode.muhasabahsholat.db.DBHelper;
import com.kinocode.muhasabahsholat.model.Items;
import com.kinocode.muhasabahsholat.model.JadwalSholat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvNamaDaerah, tvTanggal, tvSubuhTime, tvTerbitTime, tvDzuhurTime, tvAsharTime, tvMaghribTime, tvIsyaTime;

    @BindView(R.id.tv_subuh) TextView tvSubuh;
    @BindView(R.id.tv_dzuhur) TextView tvDzuhur;
    @BindView(R.id.tv_ashar) TextView tvAshar;
    @BindView(R.id.tv_maghrib) TextView tvMaghrib;
    @BindView(R.id.tv_Isya) TextView tvIsya;

    @BindView(R.id.rdg_subuh)RadioGroup rdgSubuh;
    @BindView(R.id.rdg_dzuhur) RadioGroup rdgDzuhur;
    @BindView(R.id.rdg_ashar) RadioGroup rdgAshar;
    @BindView(R.id.rdg_maghrib) RadioGroup rdgMaghrib;
    @BindView(R.id.rdg_isya) RadioGroup rdgIsya;

    @BindView(R.id.rbt_j_subuh) RadioButton rbtJsubuh;
    @BindView(R.id.rbt_j_dzuhur) RadioButton rbtJdzuhur;
    @BindView(R.id.rbt_j_ashar) RadioButton rbtJashar;
    @BindView(R.id.rbt_j_maghrib) RadioButton rbtJmaghrib;
    @BindView(R.id.rbt_j_isya) RadioButton rbtJisya;

    @BindView(R.id.rbt_s_subuh) RadioButton rbtSsubuh;
    @BindView(R.id.rbt_s_dzuhur) RadioButton rbtSdzuhur;
    @BindView(R.id.rbt_s_ashar) RadioButton rbtSashar;
    @BindView(R.id.rbt_s_maghrib) RadioButton rbtSmaghrib;
    @BindView(R.id.rbt_s_isya) RadioButton rbtSisya;

    @BindView(R.id.rbt_ts_subuh) RadioButton rbtTSsubuh;
    @BindView(R.id.rbt_ts_dzuhur) RadioButton rbtTSdzuhur;
    @BindView(R.id.rbt_ts_ashar) RadioButton rbtTSashar;
    @BindView(R.id.rbt_ts_maghrib) RadioButton rbtTSmaghrib;
    @BindView(R.id.rbt_ts_isya) RadioButton rbtTSisya;

    private String tanggal, subuh, dzuhur, ashar, maghrib, isya;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvNamaDaerah = (TextView) findViewById(R.id.tv_nama_daerah);
        tvTanggal = (TextView) findViewById(R.id.tv_tanggal);
        tvSubuhTime = (TextView) findViewById(R.id.tv_subuh_time);
        tvTerbitTime = (TextView) findViewById(R.id.tv_terbit_time);
        tvDzuhurTime = (TextView) findViewById(R.id.tv_dzuhur_time);
        tvAsharTime = (TextView) findViewById(R.id.tv_ashar_time);
        tvMaghribTime = (TextView) findViewById(R.id.tv_maghrib_time);
        tvIsyaTime = (TextView) findViewById(R.id.tv_isya_time);

        tanggal = tvTanggal.getText().toString();
        subuh = tvSubuh.getText().toString();
        dzuhur = tvDzuhur.getText().toString();
        ashar = tvAshar.getText().toString();
        maghrib = tvMaghrib.getText().toString();
        isya = tvIsya.getText().toString();

        String jamaah = "Jama'ah";
        String sendiri = "Sendiri";
        String tidakSholat = "Tidak Solat";

        rbtJsubuh.setText(jamaah);
        rbtJdzuhur.setText(jamaah);
        rbtJashar.setText(jamaah);
        rbtJmaghrib.setText(jamaah);
        rbtJisya.setText(jamaah);

        rbtSsubuh.setText(sendiri);
        rbtSdzuhur.setText(sendiri);
        rbtSashar.setText(sendiri);
        rbtSmaghrib.setText(sendiri);
        rbtSisya.setText(sendiri);

        rbtTSsubuh.setText(tidakSholat);
        rbtTSdzuhur.setText(tidakSholat);
        rbtTSashar.setText(tidakSholat);
        rbtTSmaghrib.setText(tidakSholat);
        rbtTSisya.setText(tidakSholat);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_grafik){
            startActivity(new Intent(this, GrafikActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    private void saveDataEvaluasi(){
        DBHelper dbHelper = new DBHelper(this);

        int selectedRdgSubuh = rdgSubuh.getCheckedRadioButtonId();
        int selectedRdgDzuhur = rdgDzuhur.getCheckedRadioButtonId();
        int selectedRdgAshar = rdgAshar.getCheckedRadioButtonId();
        int selectedRdgMaghrib = rdgMaghrib.getCheckedRadioButtonId();
        int selectedRdgIsya = rdgIsya.getCheckedRadioButtonId();

        if (selectedRdgSubuh == rbtJsubuh.getId()){
            dbHelper.insertData(tanggal, subuh, rbtJsubuh.getText().toString());
        }else if(selectedRdgSubuh == rbtSsubuh.getId()){
            dbHelper.insertData(tanggal, subuh, rbtSsubuh.getText().toString());
        }else {
            dbHelper.insertData(tanggal, subuh, rbtTSsubuh.getText().toString());
        }

        if (selectedRdgDzuhur == rbtJdzuhur.getId()){
            dbHelper.insertData(tanggal, subuh, rbtJdzuhur.getText().toString());
        }else if(selectedRdgDzuhur == rbtSdzuhur.getId()){
            dbHelper.insertData(tanggal, subuh, rbtSdzuhur.getText().toString());
        }else {
            dbHelper.insertData(tanggal, subuh, rbtTSdzuhur.getText().toString());
        }

        if (selectedRdgAshar == rbtJashar.getId()){
            dbHelper.insertData(tanggal, subuh, rbtJashar.getText().toString());
        }else if(selectedRdgAshar == rbtSashar.getId()){
            dbHelper.insertData(tanggal, subuh, rbtSashar.getText().toString());
        }else {
            dbHelper.insertData(tanggal, subuh, rbtTSashar.getText().toString());
        }

        if (selectedRdgMaghrib == rbtJmaghrib.getId()){
            dbHelper.insertData(tanggal, subuh, rbtJmaghrib.getText().toString());
        }else if(selectedRdgMaghrib == rbtSmaghrib.getId()){
            dbHelper.insertData(tanggal, subuh, rbtSmaghrib.getText().toString());
        }else {
            dbHelper.insertData(tanggal, subuh, rbtTSmaghrib.getText().toString());
        }

        if (selectedRdgIsya == rbtJisya.getId()){
            dbHelper.insertData(tanggal, subuh, rbtJisya.getText().toString());
        }else if(selectedRdgIsya == rbtSisya.getId()){
            dbHelper.insertData(tanggal, subuh, rbtSisya.getText().toString());
        }else {
            dbHelper.insertData(tanggal, subuh, rbtTSisya.getText().toString());
        }

    }

    private void updateDataEvaluasi(){

    }
}
