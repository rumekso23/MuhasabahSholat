package com.kinocode.muhasabahsholat.model;

import java.util.Date;

public class EvaluasiSholat {

    private long id;
    private String tanggal;
    private String sholat;
    private String status;

    public EvaluasiSholat(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getSholat() {
        return sholat;
    }

    public void setSholat(String sholat) {
        this.sholat = sholat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
