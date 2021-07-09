package app.Chiphr;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import app.Chiphr.helper.SqliteHelper;

public class MainActivity extends AppCompatActivity {

    //Perintah dibawah  yaitu untuk mengatur database pada program dan untuk
    // pemanggilan TextView, ListView, String, SqliteHelper dan Cursor.

    TextView text_masuk, text_keluar, text_total;
    // deklarasi variable untuk TextView
    ListView list_kas;
    // deklarasi variable untuk ListView
    SwipeRefreshLayout swipe_refresh;
    // deklarasi variable untuk SwipeRefreshLayout
    ArrayList<HashMap<String, String>> aruskas = new ArrayList<HashMap<String, String>>();

    public static TextView text_filter;
    // deklarasi variable untuk TextView
    public static String transaksi_id, tgl_dari, tgl_ke;
    // deklarasi variable untuk String
    public static boolean filter;

    String query_kas, query_total;
    // deklarasi variable untuk String
    SqliteHelper sqliteHelper;
    // deklarasi variable untuk SqlLiteHelper
    Cursor cursor;
    // deklarasi variable untuk Cursor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        transaksi_id = ""; tgl_dari = ""; tgl_ke = ""; query_kas = ""; query_total = "";  filter = false;
        sqliteHelper = new SqliteHelper(this);

        //Perintah dibawah mendeklarasikan text_masuk, text_keluar, text_saldo,
        //list_kas, text_filter.

        text_filter     = findViewById(R.id.text_filter);
        // menghubungkan variable text_filter dengan componnen text_filter pada layout
        text_masuk      = findViewById(R.id.text_masuk);
        // menghubungkan variable text_masuk dengan componnen text_masuk pada layout
        text_keluar     = findViewById(R.id.text_keluar);
        // menghubungkan variable text_keluar dengan componnen text_keluar pada layout
        text_total      = findViewById(R.id.text_total);
        // menghubungkan variable text_total dengan componnen text_total pada layout
        list_kas        = findViewById(R.id.list_kas);
        // menghubungkan variable list_kas dengan componnen list_kas pada layout
        swipe_refresh   = findViewById(R.id.swipe_refresh);
        // menghubungkan variable swipe_refresh dengan componnen swipe_refresh pada layout

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Perintah untuk tanggal transaksi adalah sebagai berikut:
                query_kas   =
                        "SELECT *, strftime('%d/%m/%Y', tanggal) AS tgl FROM transaksi ORDER BY transaksi_id DESC";

                //Perintah untuk total pemasukan dan pengeluaran adalah sebagai berikut :
                query_total =
                        "SELECT SUM(jumlah) AS total, " +
                                "(SELECT SUM(jumlah) FROM transaksi WHERE status='MASUK') AS masuk, " +
                                "(SELECT SUM(jumlah) FROM transaksi WHERE status='KELUAR') AS keluar " +
                                "FROM transaksi";
                KasAdapter();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
        FloatingActionButton bhome = (FloatingActionButton) findViewById(R.id.bhome);
        bhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SplashActivity.class));
            }
        });

    }

    private void KasAdapter(){

        aruskas.clear(); list_kas.setAdapter(null);

        SQLiteDatabase database = sqliteHelper.getReadableDatabase();
        cursor = database.rawQuery( query_kas, null);
        cursor.moveToFirst();

        int i;
        for (i=0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);

            // Perintah dibawah merupakan hashmap yaitu sebuah class yang berisi sekumpulan
            // pasangan nilai (value) dan kunci (key) untuk menampilkan data

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("transaksi_id", cursor.getString(0) );
            map.put("status",       cursor.getString(1) );
            map.put("jumlah",       cursor.getString(2) );
            map.put("keterangan",   cursor.getString(3) );
            map.put("tanggal",      cursor.getString(5) );
            aruskas.add(map);
        }
        // Hashmap diatas terdiri dari transaksi_id, status, jumlah, keterangan dan
        //tanggal.

        if (i == 0){
            Toast.makeText(getApplicationContext(), "Tidak ada transaksi untuk ditampilkan",
                    Toast.LENGTH_LONG).show();
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, aruskas, R.layout.list_pulsa,
                new String[] { "transaksi_id", "status", "jumlah", "keterangan", "tanggal"},
                new int[] {R.id.text_transaksi_id, R.id.text_status, R.id.text_jumlah, R.id.text_keterangan,
                        R.id.text_tanggal});

        list_kas.setAdapter(simpleAdapter);
        list_kas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                transaksi_id = ((TextView) view.findViewById(R.id.text_transaksi_id)).getText().toString();
                ListMenu();
            }
        });

        KasTotal();
    }

    private void KasTotal(){
        NumberFormat rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);

        SQLiteDatabase database = sqliteHelper.getReadableDatabase();
        cursor = database.rawQuery( query_total, null);
        cursor.moveToFirst();
