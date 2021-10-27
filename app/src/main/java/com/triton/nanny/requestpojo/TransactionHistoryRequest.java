package com.triton.nanny.requestpojo;

public class TransactionHistoryRequest {

    /**
     * user_id : 6163d60a489ccc3d894683d2
     * filter_date :
     */

    private String user_id;
    private String filter_date;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFilter_date() {
        return filter_date;
    }

    public void setFilter_date(String filter_date) {
        this.filter_date = filter_date;
    }
}
