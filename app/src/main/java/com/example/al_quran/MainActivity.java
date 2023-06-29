package com.example.al_quran;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.al_quran.Models.AudioModel.Audio;
import com.example.al_quran.Models.AudioModel.AudioFilesItem;
import com.example.al_quran.Models.SurahModel.Chapters;
import com.example.al_quran.Models.SurahModel.ChaptersItem;
import com.example.al_quran.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView, recyclerView1_;
    private MainAdapter mainAdapter;
    private AudioAdapter audioAdapter;

    private RecyclerView.LayoutManager layoutManager1, layoutManager2;


    private List<ChaptersItem> results = new ArrayList<>();

    private List<AudioFilesItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        setUpRecyclerView();
        getDataFromApi();
        getDataFromApiAudio();

    }

    private void getDataFromApiAudio() {
        ApiService.endpoint().getAudio().enqueue(new Callback<Audio>() {
            @Override
            public void onResponse(Call<Audio> call, Response<Audio> response) {
                List<AudioFilesItem> result = response.body().getAudioFiles();
                audioAdapter.setData(result);
            }

            @Override
            public void onFailure(Call<Audio> call, Throwable t) {

            }
        });
    }


    private void setUpRecyclerView() {
        mainAdapter = new MainAdapter(results);
        layoutManager1 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(mainAdapter);

        audioAdapter = new AudioAdapter(list);
        layoutManager2 = new LinearLayoutManager( this);
        recyclerView1_.setLayoutManager(layoutManager2);
        recyclerView1_.setAdapter(audioAdapter);

    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerViewAyat);
        recyclerView1_ = findViewById(R.id.recyclerViewAudio);
    }

    private void getDataFromApi (){
        ApiService.endpoint().getSurah().enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(Call<Chapters> call, Response<Chapters> response) {
                if (response.isSuccessful()){
                    List<ChaptersItem> result = response.body().getChapters();
                    mainAdapter.setData(result);
                }
            }

            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
            }
        });
    }
}