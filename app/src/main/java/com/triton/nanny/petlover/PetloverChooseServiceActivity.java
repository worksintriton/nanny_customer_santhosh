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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.triton.nanny.R;
import com.triton.nanny.adapter.SPDetails_SpecTypesListAdapter;
import com.triton.nanny.adapter.ViewPagerSPDetailsGalleryAdapter;
import com.triton.nanny.api.APIClient;
import com.triton.nanny.api.RestApiInterface;
import com.triton.nanny.doctor.DoctorDashboardActivity;
import com.triton.nanny.requestpojo.TriggerSPSearchRequest;
import com.triton.nanny.responsepojo.CartDetailsResponse;
import com.triton.nanny.responsepojo.SPDetailsRepsonse;
import com.triton.nanny.responsepojo.TriggerSPSearchResponse;
import com.triton.nanny.serviceprovider.ServiceProviderDashboardActivity;
import com.triton.nanny.sessionmanager.SessionManager;
import com.triton.nanny.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    String fromactivity,SP_ava_Date,selectedTimeSlot,subcatid,subservname,servname,icon_banner,servicedate;

    private Dialog dialog;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_servicename)
    TextView txt_servicename;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_date)
    TextView txt_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_time)
    TextView txt_time;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cost)
    TextView txt_cost;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_sp_location)
    TextView txt_sp_location;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_total_amount)
    TextView txt_total_amount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rdGroupPayment)
    RadioGroup rdGroupPayment;

    String  name, phonum, state, street, landmark_pincode, address_type, date, shipid;

    String first_name,last_name,flat_no,landmark,pincode,alt_phonum,address_status,city,username;

    String radioValue = "Online";


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
        username = user.get(SessionManager.KEY_FIRST_NAME);


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


            /**/

            catid = extras.getString("catid");

            subcatid = extras.getString("subcatid");

            servname = extras.getString("servname");

            subservname = extras.getString("subservname");

            icon_banner = extras.getString("icon_banner");

            serviceamount = extras.getInt("serviceamount");

            servicetime = extras.getString("servicetime");

            servicedate = extras.getString("servicedate");




            state = extras.getString("state");

            street = extras.getString("street");

            landmark = extras.getString("landmark");

            pincode = extras.getString("pincode");

            address_type = extras.getString("address_type");

            city = extras.getString("city");


            Log.w(TAG,"spid : "+spid +" catid : "+catid+"subcatid : "+subcatid+"  from : "+from);

            Log.w(TAG,"servname : "+servname +" subservname : "+subservname+"icon_banner : "+icon_banner+"  serviceamount : "+serviceamount);

            Log.w(TAG,"servicetime : "+servicetime +" servicedate : "+servicetime);


            Log.w(TAG,"distance : "+distance);



            viewPager.setVisibility(View.GONE);

            tabLayout.setVisibility(View.GONE);

