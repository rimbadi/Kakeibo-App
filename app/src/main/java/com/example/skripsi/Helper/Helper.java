package com.example.skripsi.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import java.util.ArrayList;
import java.util.HashMap;

public class Helper extends SQLiteOpenHelper {
    public Helper(Context context) {
        super(context, "kapp" , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_PEMASUKKAN = "CREATE TABLE pemasukkan(id_pemasukkan INTEGER PRIMARY KEY autoincrement, keterangan_pemasukkan TEXT(15) NOT NULL, uang_pemasukkan INTEGER(10), tanggal_pemasukkan DATE)";
        final String CREATE_KATEGORI = "CREATE TABLE kategori(nama_kategori TEXT(8) PRIMARY KEY, budget_kategori INTEGER(10))";
        final String CREATE_PENGELUARAN = " CREATE TABLE pengeluaran(id_pengeluaran INTEGER PRIMARY KEY autoincrement, nama_kategori TEXT REFERENCES kategori(nama_kategori), keterangan_pengeluaran TEXT(15) NOT NULL, uang_pengeluaran INTEGER(10), tanggal_pengeluaran DATE)";
        db.execSQL(CREATE_PEMASUKKAN);
        db.execSQL(CREATE_KATEGORI);
        db.execSQL(CREATE_PENGELUARAN);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pemasukkan");
        db.execSQL("DROP TABLE IF EXISTS kategori");
        db.execSQL("DROP TABLE IF EXISTS pengeluaran");
        onCreate(db);
    }
    //Menampilkan Data
    public ArrayList<HashMap<String, String>> getPemasukkan() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM pemasukkan ORDER BY tanggal_pemasukkan DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id_pemasukkan", cursor.getString(0));
                map.put("keterangan_pemasukkan", cursor.getString(1));
                map.put("uang_pemasukkan", cursor.getString(2));
                map.put("tanggal_pemasukkan", cursor.getString(3));
                list.add(map);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public ArrayList<HashMap<String, String>> getPengeluaran() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM pengeluaran ORDER BY tanggal_pengeluaran DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id_pengeluaran", cursor.getString(0));
                map.put("nama_kategori", cursor.getString(1));
                map.put("keterangan_pengeluaran", cursor.getString(2));
                map.put("uang_pengeluaran", cursor.getString(3));
                map.put("tanggal_pengeluaran", cursor.getString(4));
                list.add(map);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public ArrayList<HashMap<String, String>> getKategori() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        String QUERY = "SELECT * FROM kategori";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("nama_kategori", cursor.getString(0));
                map.put("budget_kategori", cursor.getString(1));
                list.add(map);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    //Memasukkan Data
    //PEMASUKKAN
    public void insertPemasukkan(String keterangan, Integer uang, String tanggal){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "INSERT INTO pemasukkan (keterangan_pemasukkan,uang_pemasukkan,tanggal_pemasukkan) VALUES ('"+keterangan+"','"+uang+"','"+tanggal+"')";
        db.execSQL(QUERY);
    }
    //PENGELUARAN
    public  void insertPengeluaran(String kategori, String keterangan, Integer uang, String tanggal){
        SQLiteDatabase db= this.getWritableDatabase();
        String QUERY = "INSERT INTO pengeluaran(nama_kategori, keterangan_pengeluaran, uang_pengeluaran, tanggal_pengeluaran) VALUES ('"+kategori+"','"+keterangan+"','"+uang+"','"+tanggal+"')";
        db.execSQL(QUERY);
    }
    //KATEGORI
    public  void insertKategori(String nama, Integer budget){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "INSERT INTO kategori(nama_kategori, budget_kategori) VALUES ('"+nama+"','"+budget+"')";
        db.execSQL(QUERY);
    }
    // Mengubah data
    //KATEGORI
    public Boolean updateKategori( String nama, Integer budget){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("budget_kategori",budget);
        Cursor cursor = db.rawQuery("SELECT * FROM kategori WHERE nama_kategori =?", new String[]{nama});
        if (cursor.getCount()>0){
            long result = db.update("kategori", contentValues,"nama_kategori=?",new String[]{nama});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }
    //PEMASUKKAN
    public void updatePemasukkan(Integer id, String keterangan, Integer uang, String tanggal){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "UPDATE pemasukkan SET keterangan_pemasukkan='"+keterangan+"', uang_pemasukkan = "+uang+", tanggal_pemasukkan='"+tanggal+"' WHERE id_pemasukkan="+id;
        db.execSQL(QUERY);
    }
    //PENGELUARAN
    public void updatePengeluaran(int id, String kategori,String keterangan, Integer uang, String tanggal){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "UPDATE pengeluaran SET nama_kategori='"+kategori+"',keterangan_Pengeluaran='"+keterangan+"', uang_pengeluaran = "+uang+", tanggal_pengeluaran='"+tanggal+"' WHERE id_pengeluaran="+id;
        db.execSQL(QUERY);
    }
    //Menghapus Data
    //PEMASUKKAN
    public void deletePemasukkan(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "DELETE FROM pemasukkan WHERE id_pemasukkan="+id;
        db.execSQL(QUERY);
    }
    //PENGELUARAN
    public void deletePengeluaran(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "DELETE FROM pengeluaran WHERE id_pengeluaran="+id;
        db.execSQL(QUERY);
    }
    public void deletetransaksi(){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERYPengeluaran = "DELETE FROM pengeluaran ";
        String QUERYPemasukkan = "DELETE FROM pemasukkan ";
        db.execSQL(QUERYPengeluaran);
        db.execSQL(QUERYPemasukkan);
    }

    //KATEGORI
    public Boolean deleteKategori(String nama){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM kategori WHERE nama_kategori =?", new String[]{nama});
        if (cursor.getCount()>0){
            long result = db.delete("kategori", "nama_kategori=?", new String[]{nama});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }
}


