package com.triton.nanny.responsepojo;

import java.util.List;

public class SplashScreenResponse {


    /**
     * Status : Success
     * Message : Splash screen  Details
     * Data : [{"_id":"60e2c8bbb374a2248e54a345","img_path":"http://54.212.108.156:3000/api/uploads/1625475257103.jpeg","img_title":"title 2","img_index":4,"show_status":true,"date_and_time":"Mon Jul 05 2021 14:24:19 GMT+0530 (India Standard Time)","delete_status":false,"updatedAt":"2021-07-05T08:54:19.425Z","createdAt":"2021-07-05T08:54:19.425Z","__v":0},{"_id":"60e6d09526d0cd1e18bd7f8d","img_path":"http://54.212.108.156:3000/api/uploads/1625739401990.png","img_title":".","img_index":4,"show_status":true,"date_and_time":"Thu Jul 08 2021 15:46:52 GMT+0530 (India Standard Time)","delete_status":false,"updatedAt":"2021-07-08T10:16:53.694Z","createdAt":"2021-07-08T10:16:53.694Z","__v":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 60e2c8bbb374a2248e54a345
     * img_path : http://54.212.108.156:3000/api/uploads/1625475257103.jpeg
     * img_title : title 2
     * img_index : 4
     * show_status : true
     * date_and_time : Mon Jul 05 2021 14:24:19 GMT+0530 (India Standard Time)
     * delete_status : false
     * updatedAt : 2021-07-05T08:54:19.425Z
     * createdAt : 2021-07-05T08:54:19.425Z
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
