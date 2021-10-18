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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.triton.nanny.R;
import com.triton.nanny.api.APIClient;
import com.triton.nanny.petlover.PetAppointmentDetailsActivity;
import com.triton.nanny.responsepojo.PetAppointmentResponse;
import java.util.List;


public class PetMissedAppointmentAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetMissedAppointmentAdapter";
    private List<PetAppointmentResponse.DataBean> missedAppointmentResponseList;
    private Context context;

    PetAppointmentResponse.DataBean currentItem;
    int size;


    public PetMissedAppointmentAdapter(Context context, List<PetAppointmentResponse.DataBean> missedAppointmentResponseList, RecyclerView inbox_list,int size) {
        this.missedAppointmentResponseList = missedAppointmentResponseList;
        this.context = context;
        this.size = size;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cust_spmissed_appointment, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        if(missedAppointmentResponseList.get(position).getBussiness_name() != null) {
            holder.txt_spbusnsname.setText(missedAppointmentResponseList.get(position).getBussiness_name());
        }
        else {

            holder.txt_spbusnsname.setText("");
        }

        if(missedAppointmentResponseList.get(position).getService_name() != null) {
            holder.txt_servname.setText(missedAppointmentResponseList.get(position).getService_name());
        }

        else {

            holder.txt_servname.setText("");
        }

        if(missedAppointmentResponseList.get(position).getService_hrs() != null) {
            holder.txt_hrs.setText(missedAppointmentResponseList.get(position).getService_hrs());
        }

        else {

            holder.txt_hrs.setText("");
        }

        if(missedAppointmentResponseList.get(position).getBooking_time() != null){
            holder.txt_datetimeslot.setText("\u20B9 "+missedAppointmentResponseList.get(position).getBooking_time());
        }
        else {

            holder.txt_datetimeslot.setText("");
        }


        if(missedAppointmentResponseList.get(position).getBooking_cost() != null){
            holder.txt_service_cost.setText("\u20B9 "+missedAppointmentResponseList.get(position).getBooking_cost());
        }

        else {

            holder.txt_service_cost.setText("");
        }

        if(missedAppointmentResponseList.get(position).getStatus() != null){
            holder.txt_appointment_status.setText(" "+missedAppointmentResponseList.get(position).getStatus());

        }
        else {
            holder.txt_appointment_status.setText("");
        }

/*
        if(missedAppointmentResponseList.get(position).getBooking_at() != null){
            holder.txt_bookedon.setText("Booked for :"+" "+missedAppointmentResponseList.get(position).getBooking_at());

        }
*/


        if (missedAppointmentResponseList.get(position).getImage_url() != null && !missedAppointmentResponseList.get(position).getImage_url().isEmpty()) {

            Glide.with(context)
                    .load(missedAppointmentResponseList.get(position).getImage_url())
                    .into(holder.img_clinic_imge);

        }
        else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_clinic_imge);

        }




        holder.ll_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, PetAppointmentDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("appointment_id",missedAppointmentResponseList.get(position).get_id());
                i.putExtra("from",TAG);
                context.startActivity(i);

                }

        });






    }

   @Override
    public int getItemCount() {
       return Math.min(missedAppointmentResponseList.size(), size);

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
            txt_bookedon = itemView.findViewById(R.id.txt_bookedon);
            btn_cancel = itemView.findViewById(R.id.btn_cancel);
            ll_new = itemView.findViewById(R.id.ll_new);

        }






    }








}
