package com.triton.nanny.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.triton.nanny.R;
import com.triton.nanny.api.APIClient;
import com.triton.nanny.interfaces.OnAppointmentCancel;
import com.triton.nanny.petlover.PetAppointmentDetailsActivity;
import com.triton.nanny.petlover.VideoCallPetLoverActivity;
import com.triton.nanny.responsepojo.PetAppointmentResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;


public class PetNewAppointmentAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetNewAppointmentAdapter";
    private List<PetAppointmentResponse.DataBean> newAppointmentResponseList;
    private Context context;

    PetAppointmentResponse.DataBean currentItem;

    private OnAppointmentCancel onAppointmentCancel;

    private int size;
    private String communicationtype;
    private boolean isVaildDate;

    public PetNewAppointmentAdapter(Context context, List<PetAppointmentResponse.DataBean> newAppointmentResponseList, RecyclerView inbox_list,int size,OnAppointmentCancel onAppointmentCancel) {
        this.newAppointmentResponseList = newAppointmentResponseList;
        this.context = context;
        this.size = size;
        this.onAppointmentCancel = onAppointmentCancel;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cust_new_spappointment, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {


        currentItem = newAppointmentResponseList.get(position);

            if(newAppointmentResponseList.get(position).getBussiness_name() != null) {
                holder.txt_spbusnsname.setText(newAppointmentResponseList.get(position).getBussiness_name());
            }
            else {

                holder.txt_spbusnsname.setText("");
            }

            if(newAppointmentResponseList.get(position).getService_name() != null) {
                holder.txt_servname.setText(newAppointmentResponseList.get(position).getService_name());
            }

            else {

                holder.txt_servname.setText("");
            }

        if(newAppointmentResponseList.get(position).getService_hrs() != null) {
            holder.txt_hrs.setText(newAppointmentResponseList.get(position).getService_hrs());
        }

        else {

            holder.txt_hrs.setText("");
        }

        if(newAppointmentResponseList.get(position).getBooking_time() != null){
        holder.txt_datetimeslot.setText("\u20B9 "+newAppointmentResponseList.get(position).getBooking_time());
        }
        else {

            holder.txt_datetimeslot.setText("");
        }


        if(newAppointmentResponseList.get(position).getBooking_cost() != null){
            holder.txt_service_cost.setText("\u20B9 "+newAppointmentResponseList.get(position).getBooking_cost());
        }

        else {

            holder.txt_service_cost.setText("");
        }

        if(newAppointmentResponseList.get(position).getStatus() != null){
            holder.txt_appointment_status.setText(" "+newAppointmentResponseList.get(position).getStatus());

        }
        else {
            holder.txt_appointment_status.setText("");
        }

/*        if(newAppointmentResponseList.get(position).getBooking_at() != null){
            holder.txt_bookedon.setText("Booked for :"+" "+newAppointmentResponseList.get(position).getBooking_at());

        }*/


        if (newAppointmentResponseList.get(position).getImage_url() != null && !newAppointmentResponseList.get(position).getImage_url().isEmpty()) {

                Glide.with(context)
                        .load(newAppointmentResponseList.get(position).getImage_url())
                        .into(holder.img_clinic_imge);

            }
        else{
                Glide.with(context)
                        .load(APIClient.PROFILE_IMAGE_URL)
                        .into(holder.img_clinic_imge);

            }


            holder.btn_cancel.setOnClickListener(v -> {
                onAppointmentCancel.onAppointmentCancel(newAppointmentResponseList.get(position).get_id(), "", "", "", newAppointmentResponseList.get(position).getAppointment_id(), "");
            });

        holder.ll_new.setOnClickListener(v -> {

            Intent i = new Intent(context, PetAppointmentDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("appointment_id",newAppointmentResponseList.get(position).get_id());
  /*          i.putExtra("bookedat",newAppointmentResponseList.get(position).getBooked_at());
            i.putExtra("startappointmentstatus",newAppointmentResponseList.get(position).getStart_appointment_status());
            i.putExtra("appointmentfor",newAppointmentResponseList.get(position).getAppointment_for());*/
            i.putExtra("from",TAG);
            context.startActivity(i);

        });


    }

    @Override
    public int getItemCount() {
        return Math.min(newAppointmentResponseList.size(), size);
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_spbusnsname,txt_servname,txt_datetimeslot,txt_hrs,txt_appointment_status,txt_clinicname,txt_petname,txt_type,txt_service_cost,txt_bookedon,txt_lbl_doctorname,txt_doctorname;
        public ImageView img_clinic_imge,img_emergency_appointment,img_videocall;
        public Button btn_cancel;
        LinearLayout ll_new;


        public ViewHolderOne(View itemView) {
            super(itemView);
            img_clinic_imge = itemView.findViewById(R.id.img_clinic_imge);
            txt_spbusnsname = itemView.findViewById(R.id.txt_spbusnsname);
            txt_servname = itemView.findViewById(R.id.txt_servname);
            txt_hrs = itemView.findViewById(R.id.txt_hrs);
            txt_datetimeslot = itemView.findViewById(R.id.txt_datetimeslot);
            txt_service_cost = itemView.findViewById(R.id.txt_service_cost);
            txt_appointment_status = itemView.findViewById(R.id.txt_appointment_status);
        /*    txt_bookedon = itemView.findViewById(R.id.txt_bookedon);*/
            btn_cancel = itemView.findViewById(R.id.btn_cancel);
            ll_new = itemView.findViewById(R.id.ll_new);

        }




    }

    @SuppressLint("LogNotTimber")
    private void compareDatesandTime(String currentDateandTime, String bookingDateandTime) {
        try{

            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");

            Date currentDate = formatter.parse(currentDateandTime);

            Date responseDate = formatter.parse(bookingDateandTime);

            Log.w(TAG,"compareDatesandTime--->"+"responseDate :"+responseDate+" "+"currentDate :"+currentDate);

            if (currentDate.compareTo(responseDate)<0 || responseDate.compareTo(currentDate) == 0)
            {
                Log.w(TAG,"date is equal");
                isVaildDate = true;

            }else{
                Log.w(TAG,"date is not equal");
                isVaildDate = false;
            }



        }catch (ParseException e1){
            e1.printStackTrace();
        }
    }








}
