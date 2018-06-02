package com.edugram;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


/**
 * Merupakan class exercise, digunakan untuk menghandle tanya jawab dari materi solar sistem dengan menggunakan voice recognition.
 *
 * @version 02/06/2018
 */
public class ExerciseActivity extends AppCompatActivity {
    private TextView inputSuara;
    public TextView skor;
    int passingSkor = 0;
    private Button tombolBicara;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_exercise);
        inputSuara = (TextView) findViewById(R.id.inputSuara);
        tombolBicara = (Button) findViewById(R.id.tombolBicara);
        skor = (TextView) findViewById(R.id.tvSkor);
        skorSementara();


        tombolBicara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tanyaInputSuara();
            }
        });
    }

    //untuk melakukan inisialisasi skor, yaitu mendapatkan skor dari activity soal pilihan ganda materi solar sistem.
    public void skorSementara() {
        String skorPilGan = getIntent().getStringExtra("skorAkhir");
        skor.setText("" + skorPilGan);
        passingSkor = Integer.parseInt(skorPilGan);
    }

    // Untuk menampilkan Google speech input dialog
    public void tanyaInputSuara() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hei Jawab Pertanyaannya ");

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }

    }

    //Untuk menerima inputan speech dan menampilkan text
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "Cek Jawaban Anda", Toast.LENGTH_SHORT).show();
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    inputSuara.setText(result.get(0));
                    AlertDialog.Builder builder = new AlertDialog.Builder(ExerciseActivity.this);
                    builder.setMessage("Apakah Anda Yakin dengan Jawaban Anda?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    if (inputSuara.getText().toString().equalsIgnoreCase("Jupiter")) {

                                        passingSkor = passingSkor + 20;
                                        Toast.makeText(ExerciseActivity.this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ExerciseActivity.this, ScoreActivity.class);
                                        intent.putExtra("finalSkor", passingSkor);
                                        startActivity(intent);
                                    } else {

                                        Toast.makeText(ExerciseActivity.this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                                        passingSkor = passingSkor + 0;
                                        Intent intent = new Intent(ExerciseActivity.this, ScoreActivity.class);
                                        intent.putExtra("finalSkor", passingSkor);
                                        startActivity(intent);
                                    }

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(ExerciseActivity.this, ExerciseActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                break;
            }

        }
    }

}