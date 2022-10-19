package com.example.skripsi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.skripsi.Adapter.AdapterKategori;
import com.example.skripsi.Data.DataKategori;
import com.example.skripsi.Helper.Helper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KategoriActivity extends AppCompatActivity {
    ListView listView;
    TextView tvkategori;
    AlertDialog.Builder dialog;
    List<DataKategori> lists = new ArrayList<>();
    AdapterKategori adapter;
    Helper db = new Helper(this);
    Button button_simpan_kategori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
        db = new Helper(getApplicationContext());
        tvkategori = findViewById(R.id.textViewKategori);
        listView = findViewById(R.id.list_laporan);
        button_simpan_kategori = findViewById(R.id.btn_tambah_kategori);
        button_simpan_kategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KategoriActivity.this,EditKategoriActivity.class);
                startActivity(intent);
            }
        });
        adapter = new AdapterKategori(KategoriActivity.this,lists);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String nama = lists.get(position).getNama_kategori();
                final String budget = lists.get(position).getBudget_kategori();
                final CharSequence[] dialogItem = {"Edit","Hapus"};
                dialog = new AlertDialog.Builder(KategoriActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(KategoriActivity.this,EditKategoriActivity.class);
                                intent.putExtra("nama",nama);
                                intent.putExtra("budget",budget);
                                startActivity(intent);
                                break;
                            case 1:
                                db.deleteKategori(nama);
                                lists.clear();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getDataKategori();
    }
    private void getDataKategori() {
        ArrayList<HashMap<String, String>> rows = db.getKategori();
        for (int i = 0; i < rows.size(); i++) {
            String nama = rows.get(i).get("nama_kategori");
            Integer budget = Integer.valueOf(rows.get(i).get("budget_kategori"));

            DataKategori data= new DataKategori();
            data.setNama_kategori(nama);
            data.setBudget_kategori(String.valueOf(budget));
            lists.add(data);
        }
        adapter.notifyDataSetChanged();
    }
}