//        txt_sp_companyname.setVisibility(View.GONE);
//
//        txt_sp_name.setVisibility(View.GONE);

            rl_add.setOnClickListener(this);

            rl_minus.setOnClickListener(this);

            ll_sp_bookserv.setOnClickListener(this);

            rdGroupPayment.setOnCheckedChangeListener(new  RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    RadioButton rb = (RadioButton) group.findViewById(checkedId);
                    if (null != rb) {

                        // checkedId is the RadioButton selected
                        switch (checkedId) {
                            case R.id.rdonline:
                                // Do Something
                                radioValue="Online";
                                break;

                            case R.id.rdcod:
                                // Do Something
                                radioValue="Cash";
                                break;

                        }
                    }
                }

            });


        }

        img_fav.setVisibility(View.GONE);

        setBottomSheet();

        SPDetailsRepsonse.DataBean.BusServiceGallBean busSpecListBean = new SPDetailsRepsonse.DataBean.BusServiceGallBean();

        if(icon_banner!=null){

            busSpecListBean.setBus_service_gall(icon_banner);

        }

        else {

            busSpecListBean.setBus_service_gall("http://54.212.108.156:3000/api/uploads/1624556489164.png");

        }

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

        if(servname!=null){

            txt_servicename.setText(servname);
        }

        if(serviceamount!=0){

            txt_cost.setText(""+serviceamount);

            txt_total_amount.setText(""+serviceamount);
        }

        if(servicedate!=null){

            txt_date.setText(servicedate);
        }

        if(servicetime!=null){

            txt_time.setText(servicetime);
        }



        if(street==null){

           street="";
        }

        if(state==null){

            state="";
        }

        if(city==null){

            city="";
        }

        if(pincode==null){

            pincode="";
        }

        txt_sp_location.setText(street+" "+state+" "+city+" "+pincode);

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

        int calcul = number * serviceamount;

        txt_total_amount.setText("" + calcul);
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
                triggerSPSeacrhResponseCall();
                break;

            case R.id.rl_add:
                increment(v);
                break;

            case R.id.rl_minus:
                decrement(v);
                break;
        }
    }

    private void triggerSPSeacrhResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<TriggerSPSearchResponse> call = apiInterface.triggerSPSeacrhResponseCall(RestUtils.getContentType(), TriggerSPSearchRequest());
        Log.w(TAG,"TriggerSPSearchResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<TriggerSPSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<TriggerSPSearchResponse> call, @NonNull Response<TriggerSPSearchResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "TriggerSPSearchResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {

                        if (response.body().getData()!=null) {

                            if(response.body().getData().getAppointmentid()!=null&&!response.body().getData().getAppointmentid().isEmpty()){

                                String appointment_id = response.body().getData().getAppointmentid();

                                Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                                showPaymentSuccessalert(appointment_id);
                            }

                        }

                    }

                    else {

                        Toasty.warning(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                    }


                }
            }

            @Override
            public void onFailure(@NonNull Call<TriggerSPSearchResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("TriggerSPSearchResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private TriggerSPSearchRequest TriggerSPSearchRequest() {

        /*
         * service_name : Vet Care
         * sub_service_title : Sub Service 2
         * customer_name : Mohammed imthiyas
         * location : No 2 Muthamil nager Kodugaiyur
         * selected_date : 23-10-2021
         * selected_time : 11:00 AM
         * user_id : 6164232765d9a57d7fc9575
         */

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime24hrs = simpleDateFormat.format(new Date());
        String currenttime = currentDateandTime24hrs.substring(currentDateandTime24hrs.indexOf(' ') + 1);
        String currentdate =  currentDateandTime24hrs.substring(0, currentDateandTime24hrs.indexOf(' '));
        String currentdateandtime = currentdate+"T"+currenttime+".000Z";

        TriggerSPSearchRequest TriggerSPSearchRequest = new TriggerSPSearchRequest();
        TriggerSPSearchRequest.setService_name(servname);
        TriggerSPSearchRequest.setSub_service_title(subservname);
        TriggerSPSearchRequest.setCustomer_name(username);
        TriggerSPSearchRequest.setLocation(street);
        TriggerSPSearchRequest.setSelected_date(servicedate);
        TriggerSPSearchRequest.setSelected_time(servicetime);
        TriggerSPSearchRequest.setUser_id(userid);
        TriggerSPSearchRequest.setCurrentdateandtime(currentdateandtime);

        Log.w(TAG," TriggerSPSearchRequest"+ new Gson().toJson(TriggerSPSearchRequest));
        return TriggerSPSearchRequest;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (fromactivity!=null&&!fromactivity.isEmpty()&&fromactivity.equals("PetServiceAppointment_Doctor_Date_Time_Activity")){

            Intent intent = new Intent(PetloverChooseServiceActivity.this,PetServiceAppointment_Doctor_Date_Time_Activity.class);
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

        else {

            Intent intent = new Intent(PetloverChooseServiceActivity.this,ShippingAddressActivity.class);
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

    private void showPaymentSuccessalert(String appointment_id) {



        Intent intent = new Intent(getApplicationContext(), PetLoverLoaderActivity.class);
        intent.putExtra("spid",spid);
        intent.putExtra("appointment_id",appointment_id);
        intent.putExtra("radioValue",radioValue);
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
        intent.putExtra("count_number",txt_count_number.getText().toString());
        intent.putExtra("total_amount",txt_total_amount.getText().toString());
        Log.w(TAG,"gotoServiceBookAppoinment : "+"SP_ava_Date : "+SP_ava_Date);
        startActivity(intent);

    }



}