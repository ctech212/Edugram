package com.edugram;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Merupakan class untuk menghandle tanya jawab berupa essay untuk materi hewan bawah laut. Terdapat beberapa soal, dan dibandingkan dengan jawaban.
 */
public class QuizEssayOceanActivity extends AppCompatActivity {
    TextView mtvSkor2, mtvSoal2;
    ImageView mivGambar;
    EditText medtJawaban;
    Button mbtnSubmit2;
    int x = 0;
    int arr;
    int skor;
    int passingSkor = 0;
    String jawaban;

    SoalEssay essay = new SoalEssay();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_quiz_essay_ocean);

        mtvSkor2 = (TextView) findViewById(R.id.tvSkor2);
        mtvSoal2 = (TextView) findViewById(R.id.tvSoal2);
        mivGambar = (ImageView) findViewById(R.id.ivGambar);
        medtJawaban = (EditText) findViewById(R.id.edtJawaban);
        mbtnSubmit2 = (Button) findViewById(R.id.btnSubmit2);
        skorSementara();
        skor = passingSkor;

        setKonten();

        mbtnSubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekJawaban();
            }
        });
    }

    //mengambil skor dari tanya jawab pilihan ganda
    public void skorSementara() {
        String skorPilGan = getIntent().getStringExtra("skorAkhir");
        mtvSkor2.setText("" + skorPilGan);
        passingSkor = Integer.parseInt(skorPilGan);
    }

    //mengatur tampilan soal dan jawaban
    public void setKonten() {
        medtJawaban.setText(null);
        arr = essay.pertanyaan.length;
        if (x >= arr) { //jika nilai x melebihi nilai arr(panjang array) maka akan pindah activity (kuis selesai)
            passingSkor = skor;
            Intent intent = new Intent(QuizEssayOceanActivity.this, ScoreActivity.class);
            intent.putExtra("finalSkor", passingSkor);
            startActivity(intent);
        } else {
            //setting text dengan mengambil text dari method getter di kelas SoalEssay
            mtvSoal2.setText(essay.getPertanyaan(x));
            ubahGambar();
            jawaban = essay.getJawabanBenar(x);
        }
        x++;
    }

    //ubah gambar soal
    public void ubahGambar() {
        Resources res = getResources();
        String mPhoto = essay.getStringGambar(x);
        int resID = res.getIdentifier(mPhoto, "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID);
        mivGambar.setImageDrawable(drawable);
    }

    //untuk mengeck jawaban yang dimasukkan user
    public void cekJawaban() {
        if (!medtJawaban.getText().toString().isEmpty()) { //jika edit text TIDAK kosong
            //jika text yang tertulis di edit text tsb = nilai dari var jawaban
            if (medtJawaban.getText().toString().equalsIgnoreCase(jawaban)) {
                skor = skor + 15;
                mtvSkor2.setText("" + skor);    //mtvSkor2 diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                setKonten(); //update next konten
            } else {
                mtvSkor2.setText("" + skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                setKonten();
            }
        } else {
            Toast.makeText(this, "Silahkan pilih jawaban dulu!", Toast.LENGTH_SHORT).show();
        }
    }

    //ini adalah method bawaan dari Android Studio
    //fungsi : memberi aksi ketika tombol back pada hp diklik
    public void onBackPressed() {
        Toast.makeText(this, "Selesaikan kuis", Toast.LENGTH_SHORT).show();
    }
}
