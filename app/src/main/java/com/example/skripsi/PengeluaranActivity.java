package com.example.skripsi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.skripsi.Adapter.AdapterPengeluaran;
import com.example.skripsi.Data.DataPengeluaran;
import com.example.skripsi.Helper.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PengeluaranActivity extends AppCompatActivity {
    ListView listView;
    TextView tvKategori;
    AlertDialog.Builder dialog;
    List<DataPengeluaran> listsPengeluaran= new ArrayList<>();
    AdapterPengeluaran adapter;
    Helper db = new Helper(this);
    Button btn_tambah;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengeluaran);
        db = new Helper(getApplicationContext());
        btn_tambah = findViewById(R.id.btn_tambah_pengeluaran);
        tvKategori = findViewById(R.id.textViewKategori);
        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PengeluaranActivity.this,EditPengeluaranActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.list_laporan);
        adapter = new AdapterPengeluaran(PengeluaranActivity.this,listsPengeluaran);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long i) {
                final String id = listsPengeluaran.get(position).getId_pengeluaran();
                final String kategori = listsPengeluaran.get(position).getKategori_pengeluaran();
                final String keterangan = listsPengeluaran.get(position).getKeterangan_pengeluaran();
                final String uang = listsPengeluaran.get(position).getUang_pengeluaran();
                final String tanggal = listsPengeluaran.get(position).getTanggal_pengeluaran();
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(PengeluaranActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(PengeluaranActivity.this, EditPengeluaranActivity.class);
                                intent.putExtra("id_pengeluaran", id);
                                intent.putExtra("nama_kategori",kategori);
                                intent.putExtra("keterangan_pengeluaran", keterangan);
                                intent.putExtra("uang_pengeluaran", uang);
                                intent.putExtra("tanggal_pengeluaran", tanggal);
                                startActivity(intent);
                                break;
                            case 1:
                                db.deletePengeluaran(Integer.parseInt(id));
                                listsPengeluaran.clear();
                                getDataPengeluaran();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getDataPengeluaran();
    }
    private void getDataPengeluaran() {
        ArrayList<HashMap<String, String>> rows = db.getPengeluaran();
        for (int i = 0; i < rows.size(); i++) {
            String id = rows.get(i).get("id_pengeluaran");
            String kategori = rows.get(i).get("nama_kategori");
            String keterangan = rows.get(i).get("keterangan_pengeluaran");
            Integer uang = Integer.valueOf(rows.get(i).get("uang_pengeluaran"));
            String tanggal = rows.get(i).get("tanggal_pengeluaran");
            DataPengeluaran data = new DataPengeluaran();
            data.setId_pengeluaran(id);
            data.setKategori_pengeluaran(kategori);
            data.setKeterangan_pengeluaran(keterangan);
            data.setUang_pengeluaran(String.valueOf(uang));
            data.setTanggal_pengeluaran(tanggal);
            listsPengeluaran.add(data);
        }
        adapter.notifyDataSetChanged();
    }
}

