package com.triton.nanny.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.triton.nanny.R;
import com.triton.nanny.api.APIClient;
import com.triton.nanny.petlover.SelectedServiceActivity;
import com.triton.nanny.petlover.Service_Details_Activity;
import com.triton.nanny.responsepojo.SubServiceCatResponse;

import java.util.List;


public class PetSubServicesAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetSubServicesAdapter";

    private Context context;

    private List<SubServiceCatResponse.DataBean> serviceCatList;
    SubServiceCatResponse.DataBean currentItem;

    boolean flag;
    String servname;

    public PetSubServicesAdapter(Context context, List<SubServiceCatResponse.DataBean> serviceCatList, boolean flag, String servname) {
        this.serviceCatList = serviceCatList;
        this.context = context;
        this.flag = flag;
        this.servname = servname;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_petloversplist_subservice, parent, false);

        return new ViewHolderOne(view);

    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {

          currentItem = serviceCatList.get(position);
          Log.w(TAG,"Size : "+serviceCatList.size()+" "+" Images : "+serviceCatList.get(position).getIcon_banner());
          if (currentItem.getIcon_banner() != null && !currentItem.getIcon_banner().isEmpty()) {

              int pos = position % 2 ;

              Log.w(TAG,"position "+pos);

              if(currentItem.getIcon_banner() != null && !currentItem.getIcon_banner().isEmpty()) {
                  Glide.with(context)
                          .load(currentItem.getIcon_banner())
                          //.load(R.drawable.logo)
                          .into(holder.img_petservice);
              }else{
                  Glide.with(context)
                          .load(R.drawable.no_image)
                          //.load(R.drawable.logo)
                          .into(holder.img_petservice);

              }


          }
          else{
            Glide.with(context)
                    .load(R.drawable.no_image)
                    .into(holder.img_petservice);

        }

          if(currentItem.getTitle() != null&&!currentItem.getTitle().isEmpty()){

              holder.txt_title.setText(currentItem.getTitle());

          }

        holder.img_petservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, SelectedServiceActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("catid",serviceCatList.get(position).get_id());
//                intent.putExtra("from","PetSubServices");
//                intent.putExtra("flag",flag);
//                context.startActivity(intent);

                Intent intent = new Intent(context, Service_Details_Activity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("servname",servname);
                intent.putExtra("subservname",serviceCatList.get(position).getTitle());
                intent.putExtra("subservimage",serviceCatList.get(position).getIcon_banner());
                intent.putExtra("subcatid",serviceCatList.get(position).get_id());
                intent.putExtra("catid",serviceCatList.get(position).getService_id());
                intent.putExtra("from","PetSubServices");
                intent.putExtra("flag",flag);
                context.startActivity(intent);

                Log.w(TAG,"catid"+ serviceCatList.get(position).getService_id());

                Log.w(TAG," servname : "+servname);

            }




        });



    }



    @Override
    public int getItemCount() {
        return serviceCatList.size();



    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {

        public ImageView img_petservice;
        public TextView txt_title,txt_sub_title;

        public ViewHolderOne(View itemView) {
            super(itemView);
            img_petservice = itemView.findViewById(R.id.img_petservice);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_sub_title = itemView.findViewById(R.id.txt_sub_title);




        }

    }
}
