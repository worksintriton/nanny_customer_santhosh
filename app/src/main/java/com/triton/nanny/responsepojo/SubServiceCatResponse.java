package com.triton.nanny.responsepojo;

import java.util.List;

public class SubServiceCatResponse {

    /**
     * Status : Success
     * Message : SP_sub_servicesModel screen  List
     * Data : [{"_id":"612746a9dc2406057b5aeb12","img_path":"http://54.212.108.156:3000/api/uploads/1624556378399.png","img_title":"Pet daycare 1","img_index":4,"server_id":"602d1fc0562e0916bc9b3245","img_subtitle":".......","img_banner":"http://54.212.108.156:3000/api/uploads/1625730544445.png","show_status":true,"date_and_time":"Thu Jul 08 2021 13:19:07 GMT+0530 (India Standard Time)","delete_status":false,"updatedAt":"2021-08-26T07:45:45.755Z","createdAt":"2021-08-26T07:45:45.755Z","__v":0},{"_id":"612746acdc2406057b5aeb13","img_path":"http://54.212.108.156:3000/api/uploads/1624556378399.png","img_title":"Pet daycare 2","img_index":4,"server_id":"602d1fc0562e0916bc9b3245","img_subtitle":".......","img_banner":"http://54.212.108.156:3000/api/uploads/1625730544445.png","show_status":true,"date_and_time":"Thu Jul 08 2021 13:19:07 GMT+0530 (India Standard Time)","delete_status":false,"updatedAt":"2021-08-26T07:45:48.355Z","createdAt":"2021-08-26T07:45:48.355Z","__v":0},{"_id":"612746aedc2406057b5aeb14","img_path":"http://54.212.108.156:3000/api/uploads/1624556378399.png","img_title":"Pet daycare 3","img_index":4,"server_id":"602d1fc0562e0916bc9b3245","img_subtitle":".......","img_banner":"http://54.212.108.156:3000/api/uploads/1625730544445.png","show_status":true,"date_and_time":"Thu Jul 08 2021 13:19:07 GMT+0530 (India Standard Time)","delete_status":false,"updatedAt":"2021-08-26T07:45:50.795Z","createdAt":"2021-08-26T07:45:50.795Z","__v":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 612746a9dc2406057b5aeb12
     * img_path : http://54.212.108.156:3000/api/uploads/1624556378399.png
     * img_title : Pet daycare 1
     * img_index : 4
     * server_id : 602d1fc0562e0916bc9b3245
     * img_subtitle : .......
     * img_banner : http://54.212.108.156:3000/api/uploads/1625730544445.png
     * show_status : true
     * date_and_time : Thu Jul 08 2021 13:19:07 GMT+0530 (India Standard Time)
     * delete_status : false
     * updatedAt : 2021-08-26T07:45:45.755Z
     * createdAt : 2021-08-26T07:45:45.755Z
     * __v : 0
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
        private String img_path;
        private String img_title;
        private int img_index;
        private String server_id;
        private String img_subtitle;
        private String img_banner;
        private boolean show_status;
        private String date_and_time;
        private boolean delete_status;
        private String updatedAt;
        private String createdAt;
        private int __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getImg_path() {
            return img_path;
        }

        public void setImg_path(String img_path) {
            this.img_path = img_path;
        }

        public String getImg_title() {
            return img_title;
        }

        public void setImg_title(String img_title) {
            this.img_title = img_title;
        }

        public int getImg_index() {
            return img_index;
        }

        public void setImg_index(int img_index) {
            this.img_index = img_index;
        }

        public String getServer_id() {
            return server_id;
        }

        public void setServer_id(String server_id) {
            this.server_id = server_id;
        }

        public String getImg_subtitle() {
            return img_subtitle;
        }

        public void setImg_subtitle(String img_subtitle) {
            this.img_subtitle = img_subtitle;
        }

        public String getImg_banner() {
            return img_banner;
        }

        public void setImg_banner(String img_banner) {
            this.img_banner = img_banner;
        }

        public boolean isShow_status() {
            return show_status;
        }

        public void setShow_status(boolean show_status) {
            this.show_status = show_status;
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
    }
}
