package com.triton.nannypartners.serviceprovider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.triton.nannypartners.R;
import com.triton.nannypartners.responsepojo.CartDetailsResponse;
import com.triton.nannypartners.responsepojo.SPDetailsRepsonse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class SPLoaderActivity extends AppCompatActivity {

    private String TAG = "SPLoaderActivity";

    private static final long SPLASH_TIME_OUT = 10000;

/*    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;*/

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.logo)
    ImageView logo;


    String fromactivity,SP_ava_Date,selectedTimeSlot,subcatid,subservname,servname,icon_banner,servicedate;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(+R.layout.activity_sp_loader);
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




            Log.w(TAG,"spid : "+spid +" catid : "+catid+"subcatid : "+subcatid+"  from : "+from);

            Log.w(TAG,"servname : "+servname +" subservname : "+subservname+"icon_banner : "+icon_banner+"  serviceamount : "+serviceamount);

            Log.w(TAG,"servicetime : "+servicetime +" servicedate : "+servicetime);


            Log.w(TAG,"distance : "+distance);


        }

        Glide.with(SPLoaderActivity.this)
                .load(R.drawable.calculator)
                .into(logo);


        MaterialProgressBar progressBar = (MaterialProgressBar) findViewById(R.id.progress);

        // timer for seekbar
//        final int oneMin = 1 * 60 * 1000; // 1 minute in milli seconds

        final int oneMin = 10000; // 1 minute in milli seconds

        /** CountDownTimer starts with 1 minutes and every onTick is 1 second */
        new CountDownTimer(oneMin, 1000) {
            public void onTick(long millisUntilFinished) {

                //forward progress
                long finishedSeconds = oneMin - millisUntilFinished;
                int total = (int) (((float)finishedSeconds / (float)oneMin) * 100.0);
                progressBar.setProgress(total);

//                //backward progress
//                int total = (int) (((float) millisUntilFinished / (float) oneMin) * 100.0);
//                progressBar.setProgress(total);

            }

            public void onFinish() {
                // DO something when 1 minute is up

                Intent intent = new Intent(SPLoaderActivity.this, SPInvoiceActivity.class);
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
                intent.putExtra("count_number",count_number);
                intent.putExtra("total_amount",total_amount);
                Log.w(TAG,"gotoServiceBookAppoinment : "+"SP_ava_Date : "+SP_ava_Date);
                startActivity(intent);


            }
        }.start();

    /*    new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                *//* Create an Intent that will start the SPInvoiceActivity. *//*

            }
        }, SPLASH_TIME_OUT);*/
    }

    @Override
    public void onBackPressed() {
        /*super.onBackPressed();*/
    }
}