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

    public PetSubServicesAdapter(Context context, List<SubServiceCatResponse.DataBean> serviceCatList, boolean flag) {
        this.serviceCatList = serviceCatList;
        this.context = context;
        this.flag = flag;

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
          Log.w(TAG,"Size : "+serviceCatList.size()+" "+" Images : "+serviceCatList.get(position).getImg_path());
          if (currentItem.getImg_path() != null && !currentItem.getImg_path().isEmpty()) {

              int pos = position % 2 ;

              Log.w(TAG,"position "+pos);

              if(currentItem.getImg_path() != null && !currentItem.getImg_path().isEmpty()) {
                  Glide.with(context)
                          .load(currentItem.getImg_path())
                          //.load(R.drawable.logo)
                          .into(holder.img_petservice);
              }else{
                  Glide.with(context)
                          .load(APIClient.PROFILE_IMAGE_URL)
                          //.load(R.drawable.logo)
                          .into(holder.img_petservice);

              }


          }
          else{
            Glide.with(context)
                    .load(R.drawable.no_image)
                    .into(holder.img_petservice);

        }

          if(currentItem.getImg_title() != null&&!currentItem.getImg_title().isEmpty()){

              holder.txt_title.setText(currentItem.getImg_title());

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
                intent.putExtra("catid",serviceCatList.get(position).get_id());
                intent.putExtra("from","PetSubServices");
                intent.putExtra("flag",flag);
                context.startActivity(intent);
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
