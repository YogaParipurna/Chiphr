package app.Chiphr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @description Class ini berfungsi untuk menampilkan informasi mengenai aplikasi ini
 */
public class AboutActivity extends AppCompatActivity {

    /**
     * deklarasi variable Button untuk digunakan untuk kembali ke halaman utama
     */
    private Button button;
    // deklarasi variable untuk button

    /**
     * @description dalam method onCreate kondisi awal saat Activity baru diciptakan, biasanya dilakukan inisialisasi pada tahapan ini
     * @param savedInstanceState digunakan untuk menyimpan state dari halaman sebelumnya
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        button = (Button) findViewById(R.id.backh);
        // membuat button dengan nama attribut backh
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * membuat fungsi onlick pada button
             * @param v digunakan untuk mendeteksi tampilan yang diklik sehingga OnClickListener dapat diterapkan
             */
            @Override
            // membuat fungsi onlick pada button
            public void onClick(View v) {
                // membuat objek intent untuk berpindah activity aboutactivity ke main activity
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                // berpindah ke Main activity
                startActivity(intent);
            }
        });
    }
}

