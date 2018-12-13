package com.kinocode.muhasabahsholat.api;

import com.kinocode.muhasabahsholat.model.Items;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("{periode}/daily.json?key=d54d16526f1584c2fbe54fda18ae52fa")
    Call<Items> getJadwalSholat(@Path("periode") String periode);
}
