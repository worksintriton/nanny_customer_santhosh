package com.triton.nanny.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.triton.nanny.R;
import com.triton.nanny.api.APIClient;
import com.triton.nanny.petlover.PetSubServiceActivity;
import com.triton.nanny.petlover.Service_Details_Activity;
import com.triton.nanny.responsepojo.PetLoverDashboardResponse;

import java.util.List;


public class DashboardServicesAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "DashboardServicesAdapter";

    private Context context;

    List<PetLoverDashboardResponse.DataBean.DashboarddataBean.DoctorDetailsBean> doctorDetailsResponseList;
    PetLoverDashboardResponse.DataBean.DashboarddataBean.DoctorDetailsBean currentItem;

    int size;

    public DashboardServicesAdapter(Context context, List<PetLoverDashboardResponse.DataBean.DashboarddataBean.DoctorDetailsBean> doctorDetailsResponseList, RecyclerView inbox_list, int size) {
        this.doctorDetailsResponseList = doctorDetailsResponseList;
        this.context = context;
        this.size = size;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_petlover_services, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
          currentItem = doctorDetailsResponseList.get(position);
        Log.w(TAG, "doctorDetailsResponseList " + new Gson().toJson(doctorDetailsResponseList));

        if(currentItem.getService_name() != null) {
              holder.txt_petlover_servicesname.setText(currentItem.getService_name());
          }
          if (currentItem.getIcon_banner() != null && !currentItem.getIcon_banner().isEmpty()) {

            Glide.with(context)
                    .load(currentItem.getIcon_banner())
                    //.load(R.drawable.logo)
                    .into(holder.cv_serviceimage);

           }
          else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.cv_serviceimage);

        }

         /* if(currentItem.getBackground_color() != null) {
              int color = Color.parseColor(currentItem.getBackground_color());
              Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.layout_bg_service);
              Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
              DrawableCompat.setTint(wrappedDrawable, color);
              holder.ll_root.setBackgroundResource(R.drawable.layout_bg_service);


          }*/


        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(doctorDetailsResponseList.get(position).getService_id() != null) {

                    Intent intent = new Intent(context, Service_Details_Activity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("servname",doctorDetailsResponseList.get(position).getService_name());
                    intent.putExtra("subservname",doctorDetailsResponseList.get(position).getTitle());
                    intent.putExtra("subservimage",doctorDetailsResponseList.get(position).getIcon_banner());
                    intent.putExtra("subcatid",doctorDetailsResponseList.get(position).get_id());
                    intent.putExtra("catid",doctorDetailsResponseList.get(position).getService_id());
                    intent.putExtra("from",TAG);
                    intent.putExtra("flag",true);
                    context.startActivity(intent);



                }
                }




        });


    }












    @Override
    public int getItemCount() {
        return Math.min(doctorDetailsResponseList.size(), size);



    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_petlover_servicesname;
        public RelativeLayout ll_root;
        public ImageView cv_serviceimage;




        public ViewHolderOne(View itemView) {
            super(itemView);
            txt_petlover_servicesname = itemView.findViewById(R.id.txt_petlover_servicesname);
            cv_serviceimage = itemView.findViewById(R.id.cv_serviceimage);
            ll_root = itemView.findViewById(R.id.ll_root);



        }




    }










}
