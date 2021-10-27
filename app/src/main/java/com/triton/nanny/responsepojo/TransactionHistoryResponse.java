package com.triton.nanny.responsepojo;

import java.util.List;

public class TransactionHistoryResponse {

    /**
     * Status : Success
     * Message : Transaction Details of Customer
     * Data : {"spend_amount":1050,"refund_amount":150,"transaction":[{"_id":"6176b9c4a3d6052404036df9","user_id":"6163d60a489ccc3d894683d2","sp_id":"6172b83d8dd3e15b142de045","transaction_id":"TRN12345","transaction_amount":100,"transaction_date_time":"23-10-2021 11:00 AM","transaction_date":"23-10-2021","payment_type":"Online","appointment_id":"SP_098123","mobile_type":"Android","status":"credit","reason":"","delete_status":false,"updatedAt":"2021-10-25T14:05:56.198Z","createdAt":"2021-10-25T14:05:56.198Z","__v":0},{"_id":"6176b9cca3d6052404036dfa","user_id":"6163d60a489ccc3d894683d2","sp_id":"6172b83d8dd3e15b142de045","transaction_id":"TRN12345","transaction_amount":350,"transaction_date_time":"23-10-2021 11:00 AM","transaction_date":"23-10-2021","payment_type":"Online","appointment_id":"SP_098123","mobile_type":"Android","status":"credit","reason":"","delete_status":false,"updatedAt":"2021-10-25T14:06:04.658Z","createdAt":"2021-10-25T14:06:04.658Z","__v":0},{"_id":"6176b9d0a3d6052404036dfb","user_id":"6163d60a489ccc3d894683d2","sp_id":"6172b83d8dd3e15b142de045","transaction_id":"TRN12345","transaction_amount":600,"transaction_date_time":"23-10-2021 11:00 AM","transaction_date":"23-10-2021","payment_type":"Online","appointment_id":"SP_098123","mobile_type":"Android","status":"credit","reason":"","delete_status":false,"updatedAt":"2021-10-25T14:06:08.778Z","createdAt":"2021-10-25T14:06:08.778Z","__v":0},{"_id":"6176b9dda3d6052404036dfc","user_id":"6163d60a489ccc3d894683d2","sp_id":"6172b83d8dd3e15b142de045","transaction_id":"TRN12345","transaction_amount":150,"transaction_date_time":"23-10-2021 11:00 AM","transaction_date":"23-10-2021","payment_type":"Online","appointment_id":"SP_098123","mobile_type":"Android","status":"debit","reason":"","delete_status":false,"updatedAt":"2021-10-25T14:06:21.966Z","createdAt":"2021-10-25T14:06:21.966Z","__v":0}]}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * spend_amount : 1050
     * refund_amount : 150
     * transaction : [{"_id":"6176b9c4a3d6052404036df9","user_id":"6163d60a489ccc3d894683d2","sp_id":"6172b83d8dd3e15b142de045","transaction_id":"TRN12345","transaction_amount":100,"transaction_date_time":"23-10-2021 11:00 AM","transaction_date":"23-10-2021","payment_type":"Online","appointment_id":"SP_098123","mobile_type":"Android","status":"credit","reason":"","delete_status":false,"updatedAt":"2021-10-25T14:05:56.198Z","createdAt":"2021-10-25T14:05:56.198Z","__v":0},{"_id":"6176b9cca3d6052404036dfa","user_id":"6163d60a489ccc3d894683d2","sp_id":"6172b83d8dd3e15b142de045","transaction_id":"TRN12345","transaction_amount":350,"transaction_date_time":"23-10-2021 11:00 AM","transaction_date":"23-10-2021","payment_type":"Online","appointment_id":"SP_098123","mobile_type":"Android","status":"credit","reason":"","delete_status":false,"updatedAt":"2021-10-25T14:06:04.658Z","createdAt":"2021-10-25T14:06:04.658Z","__v":0},{"_id":"6176b9d0a3d6052404036dfb","user_id":"6163d60a489ccc3d894683d2","sp_id":"6172b83d8dd3e15b142de045","transaction_id":"TRN12345","transaction_amount":600,"transaction_date_time":"23-10-2021 11:00 AM","transaction_date":"23-10-2021","payment_type":"Online","appointment_id":"SP_098123","mobile_type":"Android","status":"credit","reason":"","delete_status":false,"updatedAt":"2021-10-25T14:06:08.778Z","createdAt":"2021-10-25T14:06:08.778Z","__v":0},{"_id":"6176b9dda3d6052404036dfc","user_id":"6163d60a489ccc3d894683d2","sp_id":"6172b83d8dd3e15b142de045","transaction_id":"TRN12345","transaction_amount":150,"transaction_date_time":"23-10-2021 11:00 AM","transaction_date":"23-10-2021","payment_type":"Online","appointment_id":"SP_098123","mobile_type":"Android","status":"debit","reason":"","delete_status":false,"updatedAt":"2021-10-25T14:06:21.966Z","createdAt":"2021-10-25T14:06:21.966Z","__v":0}]
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
        private int spend_amount;
        private int refund_amount;
        /**
         * _id : 6176b9c4a3d6052404036df9
         * user_id : 6163d60a489ccc3d894683d2
         * sp_id : 6172b83d8dd3e15b142de045
         * transaction_id : TRN12345
         * transaction_amount : 100
         * transaction_date_time : 23-10-2021 11:00 AM
         * transaction_date : 23-10-2021
         * payment_type : Online
         * appointment_id : SP_098123
         * mobile_type : Android
         * status : credit
         * reason :
         * delete_status : false
         * updatedAt : 2021-10-25T14:05:56.198Z
         * createdAt : 2021-10-25T14:05:56.198Z
         * __v : 0
         */

        private List<TransactionBean> transaction;

        public int getSpend_amount() {
            return spend_amount;
        }

        public void setSpend_amount(int spend_amount) {
            this.spend_amount = spend_amount;
        }

        public int getRefund_amount() {
            return refund_amount;
        }

        public void setRefund_amount(int refund_amount) {
            this.refund_amount = refund_amount;
        }

        public List<TransactionBean> getTransaction() {
            return transaction;
        }

        public void setTransaction(List<TransactionBean> transaction) {
            this.transaction = transaction;
        }

        public static class TransactionBean {
            private String _id;
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
}
