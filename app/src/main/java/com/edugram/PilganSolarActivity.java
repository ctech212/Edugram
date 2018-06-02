package com.edugram;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Merupakan class untuk tanya jawab pilhan ganda materi sistem tata surya, ada beberapa pertanyaan yang dihandle oleh beberapa method yang ada.
 */
public class PilganSolarActivity extends AppCompatActivity {

    TextView mtvSkor;
    ImageView mtvSoal;
    RadioGroup mrgPilihanJawaban;
    RadioButton mrbPilihanJawaban1, mrbPilihanJawaban2, mrbPilihanJawaban3;
    Button mbtnSubmit;
    int skor=0;
    int arr; //untuk menampung nilai panjang array
    int x;   //menunjukkan konten sekarang
    String jawaban; //menampung jawaban benar

    //membuat objek dari kelas SoalPilihanGanda.java
    SoalPilihanGanda soalPG = new SoalPilihanGanda();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_pilgan_solar);

        //menyambungkan antara variabel KuisPilihanGanda.java dengan id activity_kuis_pilihan_ganda
        mtvSkor = (TextView) findViewById(R.id.tvSkor);
        mtvSoal = (ImageView) findViewById(R.id.tvSoal);
        mrgPilihanJawaban = (RadioGroup) findViewById(R.id.rgPilihanJawaban);
        mrbPilihanJawaban1 = (RadioButton) findViewById(R.id.rbPilihanJawaban1);
        mrbPilihanJawaban2 = (RadioButton) findViewById(R.id.rbPilihanJawaban2);
        mrbPilihanJawaban3 = (RadioButton) findViewById(R.id.rbPilihanJawaban3);
        mbtnSubmit = (Button) findViewById(R.id.btnSubmit);

        //set konten
        mtvSkor.setText(""+skor);
        setKonten();
        //handle button submit
        mbtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekJawaban();
            }
        });

    }

    //untuk mengecek jawaban yang telah dipilih oleh user
    private void cekJawaban() {
        if(mrbPilihanJawaban1.isChecked()){ //jika radio button 1 diklik
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if(mrbPilihanJawaban1.getText().toString().equals(jawaban)){
                skor = skor + 10;
                mtvSkor.setText(""+skor);	//mtvSkor diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                setKonten(); //update next konten
            }else{
                mtvSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                setKonten();
            }
        }else if(mrbPilihanJawaban2.isChecked()){
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if(mrbPilihanJawaban2.getText().toString().equals(jawaban)){
                skor = skor + 10;
                mtvSkor.setText(""+skor);	//mtvSkor diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                setKonten(); //update next konten
            }else{
                mtvSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                setKonten();
            }
        }else if(mrbPilihanJawaban3.isChecked()){
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if(mrbPilihanJawaban3.getText().toString().equals(jawaban)){
                skor = skor + 10;
                mtvSkor.setText(""+skor);	//mtvSkor diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                setKonten(); //update next konten
            }else{
                mtvSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                setKonten();
            }
        }else{
            Toast.makeText(this, "Silahkan pilih jawaban dulu!", Toast.LENGTH_SHORT).show();
        }

    }

    //untuk mengatur konten soal dan juga jawaban yang telah dilist.
    private void setKonten() {
        mrgPilihanJawaban.clearCheck();
        arr = soalPG.pertanyaan.length;
        if(x >= arr){ //jika nilai x melebihi nilai arr(panjang array) maka akan pindah activity (kuis selesai)
            String jumlahSkor = String.valueOf(skor);	//menjadikan skor menjadi string
            Intent i = new Intent(PilganSolarActivity.this, ExerciseActivity.class);

            //waktu pindah activity, sekalian membawa nilai jumlahSkor yang ditampung dengan inisial skorAkhir
            //singkatnya skorAkhir = jumlahSkor
            i.putExtra("skorAkhir",jumlahSkor);
            i.putExtra("activity","PilihanGanda");
            startActivity(i);
        }else{
            //setting text dengan mengambil text dari method getter di kelas SoalPilihanGanda
            mtvSoal.setImageResource(soalPG.getPertanyaan(x));
            mrbPilihanJawaban1.setText(soalPG.getPilihanJawaban1(x));
            mrbPilihanJawaban2.setText(soalPG.getPilihanJawaban2(x));
            mrbPilihanJawaban3.setText(soalPG.getPilihanJawaban3(x));
            jawaban = soalPG.getJawabanBenar(x);

        }
        x++;
    }

    //inisialisasi soal
    public class SoalPilihanGanda {

        //membuat array untuk pertanyaan
        public int pertanyaan[] = {
                R.drawable.saturn,
                R.drawable.earth,
                R.drawable.venus,

        };

        //membuat array untuk pilihan jawaban
        private String pilihanJawaban[][] = {
                {"Saturnus","Uranus","Neptunus"},
                {"Venus","Bumi","Uranus"},
                {"Venus","Saturnus","Jupiter"},

        };

        //membuat array untuk jawaban benar
        private String jawabanBenar[] = {
                "Saturnus",
                "Bumi",
                "Venus",
        };

        //membuat getter untuk mengambil pertanyaan
        public int getPertanyaan(int x){
            int soal = pertanyaan[x];
            return soal;
        }

        //membuat getter untuk mengambil pilihan jawaban 1
        public String getPilihanJawaban1(int x){
            String jawaban1 = pilihanJawaban[x][0];
            return jawaban1;
        }

        //membuat getter untuk mengambil pilihan jawaban 2
        public String getPilihanJawaban2(int x){
            String jawaban2 = pilihanJawaban[x][1];
            return jawaban2;
        }

        //membuat getter untuk mengambil pilihan jawaban 3
        public String getPilihanJawaban3(int x){
            String jawaban3 = pilihanJawaban[x][2];
            return jawaban3;
        }

        //membuat getter untuk mengambil jawaban benar
        public String getJawabanBenar(int x){
            String jawaban = jawabanBenar[x];
            return jawaban;
        }
    }
}
