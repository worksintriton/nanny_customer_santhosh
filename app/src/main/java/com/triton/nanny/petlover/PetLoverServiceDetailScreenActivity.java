package com.triton.nanny.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.triton.nanny.R;

import com.triton.nanny.adapter.SPDetails_SpecTypesListAdapter;
import com.triton.nanny.adapter.ServiceDetailsAdapter;
import com.triton.nanny.adapter.ViewPagerSPDetailsGalleryAdapter;
import com.triton.nanny.api.APIClient;
import com.triton.nanny.api.RestApiInterface;
import com.triton.nanny.requestpojo.SPCreateAppointmentRequest;
import com.triton.nanny.requestpojo.SPDetailScreenRequest;
import com.triton.nanny.requestpojo.SPDetailsRequest;
import com.triton.nanny.requestpojo.SPFavCreateRequest;

import com.triton.nanny.responsepojo.CartDetailsResponse;
import com.triton.nanny.responsepojo.SPCreateAppointmentResponse;
import com.triton.nanny.responsepojo.SPDetailScreenResponse;
import com.triton.nanny.responsepojo.SPDetailsRepsonse;
import com.triton.nanny.responsepojo.SPFavCreateResponse;
import com.triton.nanny.sessionmanager.SessionManager;
import com.triton.nanny.utils.ConnectionDetector;
import com.triton.nanny.utils.GridSpacingItemDecoration;
import com.triton.nanny.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
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
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetLoverServiceDetailScreenActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private String TAG = "PetLoverServiceDetailScreenActivity";

    // BottomSheetBehavior variable
    @SuppressWarnings("rawtypes")
    public BottomSheetBehavior bottomSheetBehavior;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pager)
    ViewPager viewPager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tabDots)
    TabLayout tabLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img1)
    ImageView hand_img1;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img2)
    ImageView hand_img2;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img3)
    ImageView hand_img3;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img4)
    ImageView hand_img4;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img5)
    ImageView hand_img5;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_sp_companyname)
    TextView txt_sp_companyname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_spname)
    TextView txt_sp_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_dr_consultationfees)
    TextView txt_dr_consultationfees;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_distance)
    TextView txt_distance;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_speclist)
    RecyclerView rv_speclist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_seemore_spec)
    TextView txt_seemore_spec;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_selectedserviceimage)
    ImageView img_selectedserviceimage;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_selected_servicesname)
    TextView txt_selected_servicesname;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_place)
    TextView txt_place;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_spec_label)
    TextView txt_spec_label;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_location_label)
    TextView txt_location_label;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pet_hanldle)
    TextView txt_pet_hanldle;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txtlbl_service)
    TextView txtlbl_service;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_services_list)
    RecyclerView rv_services_list;

    SPDetails_SpecTypesListAdapter spDetails_specTypesListAdapter;

    private String active_tag;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;




    private String userid;
    private String spid,catid;
    private List<SPDetailScreenResponse.DataBean.BusServiceGallBean> spServiceGalleryResponseList;
    private String from;
    private String spuserid;
    private String selectedServiceTitle;
    private String servicetime;
    private int serviceamount;


    private String serviceprovidingcompanyname = "";
    private String spprovidername = "";
    private int ratingcount;

    private String location;

    private SupportMapFragment mapFragment;
    private double latitude;
    private double longitude;
    private GoogleMap mMap;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_root1)
    LinearLayout ll_root1;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_root2)
    LinearLayout ll_root2;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_root3)
    LinearLayout ll_root3;



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_popular_serv)
    LinearLayout ll_popular_serv;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_map)
    LinearLayout ll_map;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_book_now)
    LinearLayout ll_book_now;

    List<SPDetailScreenResponse.DataBean.BusSpecListBean> specializationBeanList;

    String serv_name,selectedServiceImagepath,count_number,total_amount;


    private String distance;
    private int reviewcount;
    private int Count_value_start;
    private int Count_value_end;

    String fromactivity,SP_ava_Date,selectedTimeSlot,subcatid,subservname,servname,icon_banner,servicedate;


    List<CartDetailsResponse.DataBean> Data = new ArrayList<>();

    private int prodouct_total;

    private int shipping_charge;

    private int discount_price;

    private int grand_total;

    private int prodcut_count;

    private int prodcut_item_count;

    String  name, phonum, state, street, landmark_pincode, address_type, date, shipid;

    String first_name,last_name,flat_no,landmark,pincode,alt_phonum,address_status,city,username,radioValue;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_servicename)
    TextView txt_servicename;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_sub_servicename)
    TextView txt_sub_servicename;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_name)
    TextView txt_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_phone)
    TextView txt_phone;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_dateofbooking)
    TextView txt_dateofbooking;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_timing)
    TextView txt_timing;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_noofhours)
    TextView txt_noofhours;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_totalcost)
    TextView txt_totalcost;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_paymenttype)
    TextView txt_paymenttype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_lover_service_detail_screen);
        ButterKnife.bind(this);
        Log.w(TAG,"onCreate");



       // txt_seemore_spec.setVisibility(View.GONE);
        avi_indicator.setVisibility(View.GONE);
   /*     ll_book_now.setVisibility(View.GONE);

       //rl_back.setOnClickListener(this);

*/
        ll_book_now.setOnClickListener(this);
        SessionManager session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            spid = extras.getString("spid");
            catid = extras.getString("catid");
            from = extras.getString("from");

            selectedServiceTitle = extras.getString("selectedServiceTitle");
            serviceamount = extras.getInt("serviceamount");
            servicetime = extras.getString("servicetime");
           // distance = extras.getInt("distance");
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
            count_number = extras.getString("count_number");
            total_amount = extras.getString("total_amount");
            state = extras.getString("state");
            street = extras.getString("street");
            landmark = extras.getString("landmark");
            pincode = extras.getString("pincode");
            address_type = extras.getString("address_type");
            city = extras.getString("city");
            radioValue = extras.getString("radioValue");

            if(servname != null){
                txt_servicename.setText(servname);
            } if(subservname != null){
                txt_sub_servicename.setText(subservname);
            }if(servicedate != null){
                txt_dateofbooking.setText(servicedate);
            }if(servicetime != null){
                txt_timing.setText(servicetime);
            }if(count_number != null){
                txt_noofhours.setText(count_number);
            }if(total_amount != null){
                txt_totalcost.setText("\u20B9 "+total_amount);
            }
            if(count_number != null){
                txt_noofhours.setText(count_number);
            }if(radioValue != null){
                txt_paymenttype.setText(radioValue);
            }



            Log.w(TAG,"spid : "+spid +" catid : "+catid+"subcatid : "+subcatid+"  from : "+from);

            Log.w(TAG,"servname : "+servname +" subservname : "+subservname+"icon_banner : "+icon_banner+"  serviceamount : "+serviceamount);

            Log.w(TAG,"servicetime : "+servicetime +" servicedate : "+servicetime);


            Log.w(TAG,"distance : "+distance);

        }

