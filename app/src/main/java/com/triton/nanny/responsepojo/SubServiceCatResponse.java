package com.triton.nanny.responsepojo;

import java.util.List;

public class SubServiceCatResponse {


    /**
     * Status : Success
     * Message : SP_sub_servicesModel screen  List
     * Data : [{"_id":"6164231b65d9a57d7fc9575e","service_id":"5ff7f5171c72093650a13a14","icon_banner":"http://54.193.18.54:3000/api/uploads/1633952439347.png","title":"Sub Service 1","sub_title":"this is good for health","offer_title":"Upto 20 %"},{"_id":"6164232765d9a57d7fc9575f","service_id":"5ff7f5171c72093650a13a14","icon_banner":"http://54.193.18.54:3000/api/uploads/1633952439347.png","title":"Sub Service 2","sub_title":"this is good for health","offer_title":"Upto 20 %"}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 6164231b65d9a57d7fc9575e
     * service_id : 5ff7f5171c72093650a13a14
     * icon_banner : http://54.193.18.54:3000/api/uploads/1633952439347.png
     * title : Sub Service 1
     * sub_title : this is good for health
     * offer_title : Upto 20 %
     */

    private List<DataBean> Data;

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

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String _id;
        private String service_id;
        private String icon_banner;
        private String title;
        private String sub_title;
        private String offer_title;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getIcon_banner() {
            return icon_banner;
        }

        public void setIcon_banner(String icon_banner) {
            this.icon_banner = icon_banner;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public String getOffer_title() {
            return offer_title;
        }

        public void setOffer_title(String offer_title) {
            this.offer_title = offer_title;
        }
    }
}
