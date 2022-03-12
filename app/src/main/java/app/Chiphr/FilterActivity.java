package app.Chiphr;

import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.andexert.library.RippleView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import app.Chiphr.helper.CurrentDate;

// Kelas ini berguna untuk menyaring data yang banyak agar mudah user
//dalam mencari atau mengelompokkan data berdasarkan tanggal yang user
//inginkan.

/**
 * @description Class ini berfungsi untuk menyaring data agar user mudah dalam mencari atau mengelompokkan data berdasarkan tanggal yang user inginkan
 */
public class FilterActivity extends AppCompatActivity {

    /**
     * deklarasi variable EditText untuk digunakan untuk mengedit tanggal awal dan akhir
     */
    EditText edit_dari, edit_ke;
    // deklarasi variable untuk EditText
    /**
     * deklarasi variable Button untuk digunakan untuk memfilter data
     */
    Button btn_filter;
    // deklarasi variable untuk Button

    /**
     * deklarasi variable RippleView untuk digunakan sebagai effect tambahan ketika memfilter data
     */
    RippleView rip_filter;
    // deklarasi variable untuk Riplleview

    /**
     * deklarasi variable DatePickerDialog untuk digunakan untuk memilih tanggal
     */
    DatePickerDialog datePickerDialog;
    // deklarasi variable untuk DataPickerDialog

    /**
     * Menampilkan Layout activity_filter
     * dan memproses inputan dari Edit_dari
     * @description dalam method onCreate kondisi awal saat Activity baru diciptakan, biasanya dilakukan inisialisasi pada tahapan ini
     * @param savedInstanceState untuk memulihkan diri ke keadaan sebelumnya menggunakan data yang disimpan dalam bundel ini.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        edit_dari   = findViewById(R.id.edit_dari);
        // menghubungkan variable edit_dari dengan componnen edit_dari pada layout
        edit_ke     = findViewById(R.id.edit_ke);
        // menghubungkan variable edit_ke dengan componnen edit_ke pada layout
        btn_filter  = findViewById(R.id.btn_filter);
        // menghubungkan variable btn_filter dengan componnen button pada layout
        rip_filter  = findViewById(R.id.rip_filter);
        // menghubungkan variable rip_filter dengan componnen filter pada layout


        //Perintah dibawah ini menggunakan DatePickerDialog sebagai komponen user
        //interface. User dapat memilih tanggal, bulan dan tahun.
        // disini data picker dari, diambil dari filter tanggal awal

        edit_dari.setOnClickListener(new View.OnClickListener() {
            /**
             * menampilkan kalender untuk memilik tanggal yang dibutuhkan
             * seperti tahun,bulan, dan hari
             * @param v untuk menampilkan objek tesebut
             */
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(FilterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    /**
                     * mengkonfirmasi pilihan tanggal awal filter yang dipilih menjadi bilangan desimal
                     * lalu menyusun huruf tadi menjadi  format (hari/bulan/ tahun)
                     * @param view untuk menampilkan pemilihan tanggal
                     * @param year mendeklarasikan tahun
                     * @param month_of_year mendeklarasikan bulan
                     * @param day_of_month  mendeklarsikan hari
                     */
                    @Override
                    public void onDateSet(DatePicker view, int year, int month_of_year, int day_of_month) {
                        NumberFormat numberFormat = new DecimalFormat("00");
                        MainActivity.tgl_dari = year  + "-" + numberFormat.format(month_of_year + 1) + "-" +
                                numberFormat.format(day_of_month);
                        edit_dari.setText(numberFormat.format(day_of_month) + "/" + numberFormat.format(month_of_year + 1) +
                                "/" + year);
                    }
                }, CurrentDate.year, CurrentDate.month, CurrentDate.day);
                datePickerDialog.show();
                //Ketika user sedang memasukan data atau mengedit data user dapat mengubah
                //tanggal,bulan,dan tahun menggunakan DatePickerDialog.
            }
        });

        // Perintah dibawah ini menggunakan DatePickerDialog sebagai komponen user
        // interface. User dapat memilih tanggal, bulan dan tahun.
        // disini data picker dari, diambil dari filter tanggal ke tanggal akhir filter
        edit_ke.setOnClickListener(new View.OnClickListener() {
            /**
             * menampilkan kalender untuk memilik tanggal yang dibutuhkan
             * seperti tahun,bulan, dan hari
             * @param v untuk menampilkan objek tesebut
             */
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(FilterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    /**
                     * mengkonfirmasi pilihan tanggal akhir filter yang dipilih menjadi bilangan desimal
                     * lalu menyusun huruf tadi menjadi  format (hari/bulan/ tahun)
                     * @param view untuk menampilkan pemilihan tanggal
                     * @param year mendeklarasikan tahun
                     * @param month_of_year mendeklarasikan bulan
                     * @param day_of_month mendeklarasikan Hari
                     */
                    @Override
                    public void onDateSet(DatePicker view, int year, int month_of_year, int day_of_month) {
                        NumberFormat numberFormat = new DecimalFormat("00");
                        MainActivity.tgl_ke = year  + "-" + numberFormat.format(month_of_year + 1) + "-" +
                                numberFormat.format(day_of_month);
                        edit_ke.setText(numberFormat.format(day_of_month) + "/" + numberFormat.format(month_of_year + 1) +
                                "/" + year);
                    }
                }, CurrentDate.year, CurrentDate.month, CurrentDate.day);
                datePickerDialog.show();
                // Ketika user sedang memasukan data atau mengedit data user dapat mengubah
                //tanggal,bulan,dan tahun menggunakan DatePickerDialog.
            }
        });

        rip_filter.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            /**
             * saat edit_dari dan edit_ke salah di input maka akan muncul Toast yang isinya
             * "Isi data dengan benar"
             * dan jika benar makan akan memunculkan data pulsa yang telah difilter tadi
             * lalu akan memunculkan masin Activity
             * @param rippleView mendelkarsikan ripple view, yang digunakan untuk efek tambahan
             */
            @Override
            public void onComplete(RippleView rippleView) {
                if (edit_dari.getText().toString().equals("") || edit_ke.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Isi data dengan benar",
                            Toast.LENGTH_LONG).show();
                } else {
                    MainActivity.filter = true;
                    MainActivity.text_filter.setText( edit_dari.getText().toString() + " - " + edit_ke.getText().toString() );
                    MainActivity.text_filter.setVisibility(View.VISIBLE);

                    finish();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Set Date");

    }

    /**
     *
     * @return disini jika data yang disimpan teridentifikasi benar , makan akan tersimpan
     */
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}

//Dari program diatas user tidak hanya bisa memasukan dan mengedit tanggal tetapi
//user juga dapat memfilter data menggunakan tanggal,bulan dan tahun yang sudah
//diinput sesuai dengan keinginan user.