///*
//
//        Log.w(TAG," userid : "+userid+ " spid : "+spid+" catid : "+catid+" from : "+from+" distance : "+distance);
//
//        if(distance!=null&&!distance.isEmpty()){
//
//            APIClient.SP_DISTANCE = distance;
//        }
//        if(spid != null && userid != null) {
            if (new ConnectionDetector(PetLoverServiceDetailScreenActivity.this).isNetworkAvailable(PetLoverServiceDetailScreenActivity.this)) {
                SPDetailsRepsonseCall();
            }
//        }

        txt_seemore_spec.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(txt_seemore_spec.getText().toString() != null && txt_seemore_spec.getText().toString().equalsIgnoreCase("See more...")){
                    txt_seemore_spec.setText("Hide");
                    int size =specializationBeanList.size();
                    setSpecList(specializationBeanList,size);
                }else{
                    txt_seemore_spec.setText("See more...");
                    int size = 4;
                    setSpecList(specializationBeanList,size);

                }

            }
        });




        viewPager.setVisibility(View.GONE);

        tabLayout.setVisibility(View.GONE);

        hand_img1.setVisibility(View.GONE);

        hand_img2.setVisibility(View.GONE);

        hand_img3.setVisibility(View.GONE);

        hand_img4.setVisibility(View.GONE);

        hand_img5.setVisibility(View.GONE);

        txt_sp_companyname.setVisibility(View.GONE);

        txt_sp_name.setVisibility(View.GONE);

        ll_root1.setVisibility(View.GONE);

        ll_root3.setVisibility(View.GONE);

        txt_spec_label.setVisibility(View.GONE);

        rv_speclist.setVisibility(View.GONE);

        txt_pet_hanldle.setVisibility(View.GONE);

        ll_popular_serv.setVisibility(View.GONE);

        txt_location_label.setVisibility(View.GONE);

        txt_place.setVisibility(View.GONE);

        ll_map.setVisibility(View.GONE);

        ll_book_now.setVisibility(View.GONE);

        txt_seemore_spec.setVisibility(View.GONE);
        //setBottomSheet();


        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(this);
        }
