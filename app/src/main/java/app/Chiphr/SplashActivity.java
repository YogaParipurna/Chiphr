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

public class SplashActivity extends AppCompatActivity {

    ImageView bgapp, clover;
    // deklarasi variable untuk ImagaeView
    LinearLayout textsplash, texthome, menus;
    // deklarasi variable untuk LinearLayout
    Animation frombottom;
    // deklarasi variable untuk Animation
    private Button button;
    private Button button2;

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
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
}
    }

