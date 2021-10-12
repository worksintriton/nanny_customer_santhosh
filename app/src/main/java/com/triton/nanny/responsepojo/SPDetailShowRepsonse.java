package com.triton.nanny.responsepojo;

import java.util.List;

public class SPDetailShowRepsonse {


    /**
     * Status : Success
     * Message : SP_sub_servicesModel screen  List
     * Data : {"banner_image":[{"image":"http://54.193.18.54:3000/api/uploads/1633952464878.png"},{"image":"http://54.193.18.54:3000/api/uploads/1633952464878.png"}],"_id":"6164231b65d9a57d7fc9575e","service_id":"5ff7f5171c72093650a13a14","icon_banner":"http://54.193.18.54:3000/api/uploads/1633952439347.png","title":"Sub Service 1","sub_title":"this is good for health","offer_title":"Upto 20 %","price":200,"rating":5,"service_des":"this is good for all the product","date_and_time":"23-10-2021 11:00 AM","delete_status":false,"updatedAt":"2021-10-11T11:42:19.559Z","createdAt":"2021-10-11T11:42:19.559Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * banner_image : [{"image":"http://54.193.18.54:3000/api/uploads/1633952464878.png"},{"image":"http://54.193.18.54:3000/api/uploads/1633952464878.png"}]
     * _id : 6164231b65d9a57d7fc9575e
     * service_id : 5ff7f5171c72093650a13a14
     * icon_banner : http://54.193.18.54:3000/api/uploads/1633952439347.png
     * title : Sub Service 1
     * sub_title : this is good for health
     * offer_title : Upto 20 %
     * price : 200
     * rating : 5
     * service_des : this is good for all the product
     * date_and_time : 23-10-2021 11:00 AM
     * delete_status : false
     * updatedAt : 2021-10-11T11:42:19.559Z
     * createdAt : 2021-10-11T11:42:19.559Z
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
        private String service_id;
        private String icon_banner;
        private String title;
        private String sub_title;
        private String offer_title;
        private int price;
        private int rating;
        private String service_des;
        private String date_and_time;
        private boolean delete_status;
        private String updatedAt;
        private String createdAt;
        private int __v;
        /**
         * image : http://54.193.18.54:3000/api/uploads/1633952464878.png
         */

        private List<BannerImageBean> banner_image;

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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getService_des() {
            return service_des;
        }

        public void setService_des(String service_des) {
            this.service_des = service_des;
        }

        public String getDate_and_time() {
            return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
            this.date_and_time = date_and_time;
        }

        public boolean isDelete_status() {
            return delete_status;
        }

        public void setDelete_status(boolean delete_status) {
            this.delete_status = delete_status;
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

        public List<BannerImageBean> getBanner_image() {
            return banner_image;
        }

        public void setBanner_image(List<BannerImageBean> banner_image) {
            this.banner_image = banner_image;
        }

        public static class BannerImageBean {
            private String image;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
