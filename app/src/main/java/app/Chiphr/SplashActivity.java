package app.Chiphr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Kelas ini merupakan kelas pembuka aplikasi yang utama. Splashscreen
 * digunakan untuk tampilan tambahan yang muncul saat pertama kali kita membuka
 * suatu aplikasi.
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * deklarasi variable untuk ImagaeView
     */
    ImageView bgapp;
    /**
     * deklarasi variable untuk ImagaeView
     */
    ImageView clover;

    /**
     * deklarasi variable untuk LinearLayout
     */
    LinearLayout textsplash;
    /**
     * deklarasi variable untuk LinearLayout
     */
    LinearLayout texthome;
    /**
     * deklarasi variable untuk LinearLayout
     */
    LinearLayout menus;

    /**
     * deklarasi variable untuk Animation
     */
    Animation frombottom;

    /**
     * deklarasi variabel untuk button dengan jenis privat
     */
    private Button button;

    /**
     * @description dalam method onCreate kondisi awal saat Activity baru diciptakan, biasanya dilakukan inisialisasi pada tahapan ini
     * @param savedInstanceState digunakan untuk menyimpan state dari halaman sebelumnya
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadscreen);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);


        bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);

        bgapp.animate().translationY(-1900).setDuration(1800).setStartDelay(1300);
        clover.animate().alpha(0).setDuration(800).setStartDelay(600);
        textsplash.animate().translationY(140).alpha(0).setDuration(1800).setStartDelay(300);

        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * membuat fungsi onlick pada button
             * @param v digunakan untuk mendeteksi tampilan yang diklik sehingga OnClickListener dapat diterapkan
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }
}

