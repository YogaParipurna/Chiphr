package app.Chiphr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    private Button button;
    // deklarasi variable untuk button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        button = (Button) findViewById(R.id.backh);
        // membuat button dengan nama attribut backh
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // membuat fungsi onlick pada button
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                // membuat objek intent untuk berpindah activity aboutactivity ke main activity
                startActivity(intent);
                // berpindah ke Main activity
            }
        });
    }
}
