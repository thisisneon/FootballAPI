package com.example.footballapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.footballapi.PL.ApiService;
import com.example.footballapi.PL.PLModel;
import com.example.footballapi.PL.RetrofitClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private RecyclerView rcvData;
    private ArrayList<PLModel> arrayList;
    private RecyclerAdapter recyclerAdapter;
    private ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rcvData = (RecyclerView) findViewById(R.id.rcvData);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        arrayList = new ArrayList<>();



        getSupportActionBar().hide();

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<PLModel> call = apiService.getAllData();

        call.enqueue(new Callback<PLModel>() {
            @Override
            public void onResponse(Call<PLModel> call, Response<PLModel> response) {
                Log.e(TAG, "onResponse: code : " + response.body().getName());
                Log.e(TAG, "onResponse: code : " + response.body().getCurrentSeason().getStartDate());
                Log.e(TAG, "onResponse: code : " + response.body().getCurrentSeason().getEndDate());
                Log.e(TAG, "onResponse: code : " + response.body().getCurrentSeason().getCurrentMatchday());

//                arrayList.add(new PLModel(data1.getFirst_name(), data1.getEmail()));
                arrayList.add(new PLModel())

                rcvData.setLayoutManager(new LinearLayoutManager(Home.this));
                //here
                recyclerAdapter = new RecyclerAdapter(arrayList);
                rcvData.setAdapter(recyclerAdapter);
                

                // Làm sao để mình có thể lấy được Data mục shortname ở trong class Winner?
                //------------
                //------------
                //------------


                // Cú pháp để lấy Data ở trong Json Array

//                ArrayList<PLModel.seasons> seasons = response.body().getSeasons();
//                for (PLModel.seasons seasons1 : seasons)
//                {
//                    Log.e(TAG, "onResponse: startDate : " + seasons1.getStartDate() );
//                }
            }

            @Override
            public void onFailure(Call<PLModel> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.getMessage());

            }
        });
    }
}