package com.triton.nanny.petlover;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.triton.nanny.R;
import com.triton.nanny.adapter.SPDetails_SpecTypesListAdapter;
import com.triton.nanny.adapter.ViewPagerSPDetailsGalleryAdapter;
import com.triton.nanny.api.APIClient;
import com.triton.nanny.api.RestApiInterface;
import com.triton.nanny.requestpojo.SPDetailShowRequest;
import com.triton.nanny.responsepojo.SPDetailShowRepsonse;
import com.triton.nanny.responsepojo.SPDetailsRepsonse;
import com.triton.nanny.sessionmanager.SessionManager;
import com.triton.nanny.utils.ConnectionDetector;
import com.triton.nanny.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public class Service_Details_Activity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback

public class Service_Details_Activity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "Service_Details_Activity";

    // BottomSheetBehavior variable
    @SuppressWarnings("rawtypes")
    public BottomSheetBehavior bottomSheetBehavior;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_servname)
    TextView txt_servname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_serv_offer)
    TextView txt_serv_offer;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pager)
    ViewPager viewPager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tabDots)
    TabLayout tabLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_serv_price)
    TextView txt_serv_price;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_serv_rating)
    TextView txt_serv_rating;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_serv_desc)
    TextView txt_serv_desc;



    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;




    private String userid;
    private String spid,catid;
    private List<SPDetailsRepsonse.DataBean.BusServiceGallBean> spServiceGalleryResponseList =  new ArrayList<>();

    private List<SPDetailShowRepsonse.DataBean.BannerImageBean> bannerImageBeanList;

    private String from;
    private String spuserid;
    private String selectedServiceTitle;
    private String servicetime;
    private int serviceamount;


    private String serviceprovidingcompanyname = "";
    private String spprovidername = "";
    private int ratingcount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_sp_booklater)
    LinearLayout ll_sp_booklater;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_book_now)
    LinearLayout ll_book_now;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_fav)
    ImageView img_fav;
    List<SPDetailsRepsonse.DataBean.BusSpecListBean> specializationBeanList;

    String serv_name,selectedServiceImagepath;


    private String distance;
    private int reviewcount;
    private int Count_value_start;
    private int Count_value_end;


    /* Petlover Bottom Navigation */
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_home)
    RelativeLayout rl_home;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_care)
    RelativeLayout rl_care;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_care)
    TextView title_care;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_care)
    ImageView img_care;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_service)
    RelativeLayout rl_service;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_serv)
    TextView title_serv;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_serv)
    ImageView img_serv;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_shop)
    RelativeLayout rl_shop;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_shop)
    TextView title_shop;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_shop)
    ImageView img_shop;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_comn)
    RelativeLayout rl_comn;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_community)
    TextView title_community;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_community)
    ImageView img_community;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_homes)
    RelativeLayout rl_homes;

    boolean flag;
    String fromactivity,subcatid,subservname,servname,icon_banner,subservimage;

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        ButterKnife.bind(this);
        Log.w(TAG,"onCreate");

        avi_indicator.setVisibility(View.GONE);

        rl_back.setOnClickListener(this);
        ll_book_now.setOnClickListener(this);
        ll_sp_booklater.setOnClickListener(this);


        SessionManager session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);

        editor = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        editor.clear().commit();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String test = prefs.getString("isadd","");
        Log.w(TAG,"TEST "+test);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            spid = extras.getString("spid");
            catid = extras.getString("catid");
            servname = extras.getString("servname");
            subcatid = extras.getString("subcatid");
            subservname = extras.getString("subservname");
            subservimage = extras.getString("subservimage");
            from = extras.getString("from");
            distance = extras.getString("distance");
            reviewcount = extras.getInt("reviewcount");
            Count_value_start = extras.getInt("Count_value_start");
            Count_value_end = extras.getInt("Count_value_end");
            flag = extras.getBoolean("flag");
            fromactivity = extras.getString("fromactivity");

        }

        Log.w(TAG," servname : "+servname);


        /*serv*/
        title_care.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
          img_serv.setImageResource(R.drawable.grey_care);
        title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_shop.setImageResource(R.drawable.grey_shop);
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);
        title_serv.setTextColor(getResources().getColor(R.color.new_gree_color,getTheme()));
        img_serv.setImageResource(R.drawable.green_care);

        rl_home.setOnClickListener(this);
        rl_care.setOnClickListener(this);
        rl_service.setOnClickListener(this);
        rl_shop.setOnClickListener(this);
        rl_comn.setOnClickListener(this);
        rl_homes.setOnClickListener(this);



      Log.w(TAG," userid : "+userid+ " spid : "+spid+" catid : "+catid+" from : "+from+" distance : "+distance+" flag : "+flag);
