package com.edugram;


/**
 * Merupakan class untuk menginisialisasi resources untuk soal essai pada materi hewan bawah laut. Inisialisasi berupa inisialisasi soal, gambar, dan jawaban.
 *
 * @version 02/06/2018
 */
public class SoalEssay {

    public String pertanyaan[] = {
            "Ikan Badut memiliki hubungan simbiosis..... dengan anemon laut.",
            "Hiu adalah hewan karnivora, sehingga menjadi .... puncak pada ekosistem air.",
    };
    private String image[] = {
            "anemon",
            "hiu",
    };

    //membuat array jawaban benar
    private String jawabanBenar[] = {
            "Mutualisme",
            "Konsumen",
    };

    //membuat getter untuk mengambil pertanyaan
    public String getPertanyaan(int x){
        String soal = pertanyaan[x];
        return soal;
    }

    //membuat getter untuk mengambil jawaban benar
    public String getStringGambar(int x){
        String gambar = image[x];
        return gambar;
    }

    //membuat getter untuk mengambil jawaban benar
    public String getJawabanBenar(int x){
        String jawaban = jawabanBenar[x];
        return jawaban;
    }
}
