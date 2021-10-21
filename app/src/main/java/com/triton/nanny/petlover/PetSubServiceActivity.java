package com.triton.nanny.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.Gson;
import com.triton.nanny.R;
import com.triton.nanny.adapter.PetSubServicesAdapter;
import com.triton.nanny.api.APIClient;
import com.triton.nanny.api.RestApiInterface;
import com.triton.nanny.requestpojo.SubServiceCatRequest;
import com.triton.nanny.responsepojo.DoctorSearchResponse;
import com.triton.nanny.responsepojo.SubServiceCatResponse;
import com.triton.nanny.service.GPSTracker;
import com.triton.nanny.sessionmanager.SessionManager;
import com.triton.nanny.utils.ConnectionDetector;
import com.triton.nanny.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetSubServiceActivity extends AppCompatActivity {


    private String TAG = "PetSubServiceActivity";



    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;





    String token = "";
    String type ="";
    String name = "",patientid = "";

    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    double latitude, longitude;

    private Handler handler = new Handler();
    Runnable runnable;
    private TextView headertitle;






    Dialog dialog;
    private String userid;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_popular_services)
    RecyclerView rv_popular_services;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    private ShimmerFrameLayout mShimmerViewContainer;
    private View includelayout;


    private String AddressLine;
    private SharedPreferences preferences;
    private Context mContext;

    private static final int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private GoogleApiClient googleApiClient;
    Location mLastLocation;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private GoogleMap mMap;
    private GPSTracker gpsTracker;
    private SupportMapFragment mapFragment;
    private List<DoctorSearchResponse.DataBean> doctorDetailsResponseList;
    private Dialog alertDialog;
    private String searchString = "";

    private int reviewcount;
    private String fromactivity,specialization;
    private List<SubServiceCatResponse.DataBean> serviceCatList;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;

    private String catid = "";
    private String from,servname;

    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_sub_service);
        ButterKnife.bind(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(PetSubServiceActivity.this);

        mContext = PetSubServiceActivity.this;

        gpsTracker = new GPSTracker(mContext);


        avi_indicator.setVisibility(View.GONE);
        includelayout = findViewById(R.id.includelayout);
        mShimmerViewContainer = includelayout.findViewById(R.id.shimmer_layout);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            catid = extras.getString("catid");
            servname = extras.getString("servname");
            from = extras.getString("from");
            fromactivity = extras.getString("fromactivity");
            flag = extras.getBoolean("flag");

        }


        Log.w(TAG," userid : "+userid+ " catid : "+catid+" from : "+from+" flag : "+flag);

        Log.w(TAG," servname : "+servname);


        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);

        if(servname!=null&&!servname.isEmpty()){

            toolbar_title.setText(servname);

        }
        else {


            toolbar_title.setText(getResources().getString(R.string.subservice_details));
        }

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });


//        if(getArguments() != null){
//            fromactivity = getArguments().getString("fromactivity");
//            reviewcount = getArguments().getInt("reviewcount");
//            specialization = getArguments().getString("specialization");
//            Log.w(TAG,"fromactivity : "+fromactivity+" reviewcount : "+reviewcount+" specialization : "+specialization);
//
//        }





        SessionManager sessionManager = new SessionManager(mContext);
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        Log.w(TAG,"customerid-->"+userid);




        if (new ConnectionDetector(mContext).isNetworkAvailable(mContext)) {
            SubServiceCatResponseCall();
        }

    }


    @SuppressLint("LogNotTimber")
    private void SubServiceCatResponseCall() {
      /*  avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();*/
        includelayout.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmerAnimation();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SubServiceCatResponse> call = apiInterface.SubServiceCatResponseCall(RestUtils.getContentType(), SubServiceCatRequest());
        Log.w(TAG,"SubServiceCatResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SubServiceCatResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<SubServiceCatResponse> call, @NonNull Response<SubServiceCatResponse> response) {
                //avi_indicator.smoothToHide();
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                Log.w(TAG,"SubServiceCatResponseCall" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {


                        if (response.body().getData() != null) {
                            serviceCatList = response.body().getData();
                            if(serviceCatList != null && serviceCatList.size()>0){
                                txt_no_records.setVisibility(View.GONE);
                                rv_popular_services.setVisibility(View.VISIBLE);
                                setViewPetServices(serviceCatList);
                            }else{
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No Sub service");
                                rv_popular_services.setVisibility(View.GONE);


                            }



                        }



                    }
                    else {
                        showErrorLoading(response.body().getMessage());
                    }

                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<SubServiceCatResponse> call, @NonNull Throwable t) {
                // avi_indicator.smoothToHide();
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                Log.e(" SubServiceCatResponse flr", "--->" + t.getMessage());
            }
        });

    }

    @SuppressLint("LogNotTimber")
    private SubServiceCatRequest SubServiceCatRequest() {


        /**
         * server_id : 602d1fc0562e0916bc9b3245
         */

        SubServiceCatRequest SubServiceCatRequest = new SubServiceCatRequest();
        SubServiceCatRequest.setService_id(catid);
        Log.w(TAG,"SubServiceCatRequest"+ new Gson().toJson(SubServiceCatRequest));
        return SubServiceCatRequest;
    }

    private void setViewPetServices(List<SubServiceCatResponse.DataBean> serviceCatList) {

        rv_popular_services.setNestedScrollingEnabled(true);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,1);
        rv_popular_services.setLayoutManager(linearLayoutManager);

        PetSubServicesAdapter petServicesAdapter = new PetSubServicesAdapter(mContext, serviceCatList,flag,servname);
        rv_popular_services.setAdapter(petServicesAdapter);
    }



    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());




        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void hideLoading(){
        try {
            alertDialog.dismiss();
        }catch (Exception ignored){

        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(PetSubServiceActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(PetSubServiceActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(PetSubServiceActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(mContext,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {


                }
            } else {
                Toast.makeText(mContext, "permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {

        if(from!=null&&from.equals("PetHome")){

            callDirections("1");

        }

        else {

            callDirections("3");
        }


    }

    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }

}