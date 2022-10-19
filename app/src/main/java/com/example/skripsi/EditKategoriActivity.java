package com.example.skripsi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.skripsi.Data.DataKategori;
import com.example.skripsi.Helper.Helper;
import java.util.ArrayList;
import java.util.List;

public class EditKategoriActivity extends AppCompatActivity {
    Helper db = new Helper(this);
    TextView tvNamaKategori;
    Button btn_simpan_budget;
    Spinner spinnerKategori;
    String nama_kategori, budget_kategori;
    EditText budgetKategori;
    String[] pilihanKategori = {"Kebutuhan Hidup","Tambahan","Keinginan","Kultural","Tabungan"};
    List<DataKategori> lists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kategori);
        db = new Helper(getApplicationContext());
        spinnerKategori = findViewById(R.id.spinner_ketegori);
        btn_simpan_budget = findViewById(R.id.button_simpan_budget);
        tvNamaKategori = findViewById(R.id.textViewNamaKategori);
        budgetKategori = findViewById(R.id.input_budget_kategori);
        ArrayAdapter adapter = new ArrayAdapter(EditKategoriActivity.this, android.R.layout.simple_spinner_dropdown_item, pilihanKategori);
        spinnerKategori.setAdapter(adapter);
        nama_kategori = getIntent().getStringExtra("nama");
        budget_kategori= getIntent().getStringExtra("budget");
        if (nama_kategori == null|| nama_kategori.equals("")){
            setTitle("Tambah Kategori");
        }else {
            setTitle("Edit Kategori");
            tvNamaKategori.setText(nama_kategori);
            budgetKategori.setText(budget_kategori);
        }
        btn_simpan_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (nama_kategori == null){
                        simpanKategori();
                    }else {
                        editKategori();
                    }
                }catch (Exception e){
                    Log.e("Saving",e.getMessage());
                }
            }
        });
    }
    private void simpan(){
     if(String.valueOf(budgetKategori.getText()).equals("")){
            Toast.makeText(getApplicationContext(),"Masukkan Budget Anda", Toast.LENGTH_SHORT).show();
        }else {
            db.insertKategori(tvNamaKategori.getText().toString(), Integer.valueOf(budgetKategori.getText().toString()));
            finish();
        }
   }
    private void edit(){
        if(String.valueOf(budgetKategori.getText()).equals("")){
            Toast.makeText(getApplicationContext(),"Masukkan Budget Anda", Toast.LENGTH_SHORT).show();
        }else {
            db.updateKategori(tvNamaKategori.getText().toString(),Integer.valueOf(budgetKategori.getText().toString()));
            finish();
        }
    }
    private void simpanKategori(){
        if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[0])){
            tvNamaKategori.setText("Kebutuhan");
            simpan();
        }else if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[1])){
            tvNamaKategori.setText("Tambahan");
            simpan();
        }else if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[2])){
            tvNamaKategori.setText("Keinginan");
            simpan();
        }else if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[3])){
            tvNamaKategori.setText("Kultural");
            simpan();
        }else if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[4])){
            tvNamaKategori.setText("Tabungan");
            simpan();
        }
    }
    private void editKategori(){
        if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[0])){
            edit();
        }else if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[1])){
            edit();
        }else if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[2])){
            edit();
        }else if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[3])){
            edit();
        }else if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[4])){
            edit();
        }
    }
}