package com.edugram;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *Merupakan class untuk mengakumulasi skor setelah melakukan tanya jawab. Jadi merupakan tampilan dari final skor.
 *
 *@version 02/06/2018
 */
public class ScoreActivity extends AppCompatActivity {

    Button balik;
    TextView hasil;
    ImageView emot;
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_score);

        balik = (Button) findViewById(R.id.home);
        hasil = (TextView) findViewById(R.id.tvSkorakhir);
        emot = (ImageView) findViewById(R.id.emot);

        skorakhir();

        if (x > 50) {
            emot.setImageResource(R.drawable.happy);
        } else if (x > 35) {
            emot.setImageResource(R.drawable.shappy);
        } else if (x > 25) {
            emot.setImageResource(R.drawable.confused);
        } else {
            emot.setImageResource(R.drawable.crying);
        }

        balik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    //mengambil nilai dari tanya jawab yang telah dilakukan oleh user.
    public void skorakhir() {
        int skorakhir = getIntent().getIntExtra("finalSkor",0);
        hasil.setText("FINAL SCORE : " + skorakhir);
        x = skorakhir;

    }
}
