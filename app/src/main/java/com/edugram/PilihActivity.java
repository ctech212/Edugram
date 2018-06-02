package com.edugram;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * merupakan class untuk menghandle pilihan materi yang dapat dipilih oleh user.
 * @version 02/06/2018
 */
public class PilihActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_pilih);

        ImageButton konten1 = (ImageButton) findViewById(R.id.konten1);
        ImageButton konten2 = (ImageButton) findViewById(R.id.konten2);

        konten1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PilihActivity.this, PlayerActivity.class);
                startActivity(intent);
            }
        });

        konten2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PilihActivity.this, Player2Activity.class);
                startActivity(intent);
            }
        });
    }
}
