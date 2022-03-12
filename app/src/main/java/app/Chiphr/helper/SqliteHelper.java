package app.Chiphr.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//Untuk membuat record penyimpanan baru di aplikasi ini digunakan
//perintah public void OnCreate(SQLiteDatabase db){. Nama record
//penyimpanan aplikasi ini adalah transaksi.

/**
 * Untuk membuat record penyimpanan baru di aplikasi ini digunakan perintah public void OnCreate(SQLiteDatabase db) Nama record penyimpanan aplikasi ini adalah transaksi.
 */
public class SqliteHelper extends SQLiteOpenHelper {

    /**
     *  menamai database dengan nama chiphr
     */
    private static final String DATABASE_NAME = "Chiphr";
    /**
     * database_version ini menunjukan versi dari database yaitu database versi yang pertama
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * @param context Sebuah Context memberikan akses informasi atas application state. Ia memperbolehkan Activity, Fragment, dan Service untuk mengakses file, gambar, theme/style, dan lokasi direktori eksternal. Context juga memberikan akses ke service yang terpasang di Android yang akan digunakan misalnya untuk layout inflation, keyboard, dan mencari content provider.
     */
    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    //Untuk membuat database terlebih dahulu berikan nama database. Untuk nama
    //database dibawah ini yaitu transaksi.

    /**
     * @param db parameter db digunakan untuk menginisiasi SQLiteDatabase package
     * dalam method ini akan membuat tabel database baru
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE transaksi (transaksi_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, status TEXT," +
                        "jumlah LONG, keterangan TEXT, tanggal DATE DEFAULT CURRENT_DATE );"
                // membuat table transaksi dengan attribut yang memiliki primary key transaksi
        );
    }
    // Fungsi AUTOINCREMENT yaitu data otomatis akan terurut, status untuk
    //menyimpan adanya masuk dan keluar data, jumlah menggunakan tipe data double
    //berfungsi jika adanya angka yang sama, keterangan menggunakan text, dan
    //tanggal menggunakan DATE DEFAULT CURRENT_DATE yang otomatis
    //mengambil tanggal pada aplikasi.


    /**
     * Fungsi AUTOINCREMENT yaitu data otomatis akan terurut, status untuk menyimpan adanya masuk dan
     * keluar data, jumlah menggunakan tipe data double berfungsi jika adanya angka yang sama, keterangan menggunakan text,
     * dan tanggal menggunakan DATE DEFAULT CURRENT_DATE yang otomatis mengambil tanggal pada aplikasi.
     * @param db parameter db digunakan untuk menginisiasi SQLiteDatabase package
     * @param oldVersion maksud dari parameter ini adalah untuk mendeteksi versi database yang sebelumnya
     * @param newVersion maksud dari parameter ini adalah untuk mendeteksi versi database yang baru dibuat
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS transaksi");
        // menghpaus table transaksi jika ada

    }
}
