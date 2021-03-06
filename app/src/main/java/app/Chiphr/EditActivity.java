package app.Chiphr;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.andexert.library.RippleView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import app.Chiphr.helper.CurrentDate;
import app.Chiphr.helper.SqliteHelper;


/**
 *@description Class ini berfungsi untuk mengedit data pada halaman aplikasi sebagai tempat interaksi antara pengguna dengan aplikasi
 */
public class EditActivity extends AppCompatActivity {

    /**
     * deklarasi variable RadioGroup digunakan untuk status radio grup
     */
    RadioGroup  radio_status;
    // deklarasi variable untuk RadioGroup
    /**
     *deklarasi variable RadioButton digunakan untuk radio masuk dan radio keluar
     */
    RadioButton radio_masuk, radio_keluar;
    // deklarasi variable untuk RadioButton

    /**
     *deklarasi variable EditText digunakan untuk mengedit jumlah, keterangan, tanggal
     */
    EditText edit_jumlah, edit_keterangan, edit_tanggal;
    // deklarasi variable untuk EditText

    /**
     * deklarasi variable Button digunakan untuk menyimpan data
     */
    Button btn_simpan;
    // deklarasi variable untuk Button

    /**
     * deklarasi variable RippleView untuk digunakan sebagai effect tambahan ketika menyimpan data
     */
    RippleView rip_simpan;
    // deklarasi variable untuk RippleView

    /**
     *deklarasi variabel filter dengan tipe data sqliteHelp
     */
    SqliteHelper sqliteHelper;
    // deklarasi variable untuk SqlLiteHelper

    /**
     * deklarasi variabel filter dengan tipe data Cursor
     */
    Cursor cursor;
    // deklarasi variable untuk Cursor

    /**
     * deklarasi variable status dan tanggal type data string
     */
    String status, tanggal;
    // deklarasi variable untuk String

    /**
     ** deklarasi variable DataPickerDialog type data DataPickerDialog
     */
    DatePickerDialog datePickerDialog;
    // deklarasi variable untuk DataPickerDialog

    /**
     * @param savedInstanceState
     * @param onCheckedChanged berfungsi untuk merelasikan antara 2 radio button agar bisa di seleksi salah satu
     * @param onClick berfungsi untuk deklarasi variabel dari RadioGroup
     * @param onComplete berfungsi untuk update dan memanggil
     * @param onSupportNavigateUp berfungsi untuk kembali
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Program dibawah ini berfungsi untuk menyimpan tanggal ke sqlite yyyy-mm-dd.

        status = ""; tanggal = "";

        radio_status    = findViewById(R.id.radio_status);
        // menghubungkan variable radio_status dengan componnen radiostatus pada layout
        radio_masuk     = findViewById(R.id.radio_masuk);
        // menghubungkan variable radio_masuk dengan componnen radiomasuk pada layout
        radio_keluar    = findViewById(R.id.radio_keluar);
        // menghubungkan variable radio_keluar dengan componnen radioskeluar pada layout

        edit_jumlah     = findViewById(R.id.edit_jumlah);
        // menghubungkan variable edit_jumlah dengan componnen edit_jumlah pada layout
        edit_keterangan = findViewById(R.id.edit_keterangan);
        // menghubungkan variable Edit_Keterangan dengan componnen edit_keterangan pada layout
        edit_tanggal    = findViewById(R.id.edit_tanggal);
        // menghubungkan variable edit_tanggal dengan componnen edit_tanggal pada layout
        btn_simpan      = findViewById(R.id.btn_simpan);
        // menghubungkan variable btn_simpan dengan componnen butoon pada layout
        rip_simpan      = findViewById(R.id.rip_simpan);
        // menghubungkan variable rip_simpan dengan componnen rip_simpan pada layout

        //Perintah dibawah ini untuk input pemilihan data yang masuk dan keluar pada
        //aplikasi

        sqliteHelper = new SqliteHelper(this);
        SQLiteDatabase database = sqliteHelper.getReadableDatabase();
        // mengambil data dari database sql
        cursor = database.rawQuery(
                "SELECT *, strftime('%d/%m/%Y', tanggal) AS tanggal FROM transaksi WHERE transaksi_id ='" + MainActivity.transaksi_id + "'"
                // memberikan tangga; sesuai hari ini, yang berasal dari tabel transaksi dengan attribut transaksi_id
                , null
        );
        cursor.moveToFirst();
        //variable cursor akan berpindah ke pertama

        status = cursor.getString(1);
        //
        switch (status){
            // membuat swith case untuk variable status
            case "MASUK":
                radio_masuk.setChecked(true); break;
            // jika case masuk di centang maka akan terisi
            case "KELUAR":
                radio_keluar.setChecked(true); break;
            // jika case keluar di centang maka akan terisi

        }

        // Pada radio_masuk user dapat memilih untuk pemasukan data yang diinput,
        //sedangkan radio_keluar user dapat memilih untuk pengeluaran data yang
        //diinput.
        radio_status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * berfungsi untuk merelasikan antara 2 radio button agar bisa di seleksi salah satu
             * @param group menginisiasi radio button
             * @param checkedId berfungsi untuk merelasikan antara 2 radio button agar bisa di seleksi salah satu saja.
             */
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                // berfungsi untuk merelasikan antara 2 radio button agar bisa di seleksi salah satu
                //saja.
                switch(checkedId){
                    // membuat switch case untuk case masuk dan keluar
                    case R.id.radio_masuk:
                        status = "MASUK";
                        break;
                    case R.id.radio_keluar:
                        status = "KELUAR";
                        break;
                }

