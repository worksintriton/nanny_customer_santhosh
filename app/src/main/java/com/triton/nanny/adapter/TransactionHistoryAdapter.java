package com.triton.nanny.adapter;
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
import java.util.List;


public class TransactionHistoryAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "TransactionHistoryAdapter";
    private Context context;

    NotificationGetlistResponse.DataBean currentItem;
  private List<NotificationGetlistResponse.DataBean> notificationGetlistResponseList;


    private int currentSelectedPosition = RecyclerView.NO_POSITION;



    public TransactionHistoryAdapter(Context context, List<NotificationGetlistResponse.DataBean> notificationGetlistResponseList) {
        this.notificationGetlistResponseList = notificationGetlistResponseList;
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

  private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = notificationGetlistResponseList.get(position);

        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }




        });



   }

   @Override
    public int getItemCount() {
        
        return notificationGetlistResponseList.size();
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
