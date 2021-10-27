package com.triton.nanny.petlover;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import com.triton.nanny.R;
import com.triton.nanny.api.APIClient;
import com.triton.nanny.api.RestApiInterface;
import com.triton.nanny.requestpojo.AppointmentDetailsRequest;
import com.triton.nanny.responsepojo.PetNewAppointmentDetailsResponse;
import com.triton.nanny.responsepojo.SPAppointmentDetailsResponse;
import com.triton.nanny.utils.ConnectionDetector;
import com.triton.nanny.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSPInvoiceActivity extends AppCompatActivity {

    private  String TAG = "ViewSPInvoiceActivity";


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.scrollablContent)
    ScrollView scrollablContent;



    String appointment_id;
    String appoinment_status;
    String start_appointment_status="";
    String end_appointment_status="";
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
    private String start_otp = "";
    private String end_otp = "";

    BottomNavigationView bottom_navigation_view;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_nanny_cust_name)
    TextView txt_nanny_cust_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_invoice_no)
    TextView txt_invoice_no;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_nanny_cust_addrst)
    TextView txt_nanny_cust_addrst;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_nanny_cust_city)
    TextView txt_nanny_cust_city;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_lbl_balance_due)
    TextView txt_lbl_balance_due;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_nanny_cust_landmark)
    TextView txt_nanny_cust_landmark;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_balance_due)
    TextView txt_balance_due;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_nanny_cust_pincode)
    TextView txt_nanny_cust_pincode;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_invoicedate)
    TextView txt_invoicedate;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cust_name)
    TextView txt_cust_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_invoiceterms)
    TextView txt_invoiceterms;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_custaddress_st)
    TextView txt_custaddress_st;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_invoicebilldate)
    TextView txt_invoicebilldate;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cust_addr_landmark)
    TextView txt_cust_addr_landmark;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_custaddr_pincode)
    TextView txt_custaddr_pincode;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_servtitle)
    TextView txt_servtitle;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_hrs)
    TextView txt_hrs;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cost)
    TextView txt_cost;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_total)
    TextView txt_total;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_lbl_baldue)
    TextView txt_lbl_baldue;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_baldue)
    TextView txt_baldue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_spinvoice);
        ButterKnife.bind(this);

        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.invoice));
        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);
        img_profile.setVisibility(View.GONE);
        img_notification.setVisibility(View.GONE);

        img_back.setOnClickListener(v -> onBackPressed());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            appointment_id = extras.getString("appointment_id");
            from = extras.getString("fromactivity");

            Log.w(TAG,"appointment_id : "+appointment_id+" from : "+from);

        }

        if (new ConnectionDetector(ViewSPInvoiceActivity.this).isNetworkAvailable(ViewSPInvoiceActivity.this)) {
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

                        String vaccinated, addr;

                        String usr_image = "";
                        if (response.body().getData() != null) {
                            spid = response.body().getData().getSp_id().get_id();
                            appointmentid = response.body().getData().getAppointment_UID();
                            userid = response.body().getData().getUser_id().get_id();

                            String img_user = response.body().getData().getSp_id().getProfile_img();

                            String usrname = response.body().getData().getSp_id().getFirst_name();

                            String serv_name = response.body().getData().getService_name();


                            String datetimeslot = response.body().getData().getBooking_time();

                            String service_cost = response.body().getData().getService_amount();

                            String appointment_status = response.body().getData().getAppoinment_status();


                            String order_date = response.body().getData().getDate_and_time();




                            String order_cost = response.body().getData().getService_amount();

                            addr = response.body().getData().getSp_business_info().get(0).getSp_loc();

                            appoinment_status = response.body().getData().getAppoinment_status();

                            start_appointment_status = response.body().getData().getStart_appointment_status();

                            end_appointment_status = response.body().getData().getEnd_appointment_status();

                            start_otp = response.body().getData().getStart_otp();

                            end_otp = response.body().getData().getEnd_otp();

                            List<SPAppointmentDetailsResponse.DataBean.SpBusinessInfoBean> Address = response.body().getData().getSp_business_info();
                            for (int i = 0; i < Address.size(); i++) {
                                usr_image = Address.get(i).getThumbnail_image();

                            }

                            String invoice_no = response.body().getData().getAppointment_UID();

                            String balance_due = response.body().getData().getAddition_amount();

                            String invoicedate = response.body().getData().getBooking_date_time();

                            String cust_name = response.body().getData().getUser_id().getFirst_name();

                            String custaddress_st = response.body().getData().getAddress_text();

                            // String custaddress_st = "";

                            String invoicebilldate = response.body().getData().getDisplay_date();

                            String cust_addr_landmark = response.body().getData().getCity() + " "+response.body().getData().getState();

                            String custaddr_pincode = response.body().getData().getPin_code();

                          /*  String cust_addr_landmark = "";

                            String custaddr_pincode = "";*/

                            String servname = response.body().getData().getService_name();

                            String hrs = response.body().getData().getHrs();

                            String service_amount = response.body().getData().getService_amount();

                            String total_amount = response.body().getData().getTotal_paid_amount();


                            setView(invoice_no,balance_due,invoicedate,cust_name,custaddress_st,invoicebilldate,cust_addr_landmark,custaddr_pincode,servname,hrs,service_amount,total_amount);


                        }
                    }else{
                        scrollablContent.setVisibility(View.GONE);
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
    private void setView(String invoice_no, String balance_due, String invoicedate, String cust_name, String custaddress_st, String invoicebilldate, String cust_addr_landmark, String custaddr_pincode, String servname, String hrs, String service_amount, String total_amount) {


        if(invoice_no != null && !invoice_no.isEmpty()){

            txt_invoice_no.setText(invoice_no);
        }
        else{
            txt_invoice_no.setText("");
        }

        if(balance_due != null && !balance_due.isEmpty()){

            txt_lbl_balance_due.setVisibility(View.VISIBLE);
            txt_balance_due.setVisibility(View.VISIBLE);
            txt_balance_due.setText(balance_due);
            txt_lbl_baldue.setVisibility(View.VISIBLE);
            txt_baldue.setVisibility(View.VISIBLE);
            txt_baldue.setText(balance_due);
        }
        else{

            txt_lbl_balance_due.setVisibility(View.GONE);
            txt_balance_due.setVisibility(View.GONE);
            txt_lbl_baldue.setVisibility(View.GONE);
            txt_baldue.setVisibility(View.GONE);
        }


        if(invoicedate != null && !invoicedate.isEmpty()){

            txt_invoicedate.setText(invoicedate);
        }
        else{
            txt_invoicedate.setText("");
        }

        if(cust_name != null && !cust_name.isEmpty()){

            txt_cust_name.setText(cust_name);
        }
        else{
            txt_cust_name.setText("");
        }

        if(custaddress_st != null && !custaddress_st.isEmpty()){

            txt_custaddress_st.setText(custaddress_st);
        }
        else{
            txt_custaddress_st.setText("");
        }

        if(invoicebilldate != null && !invoicebilldate.isEmpty()){

            txt_invoicebilldate.setText(invoicebilldate);
        }
        else{
            txt_invoicebilldate.setText("");
        }

        if(cust_addr_landmark != null && !cust_addr_landmark.isEmpty()){

            txt_cust_addr_landmark.setText(cust_addr_landmark);
        }
        else{
            txt_cust_addr_landmark.setText("");
        }

        if(custaddr_pincode != null && !custaddr_pincode.isEmpty()){

            txt_custaddr_pincode.setText(custaddr_pincode);
        }
        else{
            txt_custaddr_pincode.setText("");
        }

        if(servname != null && !servname.isEmpty()){

            txt_servtitle.setText(servname);
        }else{
            txt_servtitle.setText("");
        }


        if(hrs != null && !hrs.isEmpty()){

            txt_hrs.setText(hrs);
        }else{
            txt_hrs.setText("");
        }


        if(service_amount != null && !service_amount.isEmpty()){

            txt_cost.setText("\u20B9 "+service_amount);

        }
        else{
            txt_cost.setText("");
        }

        if(total_amount != null && !total_amount.isEmpty()){

            txt_total.setText("\u20B9 "+service_amount);

        }
        else{
            txt_total.setText("");
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),PetAppointmentDetailsActivity.class);
        intent.putExtra("appointment_id",appointment_id);
        intent.putExtra("from",from);
        startActivity(intent);
        finish();
    }
}