//        text_total.setText( rupiahFormat.format(cursor.getDouble(0)) );
        text_masuk.setText( rupiahFormat.format(cursor.getDouble(1)) );
        text_keluar.setText( rupiahFormat.format(cursor.getDouble(2)) );
        text_total.setText(
                rupiahFormat.format(cursor.getDouble(1) - cursor.getDouble(2))
        );

        swipe_refresh.setRefreshing(false);

        if (!filter) { text_filter.setVisibility(View.GONE); }
        filter = false;
    }

    @Override
    public void onResume(){
        super.onResume();

        query_kas   =
                "SELECT *, strftime('%d/%m/%Y', tanggal) AS tgl FROM transaksi ORDER BY transaksi_id DESC";
        query_total =
                "SELECT SUM(jumlah) AS total, " +
                "(SELECT SUM(jumlah) FROM transaksi WHERE status='MASUK') AS masuk, " +
                "(SELECT SUM(jumlah) FROM transaksi WHERE status='KELUAR') AS keluar " +
                "FROM transaksi";

        if (filter) {

            query_kas   =
                    "SELECT *, strftime('%d/%m/%Y', tanggal) AS tgl FROM transaksi  " +
                    "WHERE (tanggal >= '" + tgl_dari + "') AND (tanggal <= '" + tgl_ke + "') ORDER BY transaksi_id ASC ";
            query_total =
                    "SELECT SUM(jumlah) AS total, " +
                    "(SELECT SUM(jumlah) FROM transaksi WHERE status='MASUK' AND (tanggal >= '" + tgl_dari + "') AND (tanggal <= '" + tgl_ke + "') ), " +
                    "(SELECT SUM(jumlah) FROM transaksi WHERE status='KELUAR' AND (tanggal >= '" + tgl_dari + "') AND (tanggal <= '" + tgl_ke + "')) " +
                    "FROM transaksi " +
                    "WHERE (tanggal >= '" + tgl_dari + "') AND (tanggal <= '" + tgl_ke + "') ";
//            filter = false;
        }

        KasAdapter();

    }

    // Perintah dibawah ini untuk membuat pilihan menu edit dan hapus.
    private void ListMenu(){

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.list_menu);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        TextView text_edit  = dialog.findViewById(R.id.text_edit);
        TextView text_hapus = dialog.findViewById(R.id.text_hapus);
        dialog.show();

        text_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(MainActivity.this, EditActivity.class));
            }
        });
        text_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Hapus();
            }
        });
    }


    // Perintah untuk hapus data
    private void Hapus(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Yakin untuk mengahapus transaksi ini?");
        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Perintah diatas merupakan code untuk penghapusan data jika data yakin ingin
                        //dihapus pilih YES.

                        dialog.dismiss();

                        SQLiteDatabase database = sqliteHelper.getWritableDatabase();
                        database.execSQL("DELETE FROM transaksi WHERE transaksi_id = '" + transaksi_id + "'");
                        Toast.makeText(getApplicationContext(), "Tranksaki berhasil dihapus",
                                Toast.LENGTH_LONG).show();

                        KasAdapter();
                    }
                });
        //Toast diatas merupakan salah satu widget yang digunakan untuk menampilkan
        // pesan berupa text. Perintah diatas merupakan source code
        //untuk jika pengapusan berhasil maka akan muncul komentar bahwa data transaksi
        //berhasil dihapus.

        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
    //Perintah diatas merupakan code untuk penghapusan data jika data yakin ingin
    //dihapus pilih NO.

    //Perintah getMenuInflater().inflate(R.menu.menu_main,
    //menu); berfungsi untuk memanggil menu option tampilan yang muncul ketika
    //ditekan tombol menu pada device android
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Perintah onOptionsItemSelected berfungsi agar berhasil dalam menangani
    //sebuah item menu dan dideklarasikan dengan menambahkan return true;.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            startActivity(new Intent(MainActivity.this, FilterActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
