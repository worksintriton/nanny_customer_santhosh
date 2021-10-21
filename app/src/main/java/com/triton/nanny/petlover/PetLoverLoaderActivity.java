package com.triton.nanny.petlover;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.triton.nanny.R;
import com.triton.nanny.api.APIClient;
import com.triton.nanny.api.RestApiInterface;
import com.triton.nanny.requestpojo.FindServiceProviderRequest;
import com.triton.nanny.responsepojo.CartDetailsResponse;
import com.triton.nanny.responsepojo.FindServiceProviderResponse;
import com.triton.nanny.responsepojo.SPDetailsRepsonse;
import com.triton.nanny.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetLoverLoaderActivity extends AppCompatActivity {

    private String TAG = "PetLoverLoaderActivity";

    private static final long SPLASH_TIME_OUT = 10000;

/*    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;*/

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.logo)
    ImageView logo;


    String fromactivity,SP_ava_Date,selectedTimeSlot,subcatid,subservname,servname,icon_banner,servicedate,appointment_id;

    String serv_name,selectedServiceImagepath;


    private int distance;
    private int reviewcount;
    private int Count_value_start;
    private int Count_value_end;

    List<CartDetailsResponse.DataBean> Data = new ArrayList<>();

    private int prodouct_total;

    private int shipping_charge;

    private int discount_price;

    private int grand_total;

    private int prodcut_count;

    private int prodcut_item_count;

    private String userid;
    private String spid,catid;
    private List<SPDetailsRepsonse.DataBean.BusServiceGallBean> spServiceGalleryResponseList;
    private String from;
    private String spuserid;
    private String selectedServiceTitle;
    private String servicetime;
    private int serviceamount;


    private String serviceprovidingcompanyname = "";
    private String spprovidername = "";
    private int ratingcount;

    private String location,count_number,total_amount;
    FindServiceProviderResponse.DataBean dataBean = new FindServiceProviderResponse.DataBean();

    CountDownTimer countDownTimer;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_root)
    LinearLayout rl_root;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_back)
    Button btn_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.content)
    LinearLayout content;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_title)
    TextView txt_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_subtitle)
    TextView txt_subtitle;

    String first_name,last_name,flat_no,landmark,pincode,alt_phonum,address_status,city,username;

    String  name, phonum, state, street, landmark_pincode, address_type, date, shipid,radioValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(+R.layout.activity_pet_lover_loader);
        ButterKnife.bind(this);
        Log.w(TAG,"onCreate");

      //  avi_indicator.smoothToShow();


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            spid = extras.getString("spid");
            catid = extras.getString("catid");
            from = extras.getString("from");
            spuserid = extras.getString("spuserid");
            selectedServiceTitle = extras.getString("selectedServiceTitle");
            serviceamount = extras.getInt("serviceamount");
            servicetime = extras.getString("servicetime");
            distance = extras.getInt("distance");
            serviceamount = extras.getInt("serviceamount");
            servicetime = extras.getString("servicetime");
            SP_ava_Date = extras.getString("SP_ava_Date");
            selectedTimeSlot = extras.getString("selectedTimeSlot");
            count_number = extras.getString("count_number");
            total_amount = extras.getString("total_amount");
            Log.w(TAG, "spid : " + spid + " catid : " + catid + " from : " + from);
            Log.w(TAG, "distance : " + distance);
            fromactivity = extras.getString("fromactivity");

            Log.w(TAG, "From " + fromactivity + " : true-->");

            Data = (List<CartDetailsResponse.DataBean>) extras.getSerializable("data");

            prodouct_total = extras.getInt("product_total");

            shipping_charge = extras.getInt("shipping_charge");

            discount_price = extras.getInt("discount_price");

            grand_total = extras.getInt("grand_total");

            prodcut_count = extras.getInt("prodcut_count");

            prodcut_item_count = extras.getInt("prodcut_item_count");


            /**/

            catid = extras.getString("catid");

            subcatid = extras.getString("subcatid");

            servname = extras.getString("servname");

            subservname = extras.getString("subservname");

            icon_banner = extras.getString("icon_banner");

            serviceamount = extras.getInt("serviceamount");

            servicetime = extras.getString("servicetime");

            servicedate = extras.getString("servicedate");

            appointment_id = extras.getString("appointment_id");


            state = extras.getString("state");

            street = extras.getString("street");

            landmark = extras.getString("landmark");

            pincode = extras.getString("pincode");

            address_type = extras.getString("address_type");

            city = extras.getString("city");

            radioValue = extras.getString("radioValue");


            Log.w(TAG,"spid : "+spid +" catid : "+catid+"subcatid : "+subcatid+"  from : "+from);

            Log.w(TAG,"servname : "+servname +" subservname : "+subservname+"icon_banner : "+icon_banner+"  serviceamount : "+serviceamount);

            Log.w(TAG,"servicetime : "+servicetime +" servicedate : "+servicetime);


            Log.w(TAG,"distance : "+distance);


        }


        txt_title.setText("Searching......");

        txt_subtitle.setText("We are looking for the nearest available best Nanny for you.\n" +
                "Please wait..!!");

        Glide.with(PetLoverLoaderActivity.this)
                .load(R.drawable.searchgif)
                .into(logo);


        MaterialProgressBar progressBar = (MaterialProgressBar) findViewById(R.id.progress);

        // timer for seekbar
        final int oneMin = 1 * 60 * 1000; // 1 minute in milli seconds

        /*final int oneMin = 10000; // 1 minute in milli seconds
*/
        /** CountDownTimer starts with 1 minutes and every onTick is 1 second */
         countDownTimer = new CountDownTimer(oneMin, 1000) {
            public void onTick(long millisUntilFinished) {

                //forward progress
                long finishedSeconds = oneMin - millisUntilFinished;
                int total = (int) (((float)finishedSeconds / (float)oneMin) * 100.0);
                progressBar.setProgress(total);
                findSPResponseCall();

//                //backward progress
//                int total = (int) (((float) millisUntilFinished / (float) oneMin) * 100.0);
//                progressBar.setProgress(total);

            }

            public void onFinish() {
                // DO something when 1 minute is up

                progressBar.setProgress(0);

                progressBar.setVisibility(View.GONE);

                btn_back.setVisibility(View.VISIBLE);

                Glide.with(PetLoverLoaderActivity.this)
                        .load(R.drawable.warning)
                        .into(logo);


                txt_title.setText("Oops!!!");

                txt_subtitle.setText("No Nanny available at this time.. Please try again.");

                btn_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showPaymentSuccessalert();
                    }
                });

            };
        }.start();

    /*    new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                *//* Create an Intent that will start the MainActivity. *//*

            }
        }, SPLASH_TIME_OUT);*/
    }

    @Override
    public void onBackPressed() {
        /*super.onBackPressed();*/
    }

    @SuppressLint("LogNotTimber")
    private void findSPResponseCall() {

        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<FindServiceProviderResponse> call = ApiService.findSPResponseCall(RestUtils.getContentType(),FindServiceProviderRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<FindServiceProviderResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<FindServiceProviderResponse> call, @NonNull Response<FindServiceProviderResponse> response) {

                Log.w(TAG, "FindServiceProviderResponse" + "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        if(response.body().getData() != null) {
                            dataBean = response.body().getData();

                            if(dataBean.getSp_id()!=null&&!dataBean.getSp_id().isEmpty()){

                                countDownTimer.cancel();

                                gotoServiceDetailScreen(dataBean.getSp_id());


                            }

                            else {


                            }
                        }

                    }

                }

            }



            @Override
            public void onFailure(@NonNull Call<FindServiceProviderResponse> call, @NonNull Throwable t) {


                Log.w(TAG,"FindServiceProviderResponse flr"+"--->" + t.getMessage());
            }
        });

    }

    private void gotoServiceDetailScreen(String id) {

        Intent intent = new Intent(PetLoverLoaderActivity.this, PetLoverServiceDetailScreenActivity.class);
        intent.putExtra("spid",id);
        intent.putExtra("catid",catid);
        intent.putExtra("radioValue",radioValue);
        intent.putExtra("subcatid",subcatid);
        intent.putExtra("servname",servname);
        intent.putExtra("subservname",subservname);
        intent.putExtra("icon_banner",icon_banner);
        intent.putExtra("serviceamount",serviceamount);
        intent.putExtra("servicedate",servicedate);
        intent.putExtra("servicetime",servicetime);
        intent.putExtra("catid",catid);
        intent.putExtra("from",from);
        intent.putExtra("spuserid",spuserid);
        intent.putExtra("selectedServiceTitle",selectedServiceTitle);
        intent.putExtra("serviceamount",serviceamount);
        intent.putExtra("servicetime",servicetime);
        intent.putExtra("SP_ava_Date",SP_ava_Date);
        intent.putExtra("selectedTimeSlot",selectedTimeSlot);
        intent.putExtra("distance",distance);
        intent.putExtra("fromactivity",TAG);
        intent.putExtra("count_number",count_number);
        intent.putExtra("total_amount",total_amount);
        intent.putExtra("state",state);
        intent.putExtra("street",street);
        intent.putExtra("landmark",landmark);
        intent.putExtra("pincode",pincode);
        intent.putExtra("address_type",address_type);
        intent.putExtra("city",city);
        Log.w(TAG,"gotoServiceBookAppoinment : "+"SP_ava_Date : "+SP_ava_Date);
        startActivity(intent);
    }

    private FindServiceProviderRequest FindServiceProviderRequest() {
        FindServiceProviderRequest FindServiceProviderRequest = new FindServiceProviderRequest();
        FindServiceProviderRequest.setAppointment_id(appointment_id);
        Log.w(TAG,"FindServiceProviderRequest"+ "--->" + new Gson().toJson(FindServiceProviderRequest));
        return FindServiceProviderRequest;
    }


    private void showPaymentSuccessalert() {

        Intent intent = new Intent(getApplicationContext(), Service_Details_Activity.class);
        intent.putExtra("spid",spid);
        intent.putExtra("catid",catid);
        intent.putExtra("subcatid",subcatid);
        intent.putExtra("servname",servname);
        intent.putExtra("subservname",subservname);
        intent.putExtra("icon_banner",icon_banner);
        intent.putExtra("serviceamount",serviceamount);
        intent.putExtra("servicedate",servicedate);
        intent.putExtra("servicetime",servicetime);
        intent.putExtra("catid",catid);
        intent.putExtra("from",from);
        intent.putExtra("spuserid",spuserid);
        intent.putExtra("selectedServiceTitle",selectedServiceTitle);
        intent.putExtra("serviceamount",serviceamount);
        intent.putExtra("servicetime",servicetime);
        intent.putExtra("SP_ava_Date",SP_ava_Date);
        intent.putExtra("selectedTimeSlot",selectedTimeSlot);
        intent.putExtra("distance",distance);
        intent.putExtra("fromactivity",TAG);
        intent.putExtra("state",state);
        intent.putExtra("street",street);
        intent.putExtra("landmark",landmark);
        intent.putExtra("pincode",pincode);
        intent.putExtra("address_type",address_type);
        intent.putExtra("city",city);
        intent.putExtra("fromactivity",TAG);

        Log.w(TAG,"gotoServiceBookAppoinment : "+"SP_ava_Date : "+SP_ava_Date);
        startActivity(intent);

    }




}