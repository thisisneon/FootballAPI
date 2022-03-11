package com.example.footballapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.footballapi.BL.ApiService3;
import com.example.footballapi.L1.ApiService2;
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
//    private ProgressBar progressBar;

    //PL
    private TextView tvPLName;
    private TextView tvstartDate;
    private TextView tvendDate;
    private TextView tvmatchDay;
    private CardView cvPL;

    //L1
    private TextView tvL1Name;
    private TextView tvstartDate1;
    private TextView tvendDate1;
    private TextView tvmatchDate1;
    private CardView cvL1;

    //BL
    private TextView tvBLName;
    private TextView tvstartDate2;
    private TextView tvendDate2;
    private TextView tvmatchDate2;
    private CardView cvBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        //PL
        tvPLName = (TextView) findViewById(R.id.tvNamePL);
        tvstartDate = (TextView) findViewById(R.id.tvstartDate);
        tvendDate = (TextView) findViewById(R.id.tvendDate);
        tvmatchDay = (TextView) findViewById(R.id.tvmatchDay);
        cvPL = (CardView) findViewById(R.id.cvPL);
//        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //L1
        tvL1Name = (TextView) findViewById(R.id.tvNameL1);
        tvstartDate1 = (TextView) findViewById(R.id.tvstartDate1);
        tvendDate1 = (TextView) findViewById(R.id.tvendDate1) ;
        tvmatchDate1 = (TextView) findViewById(R.id.tvmatchDay1);
        cvL1 = (CardView) findViewById(R.id.cvL1);

        //BL
        tvBLName = (TextView) findViewById(R.id.tvNameBL);
        tvstartDate2 = (TextView) findViewById(R.id.tvstartDate2);
        tvendDate2 = (TextView) findViewById(R.id.tvendDate2) ;
        tvmatchDate2 = (TextView) findViewById(R.id.tvmatchDay2);
        cvBL = (CardView) findViewById(R.id.cvL1);

        //-------------------------------------------------------------------------------------
        
        PLcall();
        L1call();
        BLcall();

//        click();

    }

    private void BLcall() {

        ApiService3 apiService3 = RetrofitClient.getRetrofitInstance().create(ApiService3.class);
        Call<PLModel> callBL = apiService3.getAllDataL2();

        callBL.enqueue(new Callback<PLModel>() {
            @Override
            public void onResponse(Call<PLModel> call, Response<PLModel> response) {

                Log.e(TAG, "onResponse: code : " + response.body().getName());
                Log.e(TAG, "onResponse: code : " + response.body().getCurrentSeason().getStartDate());
                Log.e(TAG, "onResponse: code : " + response.body().getCurrentSeason().getEndDate());
                Log.e(TAG, "onResponse: code : " + response.body().getCurrentSeason().getCurrentMatchday());

                PLModel plModel = response.body();

                tvBLName.setText(plModel.getName());
                tvstartDate2.setText(plModel.getCurrentSeason().getStartDate());
                tvendDate2.setText(plModel.getCurrentSeason().getEndDate());
                tvmatchDate2.setText(plModel.getCurrentSeason().getCurrentMatchday());

            }

            @Override
            public void onFailure(Call<PLModel> call, Throwable t) {

            }
        });

    }

    private void L1call() {

        ApiService2 apiService2 = RetrofitClient.getRetrofitInstance().create(ApiService2.class);
        Call<PLModel> callL1 = apiService2.getAllDataL1();

        callL1.enqueue(new Callback<PLModel>() {
            @Override
            public void onResponse(Call<PLModel> callL1, Response<PLModel> response) {
                Log.e(TAG, "onResponse: code : " + response.body().getName());
                Log.e(TAG, "onResponse: code : " + response.body().getCurrentSeason().getStartDate());
                Log.e(TAG, "onResponse: code : " + response.body().getCurrentSeason().getEndDate());
                Log.e(TAG, "onResponse: code : " + response.body().getCurrentSeason().getCurrentMatchday());


                PLModel plModel = response.body();

                tvL1Name.setText(plModel.getName());
                tvstartDate1.setText(plModel.getCurrentSeason().getStartDate());
                tvendDate1.setText(plModel.getCurrentSeason().getEndDate());
                tvmatchDate1.setText(plModel.getCurrentSeason().getCurrentMatchday());
            }

            @Override
            public void onFailure(Call<PLModel> call, Throwable t) {

            }
        });



    }

//    private void click() {
//
//        cvPL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Home.this, Demo.class);
//                startActivity(intent);
//            }
//        });
//    }

    private void PLcall() {

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<PLModel> call = apiService.getAllData();

        call.enqueue(new Callback<PLModel>() {
            @Override
            public void onResponse(Call<PLModel> call, Response<PLModel> response) {


                Log.e(TAG, "onResponse: code : " + response.body().getName());
                Log.e(TAG, "onResponse: code : " + response.body().getCurrentSeason().getStartDate());
                Log.e(TAG, "onResponse: code : " + response.body().getCurrentSeason().getEndDate());
                Log.e(TAG, "onResponse: code : " + response.body().getCurrentSeason().getCurrentMatchday());

                //plModelList.add(response.body().);
                PLModel plModel = response.body();

                tvPLName.setText(plModel.getName());
                tvstartDate.setText(plModel.getCurrentSeason().getStartDate());
                tvendDate.setText(plModel.getCurrentSeason().getEndDate());
                tvmatchDay.setText(plModel.getCurrentSeason().getCurrentMatchday());

//                progressBar.setVisibility(View.GONE);
//                cvPL.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<PLModel> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.getMessage());

            }
        });

    }
}