package com.triton.nanny.petlover;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;

import com.triton.nanny.R;
import com.triton.nanny.adapter.MyCouponsAdapter;
import com.triton.nanny.api.APIClient;
import com.triton.nanny.api.RestApiInterface;
import com.triton.nanny.requestpojo.CouponCodeListRequest;
import com.triton.nanny.responsepojo.CouponCodeListResponse;
import com.triton.nanny.sessionmanager.SessionManager;
import com.triton.nanny.utils.ConnectionDetector;
import com.triton.nanny.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyCouponsActivity extends AppCompatActivity {


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tvNoRecords)
    TextView tvNorecords;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rvnotifiaction)
    RecyclerView rvnotifiaction;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;





    private String TAG = "MyCouponsActivity";

    SessionManager session;
    String type = "",name = "",userid = "";
    private String fromactivity;
    private List<CouponCodeListResponse.DataBean> couponcoderesponseList;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupons);
        Log.w(TAG,"onCreate-->");
        ButterKnife.bind(this);
        avi_indicator.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            fromactivity = extras.getString("fromactivity");
            Log.w(TAG,"Bundle "+" fromactivity : "+fromactivity);



        }



        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        type = user.get(SessionManager.KEY_TYPE);
        name = user.get(SessionManager.KEY_FIRST_NAME);
        userid = user.get(SessionManager.KEY_ID);


        Log.w(TAG,"session--->"+"type :"+type+" "+"name :"+" "+name);


        img_back.setOnClickListener(v -> onBackPressed());


        if (new ConnectionDetector(MyCouponsActivity.this).isNetworkAvailable(MyCouponsActivity.this)) {
            CouponCodeListResponseCall();
        }

        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (new ConnectionDetector(MyCouponsActivity.this).isNetworkAvailable(MyCouponsActivity.this)) {
                    CouponCodeListResponseCall();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),PetLoverDashboardActivity.class));
        finish();


    }
    private void setView() {
        rvnotifiaction.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvnotifiaction.setItemAnimator(new DefaultItemAnimator());
        MyCouponsAdapter myCouponsAdapter = new MyCouponsAdapter(getApplicationContext(), couponcoderesponseList);
        rvnotifiaction.setAdapter(myCouponsAdapter);

    }

    @SuppressLint("LogNotTimber")
    private void CouponCodeListResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<CouponCodeListResponse> call = ApiService.CouponCodeListResponseCall(RestUtils.getContentType(),couponCodeListRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<CouponCodeListResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<CouponCodeListResponse> call, @NonNull Response<CouponCodeListResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"CouponCodeListResponse"+ "--->" + new Gson().toJson(response.body()));
                refresh_layout.setRefreshing(false);

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData() != null && response.body().getData().size()>0){
                            couponcoderesponseList = response.body().getData();
                            tvNorecords.setVisibility(View.GONE);
                            rvnotifiaction.setVisibility(View.VISIBLE);
                            setView();
                        }else{
                            rvnotifiaction.setVisibility(View.GONE);
                            tvNorecords.setVisibility(View.VISIBLE);
                            tvNorecords.setText("no coupons");

                        }


                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<CouponCodeListResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"CouponCodeListResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private CouponCodeListRequest couponCodeListRequest() {
        /*
         * user_id : 5ee3666a5dfb34019b13c3a2
         */
        CouponCodeListRequest couponCodeListRequest = new CouponCodeListRequest();
        couponCodeListRequest.setUser_id(userid);
        Log.w(TAG,"couponCodeListRequest"+ "--->" + new Gson().toJson(couponCodeListRequest));
        return couponCodeListRequest;
    }
}
