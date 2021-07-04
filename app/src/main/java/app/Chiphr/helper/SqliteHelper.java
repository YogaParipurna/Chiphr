package app.Chiphr.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SqliteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Chiphr"; // menamai database dengan nama chiphr
    private static final int DATABASE_VERSION = 1; // versi database yang pertama
    public static final String TABLE_NAME = "tbl_user"; // menamami table databse dengan nama table user


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE transaksi (transaksi_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, status TEXT," +
                        "jumlah LONG, keterangan TEXT, tanggal DATE DEFAULT CURRENT_DATE );"
                // membuat table transaksi dengan attribut yang memiliki primary key transaksi

        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS transaksi");
        // mengpaus table transaksi jika ada

    }
}
