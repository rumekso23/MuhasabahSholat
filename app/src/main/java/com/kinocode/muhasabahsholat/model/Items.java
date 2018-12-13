package com.kinocode.muhasabahsholat.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Items {
    @SerializedName("items")
    public List<JadwalSholat> items;

    public Items(List<JadwalSholat> items){
        this.items = items;
    }

    public List<JadwalSholat> getItems() {
        return items;
    }

    public void setItems(List<JadwalSholat> items) {
        this.items = items;
    }
}
