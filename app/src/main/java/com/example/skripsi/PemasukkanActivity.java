package com.example.skripsi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.skripsi.Adapter.AdapterPemasukkan;
import com.example.skripsi.Data.DataPemasukkan;
import com.example.skripsi.Helper.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PemasukkanActivity extends AppCompatActivity {
    ListView listView;
    TextView tvKategori;
    AlertDialog.Builder dialog;
    List<DataPemasukkan> lists = new ArrayList<>();
    AdapterPemasukkan adapter;
    Helper db = new Helper(this);
    Button btn_tambah;
    String total;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukkan);
        db = new Helper(getApplicationContext());
        tvKategori = findViewById(R.id.textViewKategori);
        btn_tambah = findViewById(R.id.btn_tambah_pemasukkan);
        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PemasukkanActivity.this, EditPemasukkanActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.list_laporan);
        adapter = new AdapterPemasukkan(PemasukkanActivity.this, lists);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long i) {
                final String id = lists.get(position).getId_pemasukkan();
                final String keterangan = lists.get(position).getKeterangan_pemasukkan();
                final String uang = lists.get(position).getUang_pemasukkan();
                final String tanggal = lists.get(position).getTanggal_pemasukkan();
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(PemasukkanActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(PemasukkanActivity.this, EditPemasukkanActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("keterangan", keterangan);
                                intent.putExtra("uang", uang);
                                intent.putExtra("tanggal", tanggal);
                                startActivity(intent);
                                break;
                            case 1:
                                db.deletePemasukkan(Integer.parseInt(id));
                                lists.clear();
                                getDataPemasukkan();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getDataPemasukkan();
    }
    private void getDataPemasukkan() {
        ArrayList<HashMap<String, String>> rows = db.getPemasukkan();
        for (int i = 0; i < rows.size(); i++) {
            String id = rows.get(i).get("id_pemasukkan");
            String keterangan = rows.get(i).get("keterangan_pemasukkan");
            Integer uang = Integer.valueOf((rows.get(i).get("uang_pemasukkan")));
            String tanggal = rows.get(i).get("tanggal_pemasukkan");

            DataPemasukkan data= new DataPemasukkan();
            data.setId_pemasukkan(id);
            data.setKeterangan_pemasukkan(keterangan);
            data.setUang_pemasukkan(String.valueOf(uang));
            data.setTanggal_pemasukkan(tanggal);
            lists.add(data);
        }
        adapter.notifyDataSetChanged();
    }
}

