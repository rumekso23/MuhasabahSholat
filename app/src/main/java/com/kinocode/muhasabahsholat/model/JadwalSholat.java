package com.kinocode.muhasabahsholat.model;

import com.google.gson.annotations.SerializedName;

public class JadwalSholat {

    @SerializedName("date_for")
    public String tanggal;
    @SerializedName("fajr")
    public String subuh;
    @SerializedName("shurooq")
    public String terbit;
    @SerializedName("dhuhr")
    public String dzuhur;
    @SerializedName("asr")
    public String ashar;
    @SerializedName("maghrib")
    public String maghrib;
    @SerializedName("isha")
    public String isya;

    public String getTanggal() {
        return tanggal;
    }

    public String getSubuh() {
        return subuh;
    }

    public String getTerbit() {
        return terbit;
    }

    public String getDzuhur() {
        return dzuhur;
    }

    public String getAshar() {
        return ashar;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public String getIsya() {
        return isya;
    }
}
