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
import com.bumptech.glide.request.RequestOptions;
import com.triton.nanny.R;

import com.triton.nanny.api.APIClient;
import com.triton.nanny.petlover.PetSubServiceActivity;
import com.triton.nanny.responsepojo.ServiceCatResponse;

import java.util.List;


public class PetServicesAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetServicesAdapter";

    private Context context;

    private List<ServiceCatResponse.DataBean> serviceCatList;
    ServiceCatResponse.DataBean currentItem;
    String sub_service_flag;

    public PetServicesAdapter(Context context, List<ServiceCatResponse.DataBean> serviceCatList) {
        this.serviceCatList = serviceCatList;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_petloversplist, parent, false);

        return new ViewHolderOne(view);

    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);

    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {

          currentItem = serviceCatList.get(position);
          Log.w(TAG,"Size : "+serviceCatList.size()+" "+" Images : "+serviceCatList.get(position).getImage());
          if (currentItem.getImage() != null && !currentItem.getImage().isEmpty()) {

              int pos = position % 2 ;

              Log.w(TAG,"position "+pos);

              if(position==0){
                  if(currentItem.getImage() != null && !currentItem.getImage().isEmpty()) {
                      Glide.with(context)
                              .load(currentItem.getImage())
                              .centerCrop()
                              .apply(new RequestOptions().override(140, 220))
                              //.load(R.drawable.logo)
                              .into(holder.img_petservice);
                  }else{
                      Glide.with(context)
                              .load(APIClient.PROFILE_IMAGE_URL)
                              .centerCrop()
                              .apply(new RequestOptions().override(140, 220))
                              //.load(R.drawable.logo)
                              .into(holder.img_petservice);

                  }


              }

              else if(position==1){
                  if(currentItem.getImage() != null && !currentItem.getImage().isEmpty()) {

                      Glide.with(context)
                              .load(currentItem.getImage())
                              .centerCrop()
                              .apply(new RequestOptions().override(140, 220))
                              //.load(R.drawable.logo)
                              .into(holder.img_petservice);
                  }else{
                      Glide.with(context)
                              .load(APIClient.PROFILE_IMAGE_URL)
                              .centerCrop()
                              .apply(new RequestOptions().override(140, 220))
                              //.load(R.drawable.logo)
                              .into(holder.img_petservice);
                  }


              }

              else {

                  if(pos==0){
                      if(currentItem.getImage() != null && !currentItem.getImage().isEmpty()) {

                          Glide.with(context)
                                  .load(currentItem.getImage())
                                  .centerCrop()
                                  .apply(new RequestOptions().override(140, 220))
                                  //.load(R.drawable.logo)
                                  .into(holder.img_petservice);
                      }else {

                          Glide.with(context)
                                  .load(APIClient.PROFILE_IMAGE_URL)
                                  .centerCrop()
                                  .apply(new RequestOptions().override(140, 220))
                                  //.load(R.drawable.logo)
                                  .into(holder.img_petservice);
                      }


                  }

                  else {
                      if(currentItem.getImage() != null && !currentItem.getImage().isEmpty()) {


                          Glide.with(context)
                                  .load(currentItem.getImage())
                                  .centerCrop()
                                  .apply(new RequestOptions().override(140, 220))
                                  .into(holder.img_petservice);
                      }else{
                          Glide.with(context)
                                  .load(APIClient.PROFILE_IMAGE_URL)
                                  .centerCrop()
                                  .apply(new RequestOptions().override(140, 220))
                                  .into(holder.img_petservice);
                      }


                  }


              }

          }
          else{
            Glide.with(context)
                    .load(R.drawable.no_image)
                    .into(holder.img_petservice);

        }

          if(currentItem.getTitle() != null){
              holder.txt_title.setText(currentItem.getTitle());
          }
          if(currentItem.getSub_title() != null){
              holder.txt_sub_title.setText(currentItem.getSub_title());
          }

        holder.img_petservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {

              /*  if(serviceCatList.get(position).isSub_service_flag()){

                    Log.w(TAG,""+position);

                    Log.w(TAG,String.valueOf(serviceCatList.get(position).isSub_service_flag()));
*/
                    Intent intent = new Intent(context, PetSubServiceActivity.class);
                    intent.putExtra("catid",serviceCatList.get(position).get_id());
                    intent.putExtra("servname",serviceCatList.get(position).getTitle());
                    intent.putExtra("flag",serviceCatList.get(position).isSub_service_flag());
                    intent.putExtra("from","PetServices");
                    context.startActivity(intent);

                Log.w(TAG,"servname : "+serviceCatList.get(position).getTitle());

                }

             /*   else {

                    Log.w(TAG,""+position);

                    Log.w(TAG,String.valueOf(serviceCatList.get(position).isSub_service_flag()));

                    Log.w(TAG,"-->False");
                }

            }*/


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
