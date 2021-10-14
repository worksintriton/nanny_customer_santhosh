package com.triton.nanny.responsepojo;

import java.util.List;

public class ServiceCatResponse {


    /**
     * Status : Success
     * Message : Service Cat List
     * Data : [{"_id":"5ff7f5171c72093650a13a14","image":"http://54.212.108.156:3000/api/uploads/1625730474196.png","title":"Pet Grooming","sub_title":".......","sub_service_flag":false},{"_id":"5ff815d4414b1052a09bb2b1","image":"http://54.212.108.156:3000/api/uploads/1625730503186.png","title":" Pet Training","sub_title":".......","sub_service_flag":true},{"_id":"602d1fc0562e0916bc9b3245","image":"http://54.212.108.156:3000/api/uploads/1625730544445.png","title":"Pet daycare","sub_title":".......","sub_service_flag":true},{"_id":"602d1fd0562e0916bc9b3247","image":"http://54.212.108.156:3000/api/uploads/1624556324448.png","title":" Pet Boarding","sub_title":".......","sub_service_flag":false},{"_id":"603dc7a01ef90c51d57cf775","image":"http://54.212.108.156:3000/api/uploads/1624556851643.png","title":"Pet Sitting","sub_title":"","sub_service_flag":false},{"_id":"60d479f45035a966302a37a5","image":"http://54.212.108.156:3000/api/uploads/1624556991509.png","title":"Vet Care","sub_title":"......","sub_service_flag":true}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 5ff7f5171c72093650a13a14
     * image : http://54.212.108.156:3000/api/uploads/1625730474196.png
     * title : Pet Grooming
     * sub_title : .......
     * sub_service_flag : false
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
        private String image;
        private String title;
        private String sub_title;
        private boolean sub_service_flag;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public boolean isSub_service_flag() {
            return sub_service_flag;
        }

        public void setSub_service_flag(boolean sub_service_flag) {
            this.sub_service_flag = sub_service_flag;
        }
    }
}
