package com.triton.nanny.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.triton.nanny.R;
import com.triton.nanny.adapter.SPDetails_SpecTypesListAdapter;
import com.triton.nanny.adapter.ViewPagerSPDetailsGalleryAdapter;
import com.triton.nanny.doctor.DoctorDashboardActivity;
import com.triton.nanny.responsepojo.CartDetailsResponse;
import com.triton.nanny.responsepojo.SPDetailsRepsonse;
import com.triton.nanny.serviceprovider.ServiceProviderDashboardActivity;
import com.triton.nanny.sessionmanager.SessionManager;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetloverChooseServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "PetloverChooseServiceActivity";

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
    @BindView(R.id.pager)
    ViewPager viewPager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tabDots)
    TabLayout tabLayout;

    SPDetails_SpecTypesListAdapter spDetails_specTypesListAdapter;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;




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

    private String location;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_book_now)
    LinearLayout ll_book_now;

    List<SPDetailsRepsonse.DataBean.BusSpecListBean> specializationBeanList;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_fav)
    ImageView img_fav;

    int quantity = 0;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_sp_bookserv)
    LinearLayout ll_sp_bookserv;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_minus)
    RelativeLayout rl_minus;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_add)
    RelativeLayout rl_add;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_count_number)
    TextView txt_count_number;

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

    String fromactivity,SP_ava_Date,selectedTimeSlot;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petlover_choose_service);

        ButterKnife.bind(this);
        Log.w(TAG, "onCreate");


        avi_indicator.setVisibility(View.GONE);

        rl_back.setOnClickListener(this);
        ll_book_now.setOnClickListener(this);


        SessionManager session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);


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


            viewPager.setVisibility(View.GONE);

            tabLayout.setVisibility(View.GONE);

//        txt_sp_companyname.setVisibility(View.GONE);
//
//        txt_sp_name.setVisibility(View.GONE);

            rl_add.setOnClickListener(this);

            rl_minus.setOnClickListener(this);

            ll_sp_bookserv.setOnClickListener(this);


        }

        img_fav.setVisibility(View.GONE);

        setBottomSheet();

        SPDetailsRepsonse.DataBean.BusServiceGallBean busSpecListBean = new SPDetailsRepsonse.DataBean.BusServiceGallBean();

        busSpecListBean.setBus_service_gall("http://54.212.108.156:3000/api/uploads/1624556489164.png");

        spServiceGalleryResponseList = new ArrayList<>();

        spServiceGalleryResponseList.add(busSpecListBean);

        if(spServiceGalleryResponseList != null && spServiceGalleryResponseList.size()>0){

            for (int i = 0; i < spServiceGalleryResponseList.size(); i++) {
                spServiceGalleryResponseList.get(i).getBus_service_gall();
                Log.w(TAG, "RES" + ", " +  spServiceGalleryResponseList.get(i).getBus_service_gall());
            }

            viewPager.setVisibility(View.VISIBLE);

            tabLayout.setVisibility(View.VISIBLE);

            viewpageData(spServiceGalleryResponseList);

        }
    }

    public void increment (View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement (View view) {
        if (quantity>0){
            quantity = quantity - 1;
            display(quantity);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        txt_count_number.setText("" + number);
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

    /**
     * method to setup the bottomsheet
     */
    private void setBottomSheet() {

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayoutsp));

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);

        bottomSheetBehavior.setHideable(false);

        bottomSheetBehavior.setFitToContents(false);

        bottomSheetBehavior.setHalfExpandedRatio(0.7f);


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
        switch (v.getId()) {
            case R.id.rl_back:
                onBackPressed();
                break;
            case R.id.ll_sp_bookserv:
                showPaymentSuccessalert();
                break;

            case R.id.rl_add:
                increment(v);
                break;

            case R.id.rl_minus:
                decrement(v);
                break;
        }
    }


    private void showPaymentSuccessalert() {
        try {

            dialog = new Dialog(PetloverChooseServiceActivity.this);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_payment_success_layout);
            Button btn_back_to_shop = dialog.findViewById(R.id.btn_back_to_shop);

            btn_back_to_shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
                    intent.putExtra("tag","1");
                    startActivity(intent);
                    finish();


                }
            });
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }

    }

}