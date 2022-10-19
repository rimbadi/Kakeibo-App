package com.example.skripsi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skripsi.Data.DataKategori;
import com.example.skripsi.Data.DataPemasukkan;
import com.example.skripsi.KategoriActivity;
import com.example.skripsi.R;

import java.util.List;

public class AdapterKategori extends BaseAdapter {
    private Activity activityKategori;
    private LayoutInflater inflaterKategori;
    private List<DataKategori> listsKategori;
    public AdapterKategori(Activity activityKategori, List<DataKategori> lists){
        this.activityKategori = activityKategori;
        this.listsKategori= lists;
    }
    @Override
    public int getCount() {
        return listsKategori.size();
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
        if (inflaterKategori == null){
            inflaterKategori = (LayoutInflater) activityKategori.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null && inflaterKategori != null){
            convertView = inflaterKategori.inflate(R.layout.list_laporan, null);
        }
        if (convertView != null){
            TextView nama_kategori = convertView.findViewById(R.id.keterangan_laporan);
            TextView budget_kategori = convertView.findViewById(R.id.uang_laporan);
            DataKategori data = listsKategori.get(position);
            nama_kategori.setText(data.getNama_kategori());
            budget_kategori.setText(data.getBudget_kategori());
        }
        return convertView;
    }
}
