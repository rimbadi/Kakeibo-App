package com.example.skripsi.Data;

public class DataPemasukkan {
    private String id_pemasukkan, keterangan_pemasukkan,  tanggal_pemasukkan, uang_pemasukkan;
    public DataPemasukkan(){
        this.id_pemasukkan = id_pemasukkan;
        this.keterangan_pemasukkan = keterangan_pemasukkan;
        this.uang_pemasukkan = uang_pemasukkan;
        this.tanggal_pemasukkan = tanggal_pemasukkan;
    }
    public String getId_pemasukkan() {
        return id_pemasukkan;
    }
    public void setId_pemasukkan(String id_pemasukkan) {
        this.id_pemasukkan = id_pemasukkan;
    }
    public String getKeterangan_pemasukkan() {
        return keterangan_pemasukkan;
    }
    public void setKeterangan_pemasukkan(String keterangan_pemasukkan) {
        this.keterangan_pemasukkan = keterangan_pemasukkan;
    }
    public String getUang_pemasukkan() {
        return uang_pemasukkan;
    }
    public void setUang_pemasukkan(String uang_pemasukkan) {
        this.uang_pemasukkan = uang_pemasukkan;
    }
    public String getTanggal_pemasukkan() {
        return tanggal_pemasukkan;
    }
    public void setTanggal_pemasukkan(String tanggal_pemasukkan) {
        this.tanggal_pemasukkan = tanggal_pemasukkan;
    }
}
