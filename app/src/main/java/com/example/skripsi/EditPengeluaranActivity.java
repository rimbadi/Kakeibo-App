package com.example.skripsi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.skripsi.Helper.Helper;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditPengeluaranActivity extends AppCompatActivity {
    EditText uangPengeluaran, ketPengeluaran, tglPengeluaran;
    TextView tvNamaKategori;
    Button btnSimpanPengeluaran;
    Spinner spinnerKategori;
    String[] pilihanKategori = {"Kebutuhan Hidup","Tambahan","Keinginan","Kultural","Tabungan"};
    Helper db = new Helper(this);
    String id, uang, keterangan, tanggal, kategori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pengeluaran);
        uangPengeluaran = findViewById(R.id.input_jumlah);
        tvNamaKategori = findViewById(R.id.input_kategori);
        spinnerKategori = findViewById(R.id.spinner_ketegori);
        ketPengeluaran = findViewById(R.id.input_keterangan);
        tglPengeluaran = findViewById(R.id.input_tanggal);
        btnSimpanPengeluaran = findViewById(R.id.button_simpan);
        ArrayAdapter adapter = new ArrayAdapter(EditPengeluaranActivity.this, android.R.layout.simple_spinner_dropdown_item, pilihanKategori);
        spinnerKategori.setAdapter(adapter);
        tglPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                Date nowDate = calendar.getTime();
                int nowYear = calendar.get(Calendar.YEAR);
                int nowMonth = calendar.get(Calendar.MONTH);
                int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(EditPengeluaranActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(year, month, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        tglPengeluaran.setText(dateFormat.format(cal.getTime()));
                    }
                }, nowYear, nowMonth, nowDay);
                dialog.show();
            }
        });
        id = getIntent().getStringExtra("id_pengeluaran");
        kategori = getIntent().getStringExtra("nama_kategori");
        keterangan = getIntent().getStringExtra("keterangan_pengeluaran");
        uang = getIntent().getStringExtra("uang_pengeluaran");
        tanggal = getIntent().getStringExtra("tanggal_pengeluaran");
        if (id == null|| id.equals("")){
            setTitle("Tambah Pengeluaran");
        }else {
            setTitle("Edit Pengeluaran");
            tvNamaKategori.setText(kategori);
            ketPengeluaran.setText(keterangan);
            uangPengeluaran.setText(uang);
            tglPengeluaran.setText(tanggal);
        }
        btnSimpanPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (id == null|| id.equals("")){
                        simpanPengeluaran();
                    }else{
                        editPengeluaran();
                    }
                }catch (Exception e){
                    Log.e("Saving", e.getMessage());
                }
            }
        });
    }
    private void simpan(){
        if(String.valueOf(ketPengeluaran.getText()).equals("")||String.valueOf(uangPengeluaran.getText()).equals("")||String.valueOf(tglPengeluaran.getText()).equals("")){
            Toast.makeText(getApplicationContext(),"Masukkan Pemasukkan Anda", Toast.LENGTH_SHORT).show();
        }else {
            db.insertPengeluaran(tvNamaKategori.getText().toString(),ketPengeluaran.getText().toString(), Integer.valueOf(uangPengeluaran.getText().toString()), tglPengeluaran.getText().toString());
            finish();
        }
    }
    private void edit(){
        if(String.valueOf(ketPengeluaran.getText()).equals("")||String.valueOf(uangPengeluaran.getText()).equals("")||String.valueOf(tglPengeluaran.getText()).equals("")){
            Toast.makeText(getApplicationContext(),"Masukkan Pemasukkan Anda", Toast.LENGTH_SHORT).show();
        }else {
            db.updatePengeluaran(Integer.parseInt(id),tvNamaKategori.getText().toString(),ketPengeluaran.getText().toString(),Integer.valueOf(uangPengeluaran.getText().toString()), tglPengeluaran.getText().toString());
            finish();
        }
    }
    private void simpanPengeluaran(){
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
    private void editPengeluaran(){
        if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[0])){
            tvNamaKategori.setText("Kebutuhan");
            edit();
        }else if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[1])){
            tvNamaKategori.setText("Tambahan");
            edit();
        }else if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[2])){
            tvNamaKategori.setText("Keinginan");
            edit();
        }else if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[3])){
            tvNamaKategori.setText("Kultural");
            edit();
        }else if (spinnerKategori.getSelectedItem().toString().equals(pilihanKategori[4])){
            tvNamaKategori.setText("Tabungan");
            edit();
        }
    }
}