                Log.d("Log status", status);
            }
        });

        edit_jumlah.setText( cursor.getString(2) );
        edit_keterangan.setText( cursor.getString(3) );

        tanggal = cursor.getString(4);
        edit_tanggal.setText( cursor.getString(5) );

        //Variable ini dideklarasikan untuk menentukan tanggal yang akan diinput user
        //sesuai data yang dimasukan dan dikeluarkan pada aplikasi.
        edit_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(EditActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month_of_year, int day_of_month) {
                        // set day of month , month and year value in the edit text
                        NumberFormat numberFormat = new DecimalFormat("00");
                        tanggal = year + "-" + numberFormat.format(( month_of_year +1 )) + "-" +
                                numberFormat.format(day_of_month);
                        edit_tanggal.setText(numberFormat.format(day_of_month) + "/" + numberFormat.format(( month_of_year +1 )) +
                                "/" + year );
                    }
                }, CurrentDate.year, CurrentDate.month, CurrentDate.day);
                datePickerDialog.show();
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v menginisiasi view sebagai tampilan
             */
            @Override
            public void onClick(View v) {
            }
        });

        rip_simpan.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            /**
             * @param rippleView berfungsi untuk update dan memanggil
             */
            @Override
            public void onComplete(RippleView rippleView) {
                if (status.equals("") || edit_jumlah.getText().toString().equals("")){ // menyimpan input user di edittext jumlah kedalam variable edit_jumlah
                    Toast.makeText(getApplicationContext(), "Isi data dengan benar",
                            Toast.LENGTH_LONG).show();
                } else {

                    // Perintah dibawah ini menggunakan metode update dari open helper memanggil
                    //metode update database, sehingga user tidak perlu menulis query SQL seluruhnya.
                    SQLiteDatabase database = sqliteHelper.getWritableDatabase();
                    database.execSQL(
                            "UPDATE transaksi SET status='" + status + "', jumlah='" + edit_jumlah.getText().toString() +
                                    "', " + "keterangan='" + edit_keterangan.getText().toString() + "', tanggal='" + tanggal +
                                    "' WHERE transaksi_id='" + MainActivity.transaksi_id + "'"
                    );
                    // Untuk memberitahukan Informasi pada user, mengenai aksi yang akan di
                    //eksekusi, berupa konfirmasi, pesan error atau pemberitahuan lainnya bisa
                    //menggunakan Toast
                    Toast.makeText(getApplicationContext(), "Perubahan berhasil disimpan", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit");
    }

    /**
     * @return berfungsi untuk kembali
     */
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}

