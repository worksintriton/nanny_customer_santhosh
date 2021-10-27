package com.triton.nanny.requestpojo;

public class TransactionCreateRequest {

    /**
     * user_id : 6163d60a489ccc3d894683d2
     * sp_id : 6172b83d8dd3e15b142de045
     * transaction_id : TRN12345
     * transaction_amount : 150
     * transaction_date_time : 23-10-2021 11:00 AM
     * transaction_date : 23-10-2021
     * payment_type : Online
     * appointment_id : SP_098123
     * mobile_type : Android
     * status : debit
     * reason :
     */

    private String user_id;
    private String sp_id;
    private String transaction_id;
    private int transaction_amount;
    private String transaction_date_time;
    private String transaction_date;
    private String payment_type;
    private String appointment_id;
    private String mobile_type;
    private String status;
    private String reason;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSp_id() {
        return sp_id;
    }

    public void setSp_id(String sp_id) {
        this.sp_id = sp_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(int transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_date_time() {
        return transaction_date_time;
    }

    public void setTransaction_date_time(String transaction_date_time) {
        this.transaction_date_time = transaction_date_time;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getMobile_type() {
        return mobile_type;
    }

    public void setMobile_type(String mobile_type) {
        this.mobile_type = mobile_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
