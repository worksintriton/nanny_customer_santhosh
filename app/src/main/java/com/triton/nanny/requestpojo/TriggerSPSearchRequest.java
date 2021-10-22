package com.triton.nanny.requestpojo;

public class TriggerSPSearchRequest {


    /**
     * service_name : Vet Care
     * sub_service_title : Sub Service 2
     * customer_name : Mohammed imthiyas
     * location : No 2 Muthamil nager Kodugaiyur
     * selected_date : 23-10-2021
     * selected_time : 11:00 AM
     * user_id : 6164232765d9a57d7fc9575
     * currentdateandtime
     */

    private String service_name;
    private String sub_service_title;
    private String customer_name;
    private String location;
    private String selected_date;
    private String selected_time;
    private String user_id;
    private String currentdateandtime;

    public String getCurrentdateandtime() {
        return currentdateandtime;
    }

    public void setCurrentdateandtime(String currentdateandtime) {
        this.currentdateandtime = currentdateandtime;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getSub_service_title() {
        return sub_service_title;
    }

    public void setSub_service_title(String sub_service_title) {
        this.sub_service_title = sub_service_title;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
