package com.triton.nanny.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.triton.nanny.R;
import com.triton.nanny.responsepojo.NotificationGetlistResponse;
import com.triton.nanny.responsepojo.TransactionHistoryResponse;

import java.util.List;


public class TransactionHistoryAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "TransactionHistoryAdapter";
    private Context context;

    TransactionHistoryResponse.DataBean.TransactionBean currentItem;
    private List<TransactionHistoryResponse.DataBean.TransactionBean> transactionHistoryResponseList;


    private int currentSelectedPosition = RecyclerView.NO_POSITION;



    public TransactionHistoryAdapter(Context context, List<TransactionHistoryResponse.DataBean.TransactionBean> transactionHistoryResponseList) {
        this.transactionHistoryResponseList = transactionHistoryResponseList;
        this.context = context;

       

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_transaction_history_cardview, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

  @SuppressLint("SetTextI18n")
  private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = transactionHistoryResponseList.get(position);

        if(currentItem.getStatus() != null && currentItem.getStatus().equalsIgnoreCase("credit")){
            if(currentItem.getTransaction_amount() != 0){
                holder.txt_amount.setTextColor(context.getResources().getColor(R.color.medium_green));
                holder.txt_amount.setText("+ "+"\u20B9 "+currentItem.getTransaction_amount());
            }else{
                holder.txt_amount.setText("");
            }

        }else{
            if(currentItem.getTransaction_amount() != 0){
                holder.txt_amount.setTextColor(context.getResources().getColor(R.color.vermillion));
                holder.txt_amount.setText("- "+"\u20B9 "+currentItem.getTransaction_amount());
            }else{
                holder.txt_amount.setText("");
            }
        }

     if(currentItem.getAppointment_id() != null) {
         holder.txt_appointid.setText(currentItem.getAppointment_id());
     } if(currentItem.getTransaction_date_time() != null) {
         holder.txt_dateandtime.setText(currentItem.getTransaction_date_time());
     }if(currentItem.getTransaction_id() != null) {
         holder.txt_transactionid.setText(currentItem.getTransaction_id());
     }if(currentItem.getPayment_type() != null) {
         holder.txt_paymenttype.setText(currentItem.getPayment_type());
     }






      holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }




        });



   }

   @Override
    public int getItemCount() {
        
        return transactionHistoryResponseList.size();
    }
   

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_amount,txt_appointid,txt_dateandtime,txt_transactionid,txt_paymenttype;
        public LinearLayout ll_root;




        public ViewHolderOne(View itemView) {
            super(itemView);
            ll_root = itemView.findViewById(R.id.ll_root);
            txt_amount = itemView.findViewById(R.id.txt_amount);
            txt_appointid = itemView.findViewById(R.id.txt_appointid);
            txt_dateandtime = itemView.findViewById(R.id.txt_dateandtime);
            txt_transactionid = itemView.findViewById(R.id.txt_transactionid);
            txt_paymenttype = itemView.findViewById(R.id.txt_paymenttype);

        }

    }

}
