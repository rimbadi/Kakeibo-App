package com.example.skripsi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skripsi.Helper.Helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditPemasukkanActivity extends AppCompatActivity {
    EditText uangPemasukkan, ketPemasukkan, tglPemasukkan;
    Button btnSimpanPemasukkan;
    Helper db = new Helper(this);
    String id, uang, keterangan, tanggal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pemasukkan);
        uangPemasukkan = findViewById(R.id.input_jumlah_pemasukkan);
        ketPemasukkan = findViewById(R.id.input_keterangan_pemasukkan);
        tglPemasukkan = findViewById(R.id.input_tanggal_pemasukkan);
        btnSimpanPemasukkan = findViewById(R.id.button_simpan_pemasukkan);
        tglPemasukkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //date picker
                Calendar calendar = Calendar.getInstance();
                Date nowDate = calendar.getTime();
                int nowYear = calendar.get(Calendar.YEAR);
                int nowMonth = calendar.get(Calendar.MONTH);
                int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(EditPemasukkanActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(year, month, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        tglPemasukkan.setText(dateFormat.format(cal.getTime()));
                    }
                }, nowYear, nowMonth, nowDay);
                dialog.show();
            }
        });
        db = new Helper(getApplicationContext());
        id = getIntent().getStringExtra("id");
        keterangan = getIntent().getStringExtra("keterangan");
        uang = getIntent().getStringExtra("uang");
        tanggal = getIntent().getStringExtra("tanggal");
        if (id == null|| id.equals("")){
            setTitle("Tambah Pemasukkan");
        }else {
            setTitle("Edit Pemasukkan");
            ketPemasukkan.setText(keterangan);
            uangPemasukkan.setText(uang);
            tglPemasukkan.setText(tanggal);
        }
        btnSimpanPemasukkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (id == null|| id.equals("")){
                            simpan();
                    }else{
                        edit();
                    }
                }catch (Exception e){
                    Log.e("Saving", e.getMessage());
                }
            }
        });
    }
    private void simpan(){
        if(String.valueOf(ketPemasukkan.getText()).equals("")||String.valueOf(uangPemasukkan.getText()).equals("")||String.valueOf(tglPemasukkan.getText()).equals("")){
            Toast.makeText(getApplicationContext(),"Masukkan Pemasukkan Anda", Toast.LENGTH_SHORT).show();
        }else {
            db.insertPemasukkan(ketPemasukkan.getText().toString(),Integer.valueOf(uangPemasukkan.getText().toString()), tglPemasukkan.getText().toString());
            finish();
        }
    }
    private void edit(){
        if(String.valueOf(ketPemasukkan.getText()).equals("")||String.valueOf(uangPemasukkan.getText()).equals("")||String.valueOf(tglPemasukkan.getText()).equals("")){
            Toast.makeText(getApplicationContext(),"Masukkan Pemasukkan Anda", Toast.LENGTH_SHORT).show();
        }else {
            db.updatePemasukkan(Integer.parseInt(id),ketPemasukkan.getText().toString(),Integer.valueOf(uangPemasukkan.getText().toString()), tglPemasukkan.getText().toString());
            finish();
        }
    }
}