package com.example.skripsi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skripsi.Data.DataPemasukkan;
import com.example.skripsi.R;

import java.util.List;

public class AdapterPemasukkan extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataPemasukkan> listsPemasukkan;
    public AdapterPemasukkan(Activity activity, List<DataPemasukkan> lists){
        this.activity = activity;
        this.listsPemasukkan = lists;
    }
    @Override
    public int getCount() {
        return listsPemasukkan.size();
    }
    @Override
    public Object getItem(int position) {
        return listsPemasukkan.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null && inflater != null){
            convertView = inflater.inflate(R.layout.list_laporan, null);
        }
        if (convertView != null){
            TextView keterangan_laporan = convertView.findViewById(R.id.keterangan_laporan);
            TextView uang_laporan = convertView.findViewById(R.id.uang_laporan);
            TextView tanggal_laporan = convertView.findViewById(R.id.tanggal_laporan);
            DataPemasukkan data = listsPemasukkan.get(position);
            keterangan_laporan.setText(data.getKeterangan_pemasukkan());
            uang_laporan.setText(data.getUang_pemasukkan());
            tanggal_laporan.setText(data.getTanggal_pemasukkan());
        }
        return convertView;
    }
}