//
//        if(distance!=null&&!distance.isEmpty()){
//
//            APIClient.SP_DISTANCE = distance;
//        }
//        if(spid != null && userid != null) {
            if (new ConnectionDetector(Service_Details_Activity.this).isNetworkAvailable(Service_Details_Activity.this)) {
                SPDetailsRepsonseCall();
            }
//        }
//

//
//
        viewPager.setVisibility(View.GONE);

        tabLayout.setVisibility(View.GONE);

        txt_servname.setVisibility(View.GONE);

        txt_serv_price.setVisibility(View.GONE);

        txt_serv_rating.setVisibility(View.GONE);

        txt_serv_desc.setVisibility(View.GONE);

        img_fav.setVisibility(View.GONE);

//

         setBottomSheet();

    }


    /**
     * method to setup the bottomsheet
     */
    private void setBottomSheet() {

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayoutsp));

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);

        bottomSheetBehavior.setHideable(false);

        bottomSheetBehavior.setFitToContents(false);

        bottomSheetBehavior.setHalfExpandedRatio(0.8f);


        // Capturing the callbacks for bottom sheet
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.w("Bottom Sheet Behaviour", "STATE_COLLAPSED");
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.w("Bottom Sheet Behaviour", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.w("Bottom Sheet Behaviour", "STATE_EXPANDED");
                        //  bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.w("Bottom Sheet Behaviour", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.w("Bottom Sheet Behaviour", "STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        Log.w("Bottom Sheet Behaviour", "STATE_HALF_EXPANDED");
                        break;
                }


            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {


            }


        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_back:
                onBackPressed();
                break;
                case R.id.ll_book_now:
                gotoSPAddressActivity();
                break;
            case R.id.ll_sp_booklater:
                gotoSPAvailableTimeActivity();
                break;
