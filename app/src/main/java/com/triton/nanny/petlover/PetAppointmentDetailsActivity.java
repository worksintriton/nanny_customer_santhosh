package com.triton.nanny.petlover;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.triton.nanny.R;
import com.triton.nanny.activity.NotificationActivity;
import com.triton.nanny.api.APIClient;
import com.triton.nanny.api.RestApiInterface;
import com.triton.nanny.doctor.DoctorPrescriptionDetailsActivity;
import com.triton.nanny.requestpojo.AddReviewRequest;
import com.triton.nanny.requestpojo.AppoinmentCancelledRequest;
import com.triton.nanny.requestpojo.AppoinmentUpdateRequest;
import com.triton.nanny.requestpojo.AppointmentDetailsRequest;
import com.triton.nanny.requestpojo.PetNewAppointmentDetailsRequest;
import com.triton.nanny.requestpojo.SPNotificationSendRequest;
import com.triton.nanny.requestpojo.TransactionCreateRequest;
import com.triton.nanny.requestpojo.TransactionHistoryRequest;
import com.triton.nanny.responsepojo.AddReviewResponse;
import com.triton.nanny.responsepojo.AppoinmentCancelledResponse;
import com.triton.nanny.responsepojo.NotificationSendResponse;
import com.triton.nanny.responsepojo.PetNewAppointmentDetailsResponse;
import com.triton.nanny.responsepojo.SPAppointmentDetailsResponse;
import com.triton.nanny.responsepojo.SuccessResponse;
import com.triton.nanny.responsepojo.TransactionHistoryResponse;
import com.triton.nanny.utils.ConnectionDetector;
import com.triton.nanny.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetAppointmentDetailsActivity extends AppCompatActivity implements View.OnClickListener, PaymentResultListener {

    private static final String TAG = "PetAppointmentDetailsActivity";


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_user)
    ImageView img_user;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_usrname)
    TextView txt_usrname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_serv_name)
    TextView txt_serv_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_serv_cost)
    TextView txt_serv_cost;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_cancel)
    Button btn_cancel;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_pay)
    Button btn_pay;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_start_stop)
    Button btn_start_stop;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_viewinvoice)
    Button btn_viewinvoice;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_servname)
    TextView txt_servname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_hrs)
    TextView txt_hrs;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_datetimeslot)
    TextView txt_datetimeslot;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_service_cost)
    TextView txt_service_cost;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_appointment_status)
    TextView txt_appointment_status;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_appointment_date)
    TextView txt_appointment_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_date)
    TextView txt_order_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_id)
    TextView txt_order_id;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_cost)
    TextView txt_order_cost;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_address)
    TextView txt_address;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.scrollablContent)
    ScrollView scrollablContent;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_footer)
    View include_petlover_footer;

    BottomNavigationView bottom_navigation_view;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;



    String appointment_id;
    String appoinment_status;
    String start_appointment_status;
    private Dialog dialog;
    private String bookedat;
    private String startappointmentstatus;
    private boolean isVaildDate;
    private String appointmentfor;
    private String spid;
    private String userid;
    private String from;
    private String userrate;
    Dialog alertDialog;
    private String appointmentid;
    private List<PetNewAppointmentDetailsResponse.DataBean.PetIdBean.PetImgBean> pet_image;
    private String petAgeandMonth;

    private String concatenatedStarNames = "";
    private String Payment_id;
    private String total_paid_amount;
    private int grand_total;
    private String _id;
    private String Work_status;


    @SuppressLint({"LogNotTimber", "LongLogTag"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petappointment_details);
        ButterKnife.bind(this);

        scrollablContent.setVisibility(View.GONE);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            appointment_id = extras.getString("appointment_id");
            from = extras.getString("from");
            Log.w(TAG,"appointment_id : "+appointment_id+" from : "+from);

        }



        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.appointment));
        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);



        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
            }
        });
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PetLoverProfileScreenActivity.class);
                intent.putExtra("fromactivity",TAG);
                intent.putExtra("appointment_id",appointment_id);
                intent.putExtra("bookedat",bookedat);
                intent.putExtra("startappointmentstatus",startappointmentstatus);
                intent.putExtra("appointmentfor",appointmentfor);
                intent.putExtra("userrate",userrate);
                intent.putExtra("from",from);
                startActivity(intent);
            }
        });

        img_back.setOnClickListener(v -> onBackPressed());




        btn_cancel.setVisibility(View.GONE);



        if(from != null){
            if(from.equalsIgnoreCase("PetNewAppointmentAdapter")){

                btn_cancel.setVisibility(View.VISIBLE);

                btn_start_stop.setVisibility(View.GONE);

                btn_viewinvoice.setVisibility(View.GONE);


            }
             else if(from.equalsIgnoreCase("PetMissedAppointmentAdapter")){

                btn_cancel.setVisibility(View.GONE);

                btn_start_stop.setVisibility(View.GONE);

                btn_viewinvoice.setVisibility(View.GONE);


            }
             else if(from.equalsIgnoreCase("PetCompletedAppointmentAdapter")) {

                btn_cancel.setVisibility(View.GONE);

                btn_start_stop.setVisibility(View.GONE);

                btn_viewinvoice.setVisibility(View.VISIBLE);
                btn_viewinvoice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),ViewSPInvoiceActivity.class);
                        intent.putExtra("appointment_id",appointment_id);
                        intent.putExtra("from",from);
                        startActivity(intent);
                        finish();

                    }
                });

            }

        }

        if (new ConnectionDetector(PetAppointmentDetailsActivity.this).isNetworkAvailable(PetAppointmentDetailsActivity.this)) {
            spAppointmentDetailsResponse();
        }


    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void spAppointmentDetailsResponse() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SPAppointmentDetailsResponse> call = ApiService.spAppointmentDetailsResponse(RestUtils.getContentType(), appointmentDetailsRequest());
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<SPAppointmentDetailsResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<SPAppointmentDetailsResponse> call, @NonNull Response<SPAppointmentDetailsResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "SPAppointmentDetailsResponse" + "--->" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        scrollablContent.setVisibility(View.VISIBLE);
                        String vaccinated, addr ;
                        String usr_image = "";
                        if (response.body().getData() != null) {




                            if(response.body().getData().getAddition_payment_method() != null && response.body().getData().getAddition_payment_status() != null && response.body().getData().getWork_status() != null){
                                Work_status = response.body().getData().getWork_status();
                                if(response.body().getData().getAddition_payment_method().equalsIgnoreCase("Online") && response.body().getData().getAddition_payment_status().equalsIgnoreCase("Not Paid") && response.body().getData().getWork_status().equalsIgnoreCase("Completed")) {
                                  btn_pay.setVisibility(View.VISIBLE);
                                  if(response.body().getData().getTotal_paid_amount() != null && !response.body().getData().getTotal_paid_amount().isEmpty()){
                                      total_paid_amount = response.body().getData().getTotal_paid_amount();
                                      btn_pay.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              startPayment();

                                          }
                                      }); 
                                  }
                                  
                                 
                                }else{
                                    btn_pay.setVisibility(View.GONE);
                                }


                                }




                            _id = response.body().getData().get_id();
                            appointmentid = response.body().getData().getAppointment_UID();
                            appointmentid = response.body().getData().getAppointment_UID();
                            userid = response.body().getData().getUser_id().get_id();

                            String img_user = response.body().getData().getSp_id().getProfile_img();

                            String usrname = response.body().getData().getSp_id().getFirst_name();

                            String serv_name = response.body().getData().getService_name();

                            String service_amount = response.body().getData().getService_amount();

                            String servname = response.body().getData().getService_name();

                            String hrs = response.body().getData().getHrs();

                            String datetimeslot = response.body().getData().getBooking_time();

                            String service_cost = response.body().getData().getService_amount();

                            String appointment_status = response.body().getData().getAppoinment_status();

                            String appointment_date = response.body().getData().getDisplay_date();

                            String order_date = response.body().getData().getDate_and_time();


                            String orderid = response.body().getData().getAppointment_UID();

                            String order_cost = response.body().getData().getService_amount();

                            addr = response.body().getData().getSp_business_info().get(0).getSp_loc();

                            appoinment_status = response.body().getData().getAppoinment_status();

                            start_appointment_status = response.body().getData().getStart_appointment_status();

                            List<SPAppointmentDetailsResponse.DataBean.SpBusinessInfoBean> Address = response.body().getData().getSp_business_info();
                            for (int i = 0; i < Address.size(); i++) {
                                usr_image = Address.get(i).getThumbnail_image();

                            }
                            setView(img_user, usrname, serv_name ,service_amount ,servname, hrs,datetimeslot,service_cost,appointment_status,appointment_date,order_date,orderid,order_cost,addr);


                        }
                    }


                }
            }

            @Override
            public void onFailure(@NonNull Call<SPAppointmentDetailsResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG, "PetNewAppointmentDetailsResponse" + "--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private AppointmentDetailsRequest appointmentDetailsRequest() {

        AppointmentDetailsRequest appointmentDetailsRequest = new AppointmentDetailsRequest();
        appointmentDetailsRequest.setAppointment_id(appointment_id);
        Log.w(TAG, "appointmentDetailsRequest" + "--->" + new Gson().toJson(appointmentDetailsRequest));
        return appointmentDetailsRequest;
    }



    @SuppressLint({"SetTextI18n", "LongLogTag", "LogNotTimber"})
    private void setView(String img_user, String usrname, String serv_name, String service_amount, String servname, String hrs, String datetimeslot, String service_cost, String appointment_status, String appointment_date, String order_date, String orderid, String order_cost, String addr) {


        if(img_user!= null && !img_user.isEmpty()){
            Glide.with(PetAppointmentDetailsActivity.this)
                    .load(img_user)
                    .into(this.img_user);

        }else{
            Glide.with(PetAppointmentDetailsActivity.this)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(this.img_user);
        }


        if(usrname != null && !usrname.isEmpty()){

            txt_usrname.setText(usrname);
        }
        else{
            txt_usrname.setText("");
        }

        if(serv_name != null && !serv_name.isEmpty()){

            txt_serv_name.setText(serv_name);
        }else{
            txt_serv_name.setText("");
        }


        if(service_amount != null && !service_amount.isEmpty()){

            txt_serv_cost.setText("\u20B9 "+service_amount);
        }else{
            txt_serv_cost.setText("");
        }

        if(servname != null && !servname.isEmpty()){

            txt_servname.setText(servname);
        }else{
            txt_servname.setText("");
        }


        if(hrs != null && !hrs.isEmpty()){

            txt_hrs.setText(hrs);
        }else{
            txt_hrs.setText("");
        }

        if(datetimeslot != null && !datetimeslot.isEmpty()){

            txt_datetimeslot.setText(datetimeslot);
        }else{
            txt_datetimeslot.setText("");
        }

        if(service_cost != null && !service_cost.isEmpty()){

            txt_service_cost.setText(service_cost);
        }else{
            txt_service_cost.setText("");
        }

        if(appointment_status != null && !appointment_status.isEmpty()){

            txt_appointment_status.setText(appointment_status);
        }else{
            txt_appointment_status.setText("");
        }

        if(appointment_date != null && !appointment_date.isEmpty()){

            txt_appointment_date.setText(appointment_date);
        }else{
            txt_appointment_date.setText("");
        }

        if(order_date != null && !order_date.isEmpty()){

            txt_order_date.setText(order_date);
        }else{
            txt_order_date.setText("");
        }


        if(orderid != null && !orderid.isEmpty()){

            txt_order_id.setText(orderid);
        }
        else{
            txt_order_id.setText("");
        }

        if(order_cost != null && !order_cost.isEmpty()){

            txt_order_cost.setText("\u20B9 "+order_cost);

        }
        else{
            txt_order_cost.setText("");
        }

        if(addr != null && !addr.isEmpty()){

            txt_address.setText(addr);
        }
        else{
            txt_address.setText("");
        }

        btn_cancel.setOnClickListener(v -> showStatusAlert(appointment_id,appointmentfor));

    }

    @SuppressLint("SetTextI18n")
    private void showStatusAlert(String id, String appointmentfor) {
        try {
            dialog = new Dialog(PetAppointmentDetailsActivity.this);
            dialog.setContentView(R.layout.alert_approve_reject_layout);
            TextView tvheader = dialog.findViewById(R.id.tvInternetNotConnected);
            tvheader.setText(R.string.cancelappointment);
            Button dialogButtonApprove = dialog.findViewById(R.id.btnApprove);
            dialogButtonApprove.setText("Yes");
            Button dialogButtonRejected = dialog.findViewById(R.id.btnReject);
            dialogButtonRejected.setText("No");

            dialogButtonApprove.setOnClickListener(view -> {
                dialog.dismiss();


                    spappoinmentCancelledResponseCall(id);



            });
            dialogButtonRejected.setOnClickListener(view -> dialog.dismiss());
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }


    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void appoinmentCancelledResponseCall(String id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AppoinmentCancelledResponse> call = apiInterface.appoinmentCancelledResponseCall(RestUtils.getContentType(), appoinmentCancelledRequest(id));
        Log.w(TAG,"appoinmentCancelledResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AppoinmentCancelledResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<AppoinmentCancelledResponse> call, @NonNull Response<AppoinmentCancelledResponse> response) {

                Log.w(TAG,"appoinmentCancelledResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        startActivity(new Intent(PetAppointmentDetailsActivity.this, PetMyappointmentsActivity.class));





                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<AppoinmentCancelledResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"appoinmentCancelledResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private AppoinmentCancelledRequest appoinmentCancelledRequest(String id) {

        /*
         * _id : 5fc639ea72fc42044bfa1683
         * missed_at : 23-10-2000 10 : 00 AM
         * doc_feedback : One Emergenecy work i am cancelling this appointment
         * appoinment_status : Missed
         */


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        AppoinmentCancelledRequest appoinmentCancelledRequest = new AppoinmentCancelledRequest();
        appoinmentCancelledRequest.set_id(id);
        appoinmentCancelledRequest.setMissed_at(currentDateandTime);
        appoinmentCancelledRequest.setDoc_feedback("");
        appoinmentCancelledRequest.setAppoint_patient_st("Petowner Cancelled appointment");
        appoinmentCancelledRequest.setAppoinment_status("Missed");
        Log.w(TAG,"appoinmentCancelledRequest"+ "--->" + new Gson().toJson(appoinmentCancelledRequest));
        return appoinmentCancelledRequest;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
//
//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.home:
//                callDirections("1");
//                break;
//            case R.id.shop:
//                callDirections("2");
//                break;
//            case R.id.services:
//                callDirections("3");
//                break;
//            case R.id.care:
//                callDirections("4");
//                break;
//            case R.id.community:
//                callDirections("5");
//                break;
//
//            default:
//                return  false;
//        }
//        return true;
//    }


    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void compareDatesandTime(String currentDateandTime, String bookingDateandTime) {
        try{

            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");

            Date currentDate = formatter.parse(currentDateandTime);

            Date responseDate = formatter.parse(bookingDateandTime);

            Log.w(TAG,"compareDatesandTime--->"+"responseDate :"+responseDate+" "+"currentDate :"+currentDate);

            if (currentDate != null) {
                if (responseDate != null) {
                    if (currentDate.compareTo(responseDate)<0 || responseDate.compareTo(currentDate) == 0) {
                        Log.w(TAG,"date is equal");
                        isVaildDate = true;

                    }
                    else{
                        Log.w(TAG,"date is not equal");
                        isVaildDate = false;
                    }
                }
            }


        }catch (ParseException e1){
            e1.printStackTrace();
        }
    }


    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void spappoinmentCancelledResponseCall(String id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AppoinmentCancelledResponse> call = apiInterface.spappoinmentCancelledResponseCall(RestUtils.getContentType(), appoinmentCancelledRequest(id));
        Log.w(TAG,"appoinmentCancelledResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AppoinmentCancelledResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<AppoinmentCancelledResponse> call, @NonNull Response<AppoinmentCancelledResponse> response) {

                Log.w(TAG,"appoinmentCancelledResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        spnotificationSendResponseCall();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<AppoinmentCancelledResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"appoinmentCancelledResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void spnotificationSendResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<NotificationSendResponse> call = ApiService.spnotificationSendResponseCall(RestUtils.getContentType(),spNotificationSendRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<NotificationSendResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<NotificationSendResponse> call, @NonNull Response<NotificationSendResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"notificationSendResponseCall"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        startActivity(new Intent(getApplicationContext(), PetMyappointmentsActivity.class));

                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<NotificationSendResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"NotificationSendResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private SPNotificationSendRequest spNotificationSendRequest() {

        /*
         * status : Payment Failed
         * date : 23-10-2020 11:00 AM
         * appointment_UID : PET-2923029239123
         * user_id : 601b8ac3204c595ee52582f2
         * sp_id : 601ba9c6270cbe79fd900183
         */
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
        String currentDateandTime = simpleDateFormat.format(new Date());



        SPNotificationSendRequest spNotificationSendRequest = new SPNotificationSendRequest();
        spNotificationSendRequest.setStatus("Patient Appointment Cancelled");
        spNotificationSendRequest.setDate(currentDateandTime);
        spNotificationSendRequest.setAppointment_UID(appointmentid);
        spNotificationSendRequest.setUser_id(userid);
        spNotificationSendRequest.setSp_id(spid);


        Log.w(TAG,"spNotificationSendRequest"+ "--->" + new Gson().toJson(spNotificationSendRequest));
        return spNotificationSendRequest;
    }


    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void showAddReview(String appointment_id,String appointmentfor) {
        try {

            Dialog dialog = new Dialog(PetAppointmentDetailsActivity.this);
            dialog.setContentView(R.layout.addreview_popup_layout);
            dialog.setCancelable(true);
            RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
            EditText edt_addreview = dialog.findViewById(R.id.edt_addreview);
            Button btn_addreview = dialog.findViewById(R.id.btn_addreview);

            ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
                userrate = String.valueOf(rating);
                Log.w(TAG,"onRatingChanged userrate : "+userrate);
            });

            btn_addreview.setOnClickListener(view -> {
                if(userrate != null){
                    dialog.dismiss();
                    if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                        if(appointmentfor != null && appointmentfor.equalsIgnoreCase("Doctor")){
                            addReviewResponseCall(appointment_id, edt_addreview.getText().toString(), userrate);

                        }else if(appointmentfor != null && appointmentfor.equalsIgnoreCase("SP")) {
                            spaddReviewResponseCall(appointment_id, edt_addreview.getText().toString(), userrate);
                        }
                    }
                }else{
                    showErrorLoading("Please choose a star.");
                }


            });
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void addReviewResponseCall(String id, String userfeedback, String userrate) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AddReviewResponse> call = apiInterface.addReviewResponseCall(RestUtils.getContentType(), addReviewRequest(id,userfeedback,userrate));
        Log.w(TAG,"addReviewResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AddReviewResponse>() {
            @Override
            public void onResponse(@NonNull Call<AddReviewResponse> call, @NonNull Response<AddReviewResponse> response) {

                Log.w(TAG,"AddReviewResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        showAddReviewSuccess();



                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<AddReviewResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"AddReviewResponse flr"+"--->" + t.getMessage());
            }
        });

    } @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void spaddReviewResponseCall(String id, String userfeedback, String userrate) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AddReviewResponse> call = apiInterface.spaddReviewResponseCall(RestUtils.getContentType(), addReviewRequest(id,userfeedback,userrate));
        Log.w(TAG,"spaddReviewResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AddReviewResponse>() {
            @Override
            public void onResponse(@NonNull Call<AddReviewResponse> call, @NonNull Response<AddReviewResponse> response) {

                Log.w(TAG,"spaddReviewResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        showAddReviewSuccess();



                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<AddReviewResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"spaddReviewResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }



    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private AddReviewRequest addReviewRequest(String id, String userfeedback, String userrate) {

        /*
         * _id : 5fd30a701978e618628c966c
         * user_feedback :
         * user_rate : 0
         */
        AddReviewRequest addReviewRequest = new AddReviewRequest();
        addReviewRequest.set_id(id);
        if(userfeedback != null){
            addReviewRequest.setUser_feedback(userfeedback);

        }else{
            addReviewRequest.setUser_feedback("");

        }if(userrate != null){
            addReviewRequest.setUser_rate(userrate);

        }else{
            addReviewRequest.setUser_rate("");

        }
        Log.w(TAG,"addReviewRequest"+ "--->" + new Gson().toJson(addReviewRequest));
        return addReviewRequest;
    }
    private void showAddReviewSuccess() {
        try {

            Dialog dialog = new Dialog(PetAppointmentDetailsActivity.this);
            dialog.setContentView(R.layout.addreview_review_success_layout);
            dialog.setCancelable(false);

            Button btn_back = dialog.findViewById(R.id.btn_back);


            btn_back.setOnClickListener(view -> {
                dialog.dismiss();
                startActivity(new Intent(PetAppointmentDetailsActivity.this,PetMyappointmentsActivity.class));


            });
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }
    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
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

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void getAge(int year, int month, int day){
        Log.w(TAG,"getAge : year "+year+" month : "+ month+" day : "+day);
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        Log.w(TAG,"age : "+age+" todayyear : "+today.get(Calendar.YEAR)+" dobyear : "+ dob.get(Calendar.YEAR));


        int months = dob.get(Calendar.MONTH) - today.get(Calendar.MONTH);
        int currentmonths = (today.get(Calendar.MONTH))+1;
        Log.w(TAG,"dob months: "+dob.get(Calendar.MONTH)+" currentmonths : "+ currentmonths);

        Log.w(TAG," todayyear : "+today.get(Calendar.YEAR)+" dobyear : "+ dob.get(Calendar.YEAR));

        Log.w(TAG,"Conditions : "+(today.get(Calendar.YEAR) < dob.get(Calendar.YEAR)));
        if(today.get(Calendar.YEAR) < dob.get(Calendar.YEAR)){
            age--;
        }

        Log.w(TAG,"age: "+age+" monthsInt : "+ months);
        String ageS = Integer.toString(age);
        String monthsS = Integer.toString(months);

        Log.w(TAG,"ageS: "+ageS+" months : "+monthsS);

        if(age != 0){
            petAgeandMonth = ageS+" years "+monthsS+" months";
        }else{
            petAgeandMonth = monthsS+" months";

        }



        Log.w(TAG,"ageS: "+ageS+" months : "+monthsS);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

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

            case R.id.btn_viewinvoice:

                startActivity(new Intent(PetAppointmentDetailsActivity.this,ViewSPInvoiceActivity.class));
                break;

        }
    }


    private void setMargins(RelativeLayout rl_layout, int i, int i1, int i2, int i3) {

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)rl_layout.getLayoutParams();
        params.setMargins(i, i1, i2, i3);
        rl_layout.setLayoutParams(params);
    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        //totalamount = amount;

      /*  Double d = new Double(amount);
        int amout = d.intValue();*/




        try {
            grand_total = Integer.parseInt(total_paid_amount);
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }


        Integer totalamout = grand_total*100;

        try {
            JSONObject options = new JSONObject();
            options.put("name", "PetFolio");
            options.put("description", userid);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", totalamout);


            co.open(activity, options);
        } catch (Exception e) {
            Log.w(TAG,"Error in payment: " + e.getMessage());

            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Payment_id = razorpayPaymentID;

            Log.w(TAG, "Payment Successful: " + razorpayPaymentID);
            Toasty.success(getApplicationContext(), "Payment Successful. View your booking details in upcoming appointments.", Toast.LENGTH_SHORT, true).show();


            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                transactionCreateResponseCall();
            }




        } catch (Exception e) {
            Log.w(TAG, "Exception in onPaymentSuccess", e);
        }
    }
    @SuppressLint({"LongLogTag", "LogNotTimber"})
    @Override
    public void onPaymentError(int code, String response) {
        try {
            if(new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {

            }
            Log.w(TAG,  "Payment failed: " + code + " " + response);
            Toasty.error(getApplicationContext(), "Payment failed. Please try again with another payment method..", Toast.LENGTH_SHORT, true).show();

        } catch (Exception e) {
            Log.w(TAG, "Exception in onPaymentError", e);
        }
    }




    @SuppressLint("LogNotTimber")
    private void transactionCreateResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.transactionCreateResponseCall(RestUtils.getContentType(),transactionCreateRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"transactionCreateResponseCall"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData() != null) {
                            spAppointmenUpdateResponse();

                        }

                    }




                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"transactionCreateResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private TransactionCreateRequest transactionCreateRequest() {
        /*
         * user_id : 6163d60a489ccc3d894683d2
         * sp_id : 6172b83d8dd3e15b142de045
         * transaction_id : TRN12345
         * transaction_amount : 150
         * transaction_date_time : 23-10-2021 11:00 AM
         * transaction_date : 23-10-2021
         * payment_type : Online
         * appointment_id : SP_098123
         * mobile_type : Android
         * status : debit
         * reason :
         */

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
        String currentDateandTime12hrs = simpleDateFormat.format(new Date());
        String currenttime = currentDateandTime12hrs.substring(currentDateandTime12hrs.indexOf(' ') + 1);
        String currentdate =  currentDateandTime12hrs.substring(0, currentDateandTime12hrs.indexOf(' '));



        TransactionCreateRequest transactionCreateRequest = new TransactionCreateRequest();
        transactionCreateRequest.setUser_id(userid);
        transactionCreateRequest.setSp_id(spid);
        transactionCreateRequest.setTransaction_id(Payment_id);
        transactionCreateRequest.setTransaction_amount(grand_total);
        transactionCreateRequest.setTransaction_date_time(currentDateandTime12hrs);
        transactionCreateRequest.setTransaction_date(currentdate);
        transactionCreateRequest.setPayment_type("Online");
        transactionCreateRequest.setAppointment_id(appointmentid);
        transactionCreateRequest.setMobile_type("Android");
        transactionCreateRequest.setStatus("debit");
        transactionCreateRequest.setReason("");
        Log.w(TAG,"transactionCreateRequest"+ "--->" + new Gson().toJson(transactionCreateRequest));
        return transactionCreateRequest;
    }


    @SuppressLint("LogNotTimber")
    private void spAppointmenUpdateResponse() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.spAppointmenUpdateResponse(RestUtils.getContentType(),appoinmentUpdateRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"spAppointmenUpdateResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData() != null) {
                            spAppointmentDetailsResponse();

                        }

                    }




                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"spAppointmenUpdateResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private AppoinmentUpdateRequest appoinmentUpdateRequest() {
        /*
         * _id : 5fc639ea72fc42044bfa1683
         * work_status :
         * payment_id :
         */

        AppoinmentUpdateRequest appoinmentUpdateRequest = new AppoinmentUpdateRequest();
        appoinmentUpdateRequest.set_id(_id);
        appoinmentUpdateRequest.setWork_status(Work_status);
        appoinmentUpdateRequest.setPayment_id(Payment_id);
        Log.w(TAG,"transactionCreateRequest"+ "--->" + new Gson().toJson(appoinmentUpdateRequest));
        return appoinmentUpdateRequest;
    }
}