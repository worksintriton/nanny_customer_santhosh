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
import com.triton.nanny.interfaces.AddReviewListener;
import com.triton.nanny.petlover.PetAppointmentDetailsActivity;
import com.triton.nanny.responsepojo.PetAppointmentResponse;

import java.util.List;


public class PetCompletedAppointmentAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "PetCompletedAppointmentAdapter";
    private final List<PetAppointmentResponse.DataBean> completedAppointmentResponseList;
    private final Context context;

    int size;
    private final AddReviewListener addReviewListener;


    public PetCompletedAppointmentAdapter(Context context, List<PetAppointmentResponse.DataBean> completedAppointmentResponseList, int size, AddReviewListener addReviewListener) {
        this.completedAppointmentResponseList = completedAppointmentResponseList;
        this.context = context;
        this.size = size;
        this.addReviewListener = addReviewListener;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cust_spcompleted_appointment, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber", "LongLogTag"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        if(completedAppointmentResponseList.get(position).getBussiness_name() != null) {
            holder.txt_spbusnsname.setText(completedAppointmentResponseList.get(position).getBussiness_name());
        }
        else {

            holder.txt_spbusnsname.setText("");
        }

        if(completedAppointmentResponseList.get(position).getService_name() != null) {
            holder.txt_servname.setText(completedAppointmentResponseList.get(position).getService_name());
        }

        else {

            holder.txt_servname.setText("");
        }

        if(completedAppointmentResponseList.get(position).getService_hrs() != null) {
            holder.txt_hrs.setText(completedAppointmentResponseList.get(position).getService_hrs());
        }

        else {

            holder.txt_hrs.setText("");
        }

        if(completedAppointmentResponseList.get(position).getBooking_time() != null){
            holder.txt_datetimeslot.setText("\u20B9 "+completedAppointmentResponseList.get(position).getBooking_time());
        }
        else {

            holder.txt_datetimeslot.setText("");
        }


        if(completedAppointmentResponseList.get(position).getBooking_cost() != null){
            holder.txt_service_cost.setText("\u20B9 "+completedAppointmentResponseList.get(position).getBooking_cost());
        }

        else {

            holder.txt_service_cost.setText("");
        }

        if(completedAppointmentResponseList.get(position).getStatus() != null){
            holder.txt_appointment_status.setText(" "+completedAppointmentResponseList.get(position).getStatus());

        }
        else {
            holder.txt_appointment_status.setText("");
        }

//        if(completedAppointmentResponseList.get(position).getBooking_at() != null){
//            holder.txt_bookedon.setText("Completed on :"+" "+completedAppointmentResponseList.get(position).getBooking_at());
//
//        }


        if (completedAppointmentResponseList.get(position).getImage_url() != null && !completedAppointmentResponseList.get(position).getImage_url().isEmpty()) {

            Glide.with(context)
                    .load(completedAppointmentResponseList.get(position).getImage_url())
                    .into(holder.img_clinic_imge);

        }
        else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_clinic_imge);

        }




        holder.ll_new.setOnClickListener(v -> {

            Intent i = new Intent(context, PetAppointmentDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("appointment_id",completedAppointmentResponseList.get(position).get_id());
            i.putExtra("from",TAG);
            context.startActivity(i);


        });



        }

    @Override
    public int getItemCount() {
        return Math.min(completedAppointmentResponseList.size(), size);

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
