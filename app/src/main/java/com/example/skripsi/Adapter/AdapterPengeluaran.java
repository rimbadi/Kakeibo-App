package com.example.skripsi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skripsi.Data.DataPemasukkan;
import com.example.skripsi.Data.DataPengeluaran;
import com.example.skripsi.R;

import java.util.List;

public class AdapterPengeluaran extends BaseAdapter {
    private Activity activityPengeluaran;
    private LayoutInflater inflaterPengeluaran;
    private List<DataPengeluaran> listPengeluaran;
    public AdapterPengeluaran(Activity activityPengeluaran, List<DataPengeluaran> listsPengeluaran) {
        this.activityPengeluaran = activityPengeluaran;
        this.listPengeluaran = listsPengeluaran;
    }
    @Override
    public int getCount() {
        return listPengeluaran.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflaterPengeluaran == null) {
            inflaterPengeluaran = (LayoutInflater) activityPengeluaran.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null && inflaterPengeluaran != null) {
            convertView = inflaterPengeluaran.inflate(R.layout.list_laporan, null);
        }
        if (convertView != null) {
            TextView katergori_laporan = convertView.findViewById(R.id.kategoriTransaksi);
            TextView keterangan_laporan = convertView.findViewById(R.id.keterangan_laporan);
            TextView uang_laporan = convertView.findViewById(R.id.uang_laporan);
            TextView tanggal_laporan = convertView.findViewById(R.id.tanggal_laporan);
            DataPengeluaran data = listPengeluaran.get(position);
            katergori_laporan.setText(data.getKategori_pengeluaran());
            keterangan_laporan.setText(data.getKeterangan_pengeluaran());
            uang_laporan.setText(data.getUang_pengeluaran());
            tanggal_laporan.setText(data.getTanggal_pengeluaran());
        }
        return convertView;
    }
}