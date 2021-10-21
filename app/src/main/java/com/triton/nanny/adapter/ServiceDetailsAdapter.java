package com.triton.nanny.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.nanny.R;
import com.triton.nanny.responsepojo.SPDetailScreenResponse;


import java.util.List;


public class ServiceDetailsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private  String TAG = "ServiceDetailsAdapter";
    private Context context;
    List<SPDetailScreenResponse.DataBean.BusServiceListBean> bus_service_list;

    SPDetailScreenResponse.DataBean.BusServiceListBean currentItem;
    String fromactivity;
    private String concatenatedStarNames = "";

    public ServiceDetailsAdapter(Context context, List<SPDetailScreenResponse.DataBean.BusServiceListBean> bus_service_list) {
        this.context = context;
        this.bus_service_list = bus_service_list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_parent_servicelist, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);
    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = bus_service_list.get(position);
        if(bus_service_list != null && bus_service_list.size()>0){
            holder.txt_servicename.setVisibility(View.VISIBLE);
            holder.txt_subservicename.setVisibility(View.VISIBLE);
            holder.txt_servicename.setText(currentItem.getService_name());

            if(bus_service_list.get(position).getSubsericelist() != null){
                concatenatedStarNames = "";
                for (int i = 0; i < bus_service_list.get(position).getSubsericelist().size(); i++) {
                    if(bus_service_list.get(position).getSubsericelist().get(i).isIsservice()){
                        concatenatedStarNames += bus_service_list.get(position).getSubsericelist().get(i).getTitle();
                        if (i < bus_service_list.get(position).getSubsericelist().size() - 1) concatenatedStarNames += ", ";
                        holder.txt_subservicename.setText(concatenatedStarNames);
                        Log.w(TAG," concatenatedStarNames : "+concatenatedStarNames);
                    }

                }


            }
        }else{
            holder.txt_servicename.setVisibility(View.GONE);
            holder.txt_subservicename.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return bus_service_list.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }



    public class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_servicename,txt_subservicename;
        public ImageView img_settings;


        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_servicename = itemView.findViewById(R.id.txt_servicename);
            txt_subservicename = itemView.findViewById(R.id.txt_subservicename);
            img_settings = itemView.findViewById(R.id.img_settings);





        }




    }


}
