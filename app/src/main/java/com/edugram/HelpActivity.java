package com.edugram;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Merupakan class Help, untuk menampilkan beberapa instruksi atau bantuan tentang aplikasi.
 *
 * @version 02/06/2018
 */
public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_help);
    }


}
