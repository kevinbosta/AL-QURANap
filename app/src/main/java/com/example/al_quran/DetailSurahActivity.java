package com.example.al_quran;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.al_quran.Models.ArtiModel.Arti;
import com.example.al_quran.Models.ArtiModel.TranslationsItem;
import com.example.al_quran.Models.AyatModel.Verses;
import com.example.al_quran.Models.AyatModel.VersesItem;
import com.example.al_quran.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSurahActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterAyats adapterAyats;
    private List<VersesItem> results = new ArrayList<>();
    private List<TranslationsItem> results1 = new ArrayList<>();

    List<VersesItem> list;
    List<TranslationsItem> list1;

    TextView judul, tempat, jumlahAyat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);

        int id = getIntent().getIntExtra("id", 1);

        String place = getIntent().getStringExtra("tempat");
        tempat = findViewById(R.id.tvTempat);
        tempat.setText("Tempat Diturunkan  " + place);

        String title = getIntent().getStringExtra("judul");
        judul = findViewById(R.id.tvTitle);
        judul.setText(title);

        int no = getIntent().getIntExtra("verses",1 );
        jumlahAyat = findViewById(R.id.tvJumlahAyat);
        jumlahAyat.setText("Jumlah Ayat  " + no);


        setUpView();
        setUpRecyclerView();
        getDataFromApi(id);
    }

    private void setUpRecyclerView() {
        adapterAyats = new AdapterAyats(results, results1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterAyats);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.rvAyat);
    }

    private void getDataFromApi(int id){
        ApiService.endpoint().getAyat(id).enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(Call<Verses> call, Response<Verses> response) {
                if(response.isSuccessful()){
                    DetailSurahActivity.this.list = response.body().getVerses();
                    getDataArti(getIntent().getIntExtra("id", 1));
                }
            }
            @Override
            public void onFailure(Call<Verses> call, Throwable t) {
                Log.d("Ayat", t.toString());
            }
        });
    }

    private void getDataArti(int id){
        ApiService.endpoint().getArti(id).enqueue(new Callback<Arti>() {
            @Override
            public void onResponse(Call<Arti> call, Response<Arti> response) {
                if (response.isSuccessful()){
                    DetailSurahActivity.this.list1 = response.body().getTranslations();
                    adapterAyats.setData(list, list1);
                }
            }
            @Override
            public void onFailure(Call<Arti> call, Throwable t) {
            }
        });
    }
}