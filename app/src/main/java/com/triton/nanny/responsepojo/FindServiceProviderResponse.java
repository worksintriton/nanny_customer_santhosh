package com.triton.nanny.responsepojo;

public class FindServiceProviderResponse {


    /**
     * Status : Success
     * Message : Request Details
     * Data : {"_id":"616d1c89214ef52ba9313d35","appointment_id":"SP001","customer_name":"Mohammed imthiyas","service_name":"Vet Care","location":"Muthamil nager chennai","selected_date":"24-10-2021","selected_time":"11:00 AM","start_time":"2021-10-18T07:01:50.000Z","end_time":"2021-10-18T07:02:00.000Z","status":"Accept","user_id":"616d1bdf214ef52ba9313d33","sp_id":"6164232765d9a57d7fc9575f","updatedAt":"2021-10-18T07:23:02.569Z","createdAt":"2021-10-18T07:04:41.131Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * _id : 616d1c89214ef52ba9313d35
     * appointment_id : SP001
     * customer_name : Mohammed imthiyas
     * service_name : Vet Care
     * location : Muthamil nager chennai
     * selected_date : 24-10-2021
     * selected_time : 11:00 AM
     * start_time : 2021-10-18T07:01:50.000Z
     * end_time : 2021-10-18T07:02:00.000Z
     * status : Accept
     * user_id : 616d1bdf214ef52ba9313d33
     * sp_id : 6164232765d9a57d7fc9575f
     * updatedAt : 2021-10-18T07:23:02.569Z
     * createdAt : 2021-10-18T07:04:41.131Z
     * __v : 0
     */

    private DataBean Data;
    private int Code;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public static class DataBean {
        private String _id;
        private String appointment_id;
        private String customer_name;
        private String service_name;
        private String location;
        private String selected_date;
        private String selected_time;
        private String start_time;
        private String end_time;
        private String status;
        private String user_id;
        private String sp_id;
        private String updatedAt;
        private String createdAt;
        private int __v;


        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getAppointment_id() {
            return appointment_id;
        }

        public void setAppointment_id(String appointment_id) {
            this.appointment_id = appointment_id;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getSelected_date() {
            return selected_date;
        }

        public void setSelected_date(String selected_date) {
            this.selected_date = selected_date;
        }

        public String getSelected_time() {
            return selected_time;
        }

        public void setSelected_time(String selected_time) {
            this.selected_time = selected_time;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

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

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }
}
