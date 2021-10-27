package com.triton.nanny.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.triton.nanny.R;
import com.triton.nanny.adapter.TransactionHistoryAdapter;
import com.triton.nanny.api.APIClient;
import com.triton.nanny.api.RestApiInterface;
import com.triton.nanny.requestpojo.TransactionHistoryRequest;
import com.triton.nanny.responsepojo.TransactionHistoryResponse;
import com.triton.nanny.sessionmanager.SessionManager;
import com.triton.nanny.utils.ConnectionDetector;
import com.triton.nanny.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionHistoryActivity extends AppCompatActivity {

    private String TAG = "TransactionHistoryActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_transactionhistory)
    RecyclerView rv_transactionhistory;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_date)
    RelativeLayout rl_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_date)
    TextView txt_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_total_spent)
    TextView txt_total_spent;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_return)
    TextView txt_return;

      SessionManager session;
    String type = "",name = "",userid = "";
    private String fromactivity;

    private int year, month, day;
    private static final int DATE_PICKER_ID = 0 ;
    private String Selectedddate = "";
    private List<TransactionHistoryResponse.DataBean.TransactionBean> transactionHistoryResponseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        ButterKnife.bind(this);
        avi_indicator.setVisibility(View.GONE);


        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        type = user.get(SessionManager.KEY_TYPE);
        name = user.get(SessionManager.KEY_FIRST_NAME);
        userid = user.get(SessionManager.KEY_ID);
        Log.w(TAG,"session--->"+"type :"+type+" "+"name :"+" "+name);
        img_back.setOnClickListener(v -> onBackPressed());
        
        
        if(userid != null){
            if (new ConnectionDetector(TransactionHistoryActivity.this).isNetworkAvailable(TransactionHistoryActivity.this)) {
                transactionHistoryResponseCall();
            }
        }

        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (new ConnectionDetector(TransactionHistoryActivity.this).isNetworkAvailable(TransactionHistoryActivity.this)) {
                    transactionHistoryResponseCall();
                }
            }
        });
        rl_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate();
            }
        });



    }

    private void SelectDate() {

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);


        showDialog(DATE_PICKER_ID);

    }
    @SuppressLint("LogNotTimber")
    @Override
    protected Dialog onCreateDialog(int id) {
        Log.w(TAG,"onCreateDialog id : "+id);
        if (id == DATE_PICKER_ID) {
            // open datepicker dialog.
            // set date picker for current date
            // add pickerListener listner to date picker
            // return new DatePickerDialog(this, pickerListener, year, month,day);
            DatePickerDialog dialog = new DatePickerDialog(this, pickerListener, year, month, day);
            dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            return dialog;
        }
        return null;
    }


    private final DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @SuppressLint("LogNotTimber")
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;



            String strdayOfMonth;
            String strMonth;
            int month1 =(month + 1);
            if(day == 9 || day <9){
                strdayOfMonth = "0"+day;
                Log.w(TAG,"Selected dayOfMonth-->"+strdayOfMonth);
            }else{
                strdayOfMonth = String.valueOf(day);
            }

            if(month1 == 9 || month1 <9){
                strMonth = "0"+month1;
                Log.w(TAG,"Selected month1-->"+strMonth);
            }else{
                strMonth = String.valueOf(month1);
            }

            Selectedddate = strdayOfMonth + "-" + strMonth + "-" + year;

            // Show selected date
            txt_date.setText(Selectedddate);

        }
    };



    @SuppressLint("LogNotTimber")
    private void transactionHistoryResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<TransactionHistoryResponse> call = ApiService.transactionHistoryResponseCall(RestUtils.getContentType(),transactionHistoryRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<TransactionHistoryResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<TransactionHistoryResponse> call, @NonNull Response<TransactionHistoryResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"transactionHistoryResponseCall"+ "--->" + new Gson().toJson(response.body()));
                refresh_layout.setRefreshing(false);

                if (response.body() != null) {
                    if(response.body().getCode() == 200){


                        if(response.body().getData() != null) {
                            if(response.body().getData().getRefund_amount() != 0){
                                txt_return.setText("\u20B9 "+response.body().getData().getRefund_amount());
                            }else{
                                txt_return.setText("");
                            }
                            if(response.body().getData().getSpend_amount() != 0){
                                txt_total_spent.setText("\u20B9 "+response.body().getData().getSpend_amount());
                            }else{
                                txt_total_spent.setText("");
                            }

                            if(response.body().getData().getTransaction() != null && response.body().getData().getTransaction().size()>0){
                                transactionHistoryResponseList = response.body().getData().getTransaction();
                                txt_no_records.setVisibility(View.GONE);
                                rv_transactionhistory.setVisibility(View.VISIBLE);
                                setView();
                            } else{
                                rv_transactionhistory.setVisibility(View.GONE);
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No transction history");

                            }
                        }

                        }




                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<TransactionHistoryResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"TransactionHistoryResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private TransactionHistoryRequest transactionHistoryRequest() {
        /*
         * user_id : 6163d60a489ccc3d894683d2
         * filter_date :
         */

        TransactionHistoryRequest transactionHistoryRequest = new TransactionHistoryRequest();
        transactionHistoryRequest.setUser_id(userid);
        transactionHistoryRequest.setFilter_date(Selectedddate);
        Log.w(TAG,"transactionHistoryRequest"+ "--->" + new Gson().toJson(transactionHistoryRequest));
        return transactionHistoryRequest;
    }

    private void setView() {
        rv_transactionhistory.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_transactionhistory.setItemAnimator(new DefaultItemAnimator());
        TransactionHistoryAdapter transactionHistoryAdapter = new TransactionHistoryAdapter(getApplicationContext(), transactionHistoryResponseList);
        rv_transactionhistory.setAdapter(transactionHistoryAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}