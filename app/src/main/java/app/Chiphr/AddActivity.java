package app.Chiphr;

import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.andexert.library.RippleView;

import app.Chiphr.helper.SqliteHelper;

public class AddActivity extends AppCompatActivity {
//membuat class add activity

    RadioGroup  radio_status;
    // deklarasi variable untuk RadioGrup
    EditText    edit_jumlah, edit_keterangan;
    // deklarasi variable Edit Text
    Button      btn_simpan;
    // deklarasi variable untuk Button
    RippleView  rip_simpan;
    // deklarasi variable untuk RippleView

    String status;
    // deklarasi variable untuk Stirng
    SqliteHelper sqliteHelper;
    // deklarasi variable untuk SqlLiteHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        status = "";
        sqliteHelper = new SqliteHelper(this);



        radio_status    = findViewById(R.id.radio_status);
        // menghubungkan variable radio_status dengan componnen radiostatus pada layout
        edit_jumlah     = findViewById(R.id.edit_jumlah);
        // menghubungkan variable edit_jumlah dengan componnen edit_jumlah pada layout
        edit_keterangan = findViewById(R.id.edit_keterangan);
        // menghubungkan variable edit_keterangan dengan componnen edit_keterangan pada layout
        btn_simpan      = findViewById(R.id.btn_simpan);
        // menghubungkan variable button dengan componnen button pada layout
        rip_simpan      = findViewById(R.id.rip_simpan);
        // menghubungkan variable rip_simpan dengan componen rip_simpan pada layout

        // FindViewById berfungsi untuk menentukan tampilan objek yang sifatnya
        // penting saat membuat relative layout.

        // Untuk membuat record pilihan penyimpanan data baru menggunakan perintah
        //RadioGroup.OnCheckedChangeListener(). Radio button group ini
        //berfungsi untuk merelasikan antara 2 radio button agar bisa di seleksi salah satu
        //saja.
        radio_status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                // membuat switch case masuk dan keluar
                switch(checkedId){
                    // case masuk
                    case R.id.radio_masuk:
                        status = "MASUK";
                        break;
                        // case keluar
                    case R.id.radio_keluar:
                        status = "KELUAR";
                        break;
                }

                Log.d("Log status", status);
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        rip_simpan.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                // mengecek apakah isi dari status sudah sama denga data yang sudah di set
                if (status.equals("") || edit_jumlah.getText().toString().equals("")){ // menyimpan input user di edittext jumlah kedalam variable edit_jumlah
                    Toast.makeText(getApplicationContext(), "Isi data dengan benar", // jika salah akan menampilkan pesan  " isi data dengan benar"
                            Toast.LENGTH_LONG).show();
                } else {

                    // Perintah dibawah ini untuk memasukan status,jumlah dan keterangan pada aplikasi
                    SQLiteDatabase database = sqliteHelper.getWritableDatabase();
                    database.execSQL("INSERT INTO transaksi(status, jumlah, keterangan) VALUES('" +
                            // menyimpan data di database dengan table transaksi
                            status + "','" +
                            edit_jumlah.getText().toString() + "','" +
                            edit_keterangan.getText().toString() + "')");
                            // menyimpan data seuai nama variable
                    Toast.makeText(getApplicationContext(), "Transaksi berhasil disimpan", Toast.LENGTH_LONG).show();
                    // jika benar menampilkan pesan " transaksi berhasil di simpan"
                    finish();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // menampilkan action bar
        getSupportActionBar().setTitle("Add");
        //Perintah dibawah ini berfungsi untuk menambahkan data baru.


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
