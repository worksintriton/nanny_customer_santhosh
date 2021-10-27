package com.triton.nanny.requestpojo;

public class AppoinmentUpdateRequest {

    /**
     * _id : 5fc639ea72fc42044bfa1683
     * work_status :
     * payment_id :
     */

    private String _id;
    private String work_status;
    private String payment_id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getWork_status() {
        return work_status;
    }

    public void setWork_status(String work_status) {
        this.work_status = work_status;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }
}
