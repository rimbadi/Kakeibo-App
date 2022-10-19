package com.example.skripsi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import com.example.skripsi.Helper.Helper;

public class LaporanActivity extends AppCompatActivity {
    TextView tvKebutuhan, tvTambahan, tvKeinginan, tvKultural, tvTabungan, tvPemasukkan,tvPengeluaran, tvbudgetKebutuhan, tvbudgetTambahan, tvbudgetKeinginan, tvbudgetKultural, tvbudgetTabungan;
    Cursor cursor;
    Helper db = new Helper(this);
    String channelnotif = "channelku" ;
    String channelid = "default" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);
        db = new Helper(getApplicationContext());
        tvKebutuhan = findViewById(R.id.tvKebutuhan);
        tvTambahan = findViewById(R.id.tvTambahan);
        tvKeinginan = findViewById(R.id.tvKeinginan);
        tvKultural = findViewById(R.id.tvKultural);
        tvTabungan = findViewById(R.id.tvTabungan);
        tvPemasukkan = findViewById(R.id.tvPemasukkan);
        tvPengeluaran = findViewById(R.id.tvPengeluaran);
        tvbudgetKebutuhan = findViewById(R.id.tvBudgetKebutuhan);
        tvbudgetTambahan = findViewById(R.id.tvBudgetTambahan);
        tvbudgetKeinginan = findViewById(R.id.tvBudgetKeinginan);
        tvbudgetKultural = findViewById(R.id.tvBudgetKultural);
        tvbudgetTabungan = findViewById(R.id.tvBudgetTabungan);

        showTotalKebutuhan();
        showTotalTambahan();
        showTotalKeinginan();
        showTotalKultural();
        showTotalTabungan();
        showTotalPemasukkan();
        showTotalPengeluaran();
        showBudgetKebutuhan();
        showBudgetTambahan();
        showBudgetKeinginan();
        showBudgetKultural();
        showBudgetTabungan();
        notif1();
        notif2();
        notif3();
        notif4();
        notif5();
        notif6();
    }
    private void notif1(){
        String QUERYPEMASUKKAN = "SELECT SUM(uang_pemasukkan) FROM pemasukkan";
        cursor = db.getReadableDatabase().rawQuery(QUERYPEMASUKKAN, null);
        cursor.moveToFirst();
        Integer pemasukkan= cursor.getInt(0);

        String QUERYPENGELUARAN = "SELECT SUM(uang_pengeluaran) FROM pengeluaran";
        cursor = db.getReadableDatabase().rawQuery((QUERYPENGELUARAN), null);
        cursor.moveToFirst();
        Integer pengeluaran= cursor.getInt(0);

        if (pengeluaran > pemasukkan){
            notif();
        }else {
            return;
        }
    }
    private void notif2(){
        String QUERY = "SELECT SUM(uang_pengeluaran) FROM pengeluaran WHERE nama_kategori = 'Kebutuhan'";
        cursor = db.getReadableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        Integer pengeluaran= cursor.getInt(0);

        String QUERYbugdet = "SELECT SUM(budget_kategori) FROM kategori WHERE nama_kategori = 'Kebutuhan'";
        cursor = db.getWritableDatabase().rawQuery(QUERYbugdet, null);
        cursor.moveToFirst();
        Integer budget = cursor.getInt(0);

        if (pengeluaran > budget){
            notifbudget();
        }else {
            return;
        }
    }
    private void notif3(){
        String QUERY = "SELECT SUM(uang_pengeluaran) FROM pengeluaran WHERE nama_kategori = 'Keinginan'";
        cursor = db.getReadableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        Integer pengeluaran= cursor.getInt(0);

        String QUERYbugdet = "SELECT SUM(budget_kategori) FROM kategori WHERE nama_kategori = 'Keinginan'";
        cursor = db.getWritableDatabase().rawQuery(QUERYbugdet, null);
        cursor.moveToFirst();
        Integer budget = cursor.getInt(0);

        if (pengeluaran > budget){
            notifbudget();
        }else {
            return;
        }
    }
    private void notif4(){
        String QUERY = "SELECT SUM(uang_pengeluaran) FROM pengeluaran WHERE nama_kategori = 'Tambahan'";
        cursor = db.getReadableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        Integer pengeluaran= cursor.getInt(0);

        String QUERYbugdet = "SELECT SUM(budget_kategori) FROM kategori WHERE nama_kategori = 'Tambahan'";
        cursor = db.getWritableDatabase().rawQuery(QUERYbugdet, null);
        cursor.moveToFirst();
        Integer budget = cursor.getInt(0);

        if (pengeluaran > budget){
            notifbudget();
        }else {
            return;
        }
    }
    private void notif5(){
        String QUERY = "SELECT SUM(uang_pengeluaran) FROM pengeluaran WHERE nama_kategori = 'Kultural'";
        cursor = db.getReadableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        Integer pengeluaran= cursor.getInt(0);

        String QUERYbugdet = "SELECT SUM(budget_kategori) FROM kategori WHERE nama_kategori = 'Kultural'";
        cursor = db.getWritableDatabase().rawQuery(QUERYbugdet, null);
        cursor.moveToFirst();
        Integer budget = cursor.getInt(0);

        if (pengeluaran > budget){
            notifbudget();
        }else {
            return;
        }
    }
    private void notif6(){
        String QUERY = "SELECT SUM(uang_pengeluaran) FROM pengeluaran WHERE nama_kategori = 'Tabungan'";
        cursor = db.getReadableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        Integer pengeluaran= cursor.getInt(0);

        String QUERYbugdet = "SELECT SUM(budget_kategori) FROM kategori WHERE nama_kategori = 'Tabungan'";
        cursor = db.getWritableDatabase().rawQuery(QUERYbugdet, null);
        cursor.moveToFirst();
        Integer budget = cursor.getInt(0);

        if (pengeluaran > budget){
            notifbudget();
        }else {
            return;
        }
    }
    private void notif() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(LaporanActivity. this, channelid )
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle( "Peringatan !" )
                .setContentText( "Pengeluaran sudah melebihi pemasukkan !" );
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context. NOTIFICATION_SERVICE ) ;
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new
                    NotificationChannel( channelnotif , "notif" , importance) ;
            notificationChannel.enableLights( true ) ;
            notificationChannel.setLightColor(Color. RED ) ;
            mBuilder.setChannelId( channelnotif ) ;
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel) ;
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(( int ) System. currentTimeMillis (), mBuilder.build()) ;
    }
    private void notifbudget() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(LaporanActivity. this, channelid )
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle( "Peringatan !" )
                .setContentText( "Pengeluaran sudah melebihi budget !" );
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context. NOTIFICATION_SERVICE ) ;
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new
                    NotificationChannel( channelnotif , "notif" , importance) ;
            notificationChannel.enableLights( true ) ;
            notificationChannel.setLightColor(Color. RED ) ;
            mBuilder.setChannelId( channelnotif ) ;
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel) ;
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(( int ) System. currentTimeMillis (), mBuilder.build()) ;
    }
    private void showTotalPemasukkan(){
        String QUERY = "SELECT SUM(uang_pemasukkan) FROM pemasukkan";
        cursor = db.getReadableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        if (QUERY == null) {
            tvPemasukkan.setText("0");
        }else{
            tvPemasukkan.setText(cursor.getString(0));
        }
    }
    private void showTotalPengeluaran(){
        String QUERY = "SELECT SUM(uang_pengeluaran) FROM pengeluaran";
        cursor = db.getReadableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        if (QUERY == null || QUERY.equals("")) {
            tvPengeluaran.setText("0");
        }else{
            tvPengeluaran.setText(cursor.getString(0));
        }
    }
    private void showTotalKebutuhan(){
        String QUERY = "SELECT SUM(uang_pengeluaran) FROM pengeluaran WHERE nama_kategori = 'Kebutuhan'";
        cursor = db.getReadableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        if (QUERY == null) {
            tvKebutuhan.setText("0");
        }else {
            tvKebutuhan.setText(cursor.getString(0));
        }
    }
    private void showBudgetKebutuhan(){
        String QUERY = "SELECT SUM(budget_kategori) FROM kategori WHERE nama_kategori = 'Kebutuhan'";
        cursor = db.getWritableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        if (QUERY == null) {
            tvbudgetKebutuhan.setText("0");
        }else {
            tvbudgetKebutuhan.setText(cursor.getString(0));
        }
    }
    private void showTotalTambahan(){
        String QUERY = "SELECT SUM(uang_pengeluaran) FROM pengeluaran WHERE nama_kategori = 'Tambahan'";
        cursor = db.getReadableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        if (QUERY == null) {
            tvTambahan.setText("0");
        }else {
            tvTambahan.setText(cursor.getString(0));
        }
    }
    private void showBudgetTambahan(){
        String QUERY = "SELECT SUM(budget_kategori) FROM kategori WHERE nama_kategori='Tambahan'";
        cursor = db.getWritableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        if (QUERY == null) {
            tvbudgetTambahan.setText("0");
        }else {
            tvbudgetTambahan.setText(cursor.getString(0));
        }
    }
    private void showTotalKeinginan(){
        String QUERY = "SELECT SUM(uang_pengeluaran) FROM pengeluaran WHERE nama_kategori = 'Keinginan'";
        cursor = db.getReadableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        if (QUERY == null) {
            tvKeinginan.setText("0");
        }else {
            tvKeinginan.setText(cursor.getString(0));
        }
    }
    private void showBudgetKeinginan(){
        String QUERY = "SELECT SUM(budget_kategori) FROM kategori WHERE nama_kategori='Keinginan'";
        cursor = db.getWritableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        if (QUERY == null) {
            tvbudgetKeinginan.setText("0");
        }else {
            tvbudgetKeinginan.setText(cursor.getString(0));
        }
    }
    private void showTotalKultural(){
        String QUERY = "SELECT SUM(uang_pengeluaran) FROM pengeluaran WHERE nama_kategori = 'Kultural'";
        cursor = db.getReadableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        if (QUERY == null) {
            tvKultural.setText("0");
        }else{
            tvKultural.setText(cursor.getString(0));
        }
    }
    private void showBudgetKultural(){
        String QUERY = "SELECT SUM(budget_kategori) FROM kategori WHERE nama_kategori = 'Kultural'";
        cursor = db.getWritableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        if (QUERY == null) {
            tvbudgetKultural.setText("0");
        }else {
            tvbudgetKultural.setText(cursor.getString(0));
        }
    }
    private void showTotalTabungan(){
        String QUERY = "SELECT SUM(uang_pengeluaran) FROM pengeluaran WHERE nama_kategori = 'Tabungan'";
        cursor = db.getReadableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        if (QUERY == null) {
            tvTabungan.setText("0");
        }else {
            tvTabungan.setText(cursor.getString(0));
        }
    }
    private void showBudgetTabungan(){
        String QUERY = "SELECT SUM(budget_kategori) FROM kategori WHERE nama_kategori='Tabungan'";
        cursor = db.getWritableDatabase().rawQuery(QUERY, null);
        cursor.moveToFirst();
        if (QUERY == null) {
            tvbudgetTabungan.setText("0");
        }else {
            tvbudgetTabungan.setText(cursor.getString(0));
        }
    }
}