//                case R.id.img_fav:
//                    if (new ConnectionDetector(Service_Details_Activity.this).isNetworkAvailable(Service_Details_Activity.this)) {
//                        favResponseCall();
//                    }
//                break;

            case R.id.rl_homes:
                callDirections("1");
                break;
            case R.id.rl_home:
                callDirections("1");
                break;
            case R.id.rl_shop:
                callDirections("2");
                break;
            case R.id.rl_service:
                callDirections("3");
                break;
            case R.id.rl_care:
                callDirections("4");
                break;
            case R.id.rl_comn:
                callDirections("5");
                break;


        }
    }

    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);

    }
    private void gotoSPAddressActivity() {

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        String currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());

        Intent intent = new Intent(getApplicationContext(),ShippingAddressActivity.class);
        intent.putExtra("spid",spid);
        intent.putExtra("catid",catid);
        intent.putExtra("subcatid",subcatid);
        intent.putExtra("servname",servname);
        intent.putExtra("subservname",subservname);
        intent.putExtra("icon_banner",icon_banner);
        intent.putExtra("serviceamount",serviceamount);
        intent.putExtra("servicedate",currentDate);
        intent.putExtra("servicetime",currentTime);
        intent.putExtra("from",from);
        intent.putExtra("spuserid",spuserid);
        intent.putExtra("selectedServiceTitle",selectedServiceTitle);
        intent.putExtra("distance",distance);
        intent.putExtra("fromactivity",TAG);
        intent.putExtra("goto","service");
        startActivity(intent);

        Log.w(TAG," servname : "+servname);

    }

    private void gotoSPAvailableTimeActivity() {
        Intent intent = new Intent(getApplicationContext(),ShippingAddressActivity.class);
        intent.putExtra("spid",spid);
        intent.putExtra("catid",catid);
        intent.putExtra("subcatid",subcatid);
        intent.putExtra("servname",servname);
        intent.putExtra("subservname",subservname);
        intent.putExtra("icon_banner",icon_banner);
        intent.putExtra("serviceamount",serviceamount);
        intent.putExtra("from",from);
        intent.putExtra("spuserid",spuserid);
        intent.putExtra("selectedServiceTitle",selectedServiceTitle);
        intent.putExtra("distance",distance);
        intent.putExtra("goto","calendar");
        intent.putExtra("fromactivity",TAG);
        startActivity(intent);

        Log.w(TAG," servname : "+servname);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(from != null && from.equalsIgnoreCase("DashboardServicesAdapter")){
            Intent intent = new Intent(getApplicationContext(),PetLoverDashboardActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(Service_Details_Activity.this, PetSubServiceActivity.class);
            intent.putExtra("catid",catid);
            intent.putExtra("servname",servname);
            intent.putExtra("fromactivity",fromactivity);
            intent.putExtra("flag",flag);
            intent.putExtra("from",from);
            Log.w(TAG,"catid"+ catid);
            startActivity(intent);
            finish();
        }


    }
    @SuppressLint("LogNotTimber")
    private void SPDetailsRepsonseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SPDetailShowRepsonse> call = apiInterface.SPDetailShowRepsonseCall(RestUtils.getContentType(), spDetailsRequest());
        Log.w(TAG,"SPDetailsRepsonseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SPDetailShowRepsonse>() {
            @SuppressLint({"SetTextI18n", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<SPDetailShowRepsonse> call, @NonNull Response<SPDetailShowRepsonse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SPDetailsRepsonse" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        viewPager.setVisibility(View.VISIBLE);

                        tabLayout.setVisibility(View.VISIBLE);

                        txt_servname.setVisibility(View.VISIBLE);

                        txt_serv_price.setVisibility(View.VISIBLE);

                        txt_serv_rating.setVisibility(View.VISIBLE);

                        txt_serv_desc.setVisibility(View.VISIBLE);

                        setBottomSheet();

                        if(response.body().getData().getBanner_image() != null) {
                            bannerImageBeanList = response.body().getData().getBanner_image();
                        }


                        if(response.body().getData().getTitle()!= null) {

                           // servname = response.body().getData().getTitle();

                             txt_servname.setText(""+response.body().getData().getTitle());

                        }

                        if(response.body().getData().getOffer_title()!= null) {

                            txt_serv_offer.setText(""+response.body().getData().getOffer_title());

                        }

                        if(response.body().getData().getPrice()!= 0) {

                            serviceamount = response.body().getData().getPrice();

                            txt_serv_price.setText("\u20B9"+response.body().getData().getPrice());

                        }

                        if(response.body().getData().getRating()!= 0) {

                            txt_serv_rating.setText(""+response.body().getData().getRating());

                        }

                        if(response.body().getData().getService_des()!= null) {

                            txt_serv_desc.setText(""+response.body().getData().getService_des());

                        }
                        if(response.body().getData().getIcon_banner()!= null) {

                            icon_banner = response.body().getData().getIcon_banner();

                        }

                        if(bannerImageBeanList != null && bannerImageBeanList.size()>0){

                            for (int i = 0; i < bannerImageBeanList.size(); i++) {

                                SPDetailsRepsonse.DataBean.BusServiceGallBean busServiceGallBean = new SPDetailsRepsonse.DataBean.BusServiceGallBean();

                                busServiceGallBean.setBus_service_gall(bannerImageBeanList.get(i).getImage());

                                spServiceGalleryResponseList.add(busServiceGallBean);

                                Log.w(TAG, "RES" + ", " +  spServiceGalleryResponseList.get(i).getBus_service_gall());
                            }


                            viewpageData(spServiceGalleryResponseList);

                        }


                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<SPDetailShowRepsonse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SPDetailsRepsonse flr"+ t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void viewpageData(List<SPDetailsRepsonse.DataBean.BusServiceGallBean> spServiceGalleryResponseList) {
        tabLayout.setupWithViewPager(viewPager, true);

        ViewPagerSPDetailsGalleryAdapter viewPagerSPDetailsGalleryAdapter = new ViewPagerSPDetailsGalleryAdapter(getApplicationContext(), spServiceGalleryResponseList);
        viewPager.setAdapter(viewPagerSPDetailsGalleryAdapter);
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update =  new Runnable() {
            public void run() {
                if (currentPage == spServiceGalleryResponseList.size()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, false);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

    }
    @SuppressLint("LogNotTimber")
    private SPDetailShowRequest spDetailsRequest() {

        /**
         * sub_service_id : 6164231b65d9a57d7fc9575e
         */


        SPDetailShowRequest spDetailsRequest = new SPDetailShowRequest();
        spDetailsRequest.setSub_service_id(subcatid);
        Log.w(TAG,"spSpecificServiceDetailsRequest "+ new Gson().toJson(spDetailsRequest));
        return spDetailsRequest;
    }


}