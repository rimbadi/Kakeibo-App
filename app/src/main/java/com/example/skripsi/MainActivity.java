package com.example.skripsi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.skripsi.Helper.Helper;

public class MainActivity extends AppCompatActivity {
    Button btnPemasukkan, btnPengeluaran, btnKategori, btnLaporan, btnHapus;
    AlertDialog.Builder dialog;
    Helper db = new Helper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPemasukkan = findViewById(R.id.btn_pemasukkan);
        btnPengeluaran = findViewById(R.id.btn_pengeluaran);
        btnKategori = findViewById(R.id.btn_kategori);
        btnLaporan = findViewById(R.id.btn_laporan);
        btnHapus = findViewById(R.id.btn_hapus_transaksi);
        db = new Helper(getApplicationContext());
        btnPemasukkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MainActivity.this,PemasukkanActivity.class);
                startActivity(pindah);
            }
        });
        btnPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MainActivity.this,PengeluaranActivity.class);
                startActivity(pindah);
            }
        });
        btnKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MainActivity.this,KategoriActivity.class);
                startActivity(pindah);
            }
        });
        btnLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MainActivity.this,LaporanActivity.class);
                startActivity(pindah);
            }
        });
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] dialogItem = {"Batal", "Yakin"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                break;
                            case 1:
                                db.deletetransaksi();
                                break;
                        }
                    }
                }).show();
            }
        });
    }
}