////
////        img_fav.setOnClickListener(this);
    }


/*
 * method to setup the bottomsheet
 *//*

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

                gotoSPAvailableTimeActivity();
                break;

        }
    }



    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }
    private void gotoSPAvailableTimeActivity() {
        Intent intent = new Intent(getApplicationContext(),PetLoverDashboardActivity.class);
        intent.putExtra("tag","1");
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
//
//super.onBackPressed();
//        if(from != null && from.equalsIgnoreCase("PetLoverSPNewFavAdapter")){
//            Intent intent = new Intent(getApplicationContext(),PetloverFavListActivity.class);
//            startActivity(intent);
//            finish();
//        }else{
//            Intent intent = new Intent(getApplicationContext(),SelectedServiceActivity.class);
//            intent.putExtra("spid",spid);
//            intent.putExtra("catid",catid);
//            intent.putExtra("from",from);
//            intent.putExtra("distance",distance);
//            intent.putExtra("reviewcount",reviewcount);
//            intent.putExtra("Count_value_start",Count_value_start);
//            intent.putExtra("Count_value_end",Count_value_end);
//            startActivity(intent);
//            finish();
//        }



    }
    @SuppressLint("LogNotTimber")
    private void SPDetailsRepsonseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SPDetailScreenResponse> call = apiInterface.SPDetailScreenRepsonseCall(RestUtils.getContentType(), spDetailScreenRequest());
        Log.w(TAG,"SPDetailsRepsonseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SPDetailScreenResponse>() {
            @SuppressLint({"SetTextI18n", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<SPDetailScreenResponse> call, @NonNull Response<SPDetailScreenResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SPDetailsRepsonse" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        if(response.body().getData()!=null){


                            if(response.body().getData().getBus_user_name() != null){
                                txt_name.setText(response.body().getData().getBus_user_name());
                            } if(response.body().getData().getBus_user_phone() != null){
                                txt_phone.setText(response.body().getData().getBus_user_phone());
                            }

                            viewPager.setVisibility(View.VISIBLE);
                            //ll_book_now.setVisibility(View.VISIBLE);

                            tabLayout.setVisibility(View.VISIBLE);

                            hand_img1.setVisibility(View.VISIBLE);

                            hand_img2.setVisibility(View.VISIBLE);

                            hand_img3.setVisibility(View.VISIBLE);

                            hand_img4.setVisibility(View.VISIBLE);

                            hand_img5.setVisibility(View.VISIBLE);

                            txt_sp_companyname.setVisibility(View.VISIBLE);

                            txt_sp_name.setVisibility(View.VISIBLE);

                            ll_root1.setVisibility(View.VISIBLE);

                            ll_root3.setVisibility(View.VISIBLE);

                            ll_book_now.setVisibility(View.VISIBLE);

                            txt_spec_label.setVisibility(View.VISIBLE);

                            rv_speclist.setVisibility(View.VISIBLE);

                            txt_pet_hanldle.setVisibility(View.VISIBLE);

                            ll_popular_serv.setVisibility(View.VISIBLE);

                            txt_location_label.setVisibility(View.VISIBLE);

                            txt_place.setVisibility(View.VISIBLE);

                            ll_map.setVisibility(View.VISIBLE);

                            setBottomSheet();

                            if(response.body().getData().getBus_service_gall() != null) {
                                spServiceGalleryResponseList = response.body().getData().getBus_service_gall();
                            }
                            if(response.body().getData().getBussiness_name() != null) {
                                serviceprovidingcompanyname = response.body().getData().getBussiness_name();
                            }
                            if(response.body().getData().getBus_user_name() != null) {
                                spprovidername = response.body().getData().getBus_user_name();
                            }

                            Log.w(TAG,"RatingCount : "+response.body().getData().getRating());
                            if(response.body().getData().getRating() != 0) {
                                ratingcount = response.body().getData().getRating();
                            }

                            if(response.body().getData().getUser_id() != null) {
                                spuserid = response.body().getData().getUser_id();
                            }


//                        if(response.body().getData().getDistance() != 0) {
//                             distance = response.body().getData().getDistance();
//                             txt_distance.setText(distance+"");
//
//                        }else{
//                            distance = 0;
//                            txt_distance.setText(0+"");
//                        }
                            if( response.body().getData().getSp_loc() != null) {
                                location = response.body().getData().getSp_loc();

                                latitude = response.body().getData().getSp_lat();

                                longitude = response.body().getData().getSp_long();

                                Log.w(TAG,"latitude"+ latitude );

                                Log.w(TAG,"longitude"+ longitude );



                                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                                mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                        .findFragmentById(R.id.map);


                                if (mapFragment != null) {
                                    mapFragment.getMapAsync(PetLoverServiceDetailScreenActivity.this);
                                }

                                // mapFragment.getMapAsync(PetLoverServiceDetailScreenActivity.this);

                            }
                      /*  if(response.body().getDetails().getImage_path() != null) {
                            selectedServiceImagepath = response.body().getDetails().getImage_path();
                        }



                        if(response.body().getDetails().getTitle() != null) {
                            selectedServiceTitle = response.body().getDetails().getTitle();
                        }
                        if(response.body().getDetails().getAmount() != 0) {
                            serviceamount = response.body().getDetails().getAmount();

                            txt_dr_consultationfees.setText("INR "+serviceamount);


                        }

                        if(response.body().getDetails().getTitle() != null) {

                            serv_name = response.body().getDetails().getTitle();
                        }
*/
                            if(serv_name != null && !serv_name.isEmpty()){
                                txt_selected_servicesname.setText(serv_name);

                            }

                            if(icon_banner != null && !icon_banner.isEmpty()){
                                Glide.with(PetLoverServiceDetailScreenActivity.this)
                                        .load(icon_banner)
                                        .into(img_selectedserviceimage);

                            }

                            else {

                                img_selectedserviceimage.setImageResource(R.drawable.no_image);
                            }


                            if(subservname!= null) {

                                txt_selected_servicesname.setText(subservname);
                            }

                      /*  if(response.body().getDetails().getTime() != null) {
                            servicetime = response.body().getDetails().getTime();
                        }

                        if(response.body().getDetails().getTime() != null) {
                            servicetime = response.body().getDetails().getTime();
                        }*/
                            if(response.body().getData().getUser_id() != null) {
                                spuserid = response.body().getData().getUser_id();

                                spCreateAppointmentResponseCall();
                            }

                            if(serviceprovidingcompanyname != null && !serviceprovidingcompanyname.isEmpty()){
                                txt_sp_companyname.setText(serviceprovidingcompanyname);
                            }
                            if(spprovidername != null && !spprovidername.isEmpty()){
                                txt_sp_name.setText(spprovidername);
                            }
                            if(ratingcount != 0 ) {

                                if(ratingcount == 1){
                                    hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                    hand_img2.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                    hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                    hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                    hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                } else if(ratingcount == 2){
                                    hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                    hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                                    hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                    hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                    hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                }else if(ratingcount == 3){
                                    hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                    hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                                    hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
                                    hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                    hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                }else if(ratingcount == 4){
                                    hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                    hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                                    hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
                                    hand_img4.setBackgroundResource(R.drawable.ic_logo_color);
                                    hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                } else if(ratingcount == 5){
                                    hand_img1.setBackgroundResource(R.drawable.ic_logo_color);
                                    hand_img2.setBackgroundResource(R.drawable.ic_logo_color);
                                    hand_img3.setBackgroundResource(R.drawable.ic_logo_color);
                                    hand_img4.setBackgroundResource(R.drawable.ic_logo_color);
                                    hand_img5.setBackgroundResource(R.drawable.ic_logo_color);
                                }


                            }
                            else{
                                hand_img1.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img2.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img3.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img4.setBackgroundResource(R.drawable.ic_logo_graycolor);
                                hand_img5.setBackgroundResource(R.drawable.ic_logo_graycolor);
                            }
                            if(location != null && !location.isEmpty()){
                                txt_place.setText(location);
                            }

                            if(distance != null&&!distance.isEmpty()){

                                txt_distance.setText(distance+" KM Away");

                            }
                            else if(APIClient.SP_DISTANCE != null&&!APIClient.SP_DISTANCE.isEmpty()){

                                txt_distance.setText(APIClient.SP_DISTANCE+" KM Away");

                            }


                            if(spServiceGalleryResponseList != null && spServiceGalleryResponseList.size()>0){

                                for (int i = 0; i < spServiceGalleryResponseList.size(); i++) {
                                    spServiceGalleryResponseList.get(i).getBus_service_gall();
                                    Log.w(TAG, "RES" + ", " +  spServiceGalleryResponseList.get(i).getBus_service_gall());
                                }


                                viewpageData(spServiceGalleryResponseList);

                            }

                            if(response.body().getData().getBus_spec_list() != null&&response.body().getData().getBus_spec_list().size()>0){

                                // specializationBeanList = new ArrayList<>();

                                specializationBeanList=response.body().getData().getBus_spec_list();

                                Log.w(TAG,"SpecilaziationList : "+new Gson().toJson(specializationBeanList));
                                Log.w(TAG,"SpecilaziationList size: "+specializationBeanList.size());

                                setSpecList(specializationBeanList,4);



                            }



                            if(response.body().getData().getBus_service_list() != null && response.body().getData().getBus_service_list().size()>0 ) {
                                rv_services_list.setVisibility(View.VISIBLE);
                                setServiceList(response.body().getData().getBus_service_list());
                            } else{
                                rv_services_list.setVisibility(View.GONE);
                            }

                               


                        }

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<SPDetailScreenResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SPDetailsRepsonse flr"+ t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setServiceList(List<SPDetailScreenResponse.DataBean.BusServiceListBean> bus_service_list) {
        rv_services_list.setLayoutManager(new GridLayoutManager(this, 1));
        rv_services_list.setItemAnimator(new DefaultItemAnimator());
        ServiceDetailsAdapter serviceDetailsAdapter = new ServiceDetailsAdapter(getApplicationContext(), bus_service_list);
        rv_services_list.setAdapter(serviceDetailsAdapter);
    }

    private void viewpageData(List<SPDetailScreenResponse.DataBean.BusServiceGallBean> spServiceGalleryResponseList) {
        tabLayout.setupWithViewPager(viewPager, true);

        List<SPDetailsRepsonse.DataBean.BusServiceGallBean> spServiceGalleryDetailResponseList = new ArrayList<>();

        for (int i=0;i<spServiceGalleryResponseList.size();i++){

            SPDetailsRepsonse.DataBean.BusServiceGallBean busServiceGallBean = new SPDetailsRepsonse.DataBean.BusServiceGallBean();

            busServiceGallBean.setBus_service_gall(spServiceGalleryResponseList.get(i).getBus_service_gall());

            spServiceGalleryDetailResponseList.add(busServiceGallBean);


        }

        ViewPagerSPDetailsGalleryAdapter viewPagerSPDetailsGalleryAdapter = new ViewPagerSPDetailsGalleryAdapter(getApplicationContext(), spServiceGalleryDetailResponseList);
        viewPager.setAdapter(viewPagerSPDetailsGalleryAdapter);


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
    private SPDetailScreenRequest spDetailScreenRequest() {



        SPDetailScreenRequest spDetailsRequest = new SPDetailScreenRequest();
        spDetailsRequest.setSp_id(spid);

        Log.w(TAG,"spSpecificServiceDetailsRequest "+ new Gson().toJson(spDetailsRequest));
        return spDetailsRequest;
    }



    @SuppressLint({"LongLogTag", "LogNotTimber"})
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        Log.w(TAG,"Map latitude"+ latitude );

        Log.w(TAG,"Map longitude"+ longitude );

        if(latitude!=0&&longitude!=0){

            LatLng currentLocation = new LatLng(latitude, longitude);

            mMap.addMarker(new
                    MarkerOptions().position(currentLocation));

            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15));
            // Zoom in, animating the camera.
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
            // Zoom out to zoom level 10, animating with a duration of 2 seconds.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(@NonNull LatLng latLng) {
                    Log.w(TAG,"mMap onclick : "+"latitude : "+latitude+" longitude : "+longitude+" location : "+location);
                    String strUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" + location + ")";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUri));
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(intent);
                }
            });


        }

    }



    private void setSpecList(List<SPDetailScreenResponse.DataBean.BusSpecListBean> specializationBeanList, int size) {

        int spanCount = 2; // 3 columns
        int spacing = 0; // 50px
        boolean includeEdge = true;
        rv_speclist.setLayoutManager(new GridLayoutManager(this, 2));
        rv_speclist.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        rv_speclist.setItemAnimator(new DefaultItemAnimator());
        spDetails_specTypesListAdapter = new SPDetails_SpecTypesListAdapter(PetLoverServiceDetailScreenActivity.this, specializationBeanList,size);
        rv_speclist.setAdapter(spDetails_specTypesListAdapter);

    }

    @SuppressLint("LongLogTag")
    private void spCreateAppointmentResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SPCreateAppointmentResponse> call = ApiService.SPCreateAppointmentResponseCall(RestUtils.getContentType(),spCreateAppointmentRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SPCreateAppointmentResponse>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(@NonNull Call<SPCreateAppointmentResponse> call, @NonNull Response<SPCreateAppointmentResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SPCreateAppointmentResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(response.body().getMessage() != null){

                        }
                    }
                    else{
                        if(response.body().getMessage() != null){


                        }


                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<SPCreateAppointmentResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"SPCreateAppointmentResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private SPCreateAppointmentRequest spCreateAppointmentRequest() {
        /*
         * sp_id : 5ff7ef9b1c72093650a13a10
         * booking_date : 23/10/2020
         * booking_time : 12:00 AM
         * booking_date_time : 23/10/2020 12:00 AM
         * user_id : 5fd841a67aa4cc1c6a1e5636
         * pet_id : 5fdc46be1e5d8b0eb31c3699
         * additional_info : this if is for the comments
         * sp_attched : []
         * sp_feedback :
         * sp_rate :
         * user_feedback :
         * user_rate :
         * display_date : 23/10/2020 10:10 AM
         * server_date_time : 23/10/2020 10:10 AM
         * payment_id : 12345
         * payment_method : Card
         * service_name : Grooming
         * service_amount : 200
         * service_time : 15 mins
         * completed_at :
         * missed_at :
         * mobile_type : Admin
         * sp_business_info : []
         * health_issue_title
         * original_price : 100
         * discount_price : 10
         * total_price : 90
         * coupon_status : String,
         *  coupon_code : String,
         */



        @SuppressLint("SimpleDateFormat") DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        @SuppressLint("SimpleDateFormat") DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = inputFormat.parse(servicedate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);
        String outputTimeStr = null;

        @SuppressLint("SimpleDateFormat") SimpleDateFormat h_mm_a   = new SimpleDateFormat("hh:mm aa");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat hh_mm_ss = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        try {
            Date d1 = h_mm_a.parse(servicetime);
            outputTimeStr =hh_mm_ss.format(d1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String displaydateandtime = outputDateStr+" "+outputTimeStr;

        List<SPCreateAppointmentRequest.SpAttchedBean> sp_attched = new ArrayList<>();


        SPCreateAppointmentRequest spCreateAppointmentRequest = new SPCreateAppointmentRequest();
        spCreateAppointmentRequest.setSp_id(spuserid);
        spCreateAppointmentRequest.setBooking_date(servicedate);
        spCreateAppointmentRequest.setBooking_time(servicetime);
        spCreateAppointmentRequest.setBooking_date_time(servicedate+" "+servicetime);
        spCreateAppointmentRequest.setUser_id(userid);
        spCreateAppointmentRequest.setPet_id("");
        spCreateAppointmentRequest.setAdditional_info("");
        spCreateAppointmentRequest.setSp_attched(sp_attched);
        spCreateAppointmentRequest.setSp_feedback("");
        spCreateAppointmentRequest.setSp_rate("");
        spCreateAppointmentRequest.setUser_feedback("");
        spCreateAppointmentRequest.setUser_rate("0");
        spCreateAppointmentRequest.setDisplay_date(displaydateandtime);
        spCreateAppointmentRequest.setServer_date_time("");
        spCreateAppointmentRequest.setPayment_id("");
        spCreateAppointmentRequest.setPayment_method(radioValue);
        spCreateAppointmentRequest.setService_name(servname);
        spCreateAppointmentRequest.setService_amount(String.valueOf(serviceamount));
        spCreateAppointmentRequest.setService_time(servicetime);
        spCreateAppointmentRequest.setCompleted_at("");
        spCreateAppointmentRequest.setMissed_at("");
        spCreateAppointmentRequest.setMobile_type("Android");
        spCreateAppointmentRequest.setDate_and_time(currentDateandTime);
        spCreateAppointmentRequest.setHealth_issue_title("");
        spCreateAppointmentRequest.setOriginal_price(serviceamount);
        spCreateAppointmentRequest.setDiscount_price(0);
        spCreateAppointmentRequest.setTotal_price(Integer.parseInt(total_amount));
        spCreateAppointmentRequest.setCoupon_status("");
        spCreateAppointmentRequest.setCoupon_code("");
        spCreateAppointmentRequest.setHrs(count_number);
        spCreateAppointmentRequest.setAddress_text(street);
        spCreateAppointmentRequest.setState(state);
        spCreateAppointmentRequest.setCity(city);
        spCreateAppointmentRequest.setPin_code(pincode);
        spCreateAppointmentRequest.setLat("");
        spCreateAppointmentRequest.setLon("");

        Log.w(TAG,"spCreateAppointmentRequest"+ "--->" + new Gson().toJson(spCreateAppointmentRequest));
        return spCreateAppointmentRequest;
    }




}