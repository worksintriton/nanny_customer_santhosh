package com.triton.nanny.responsepojo;

import java.util.List;

public class SPAppointmentDetailsResponse {


    /**
     * Status : Success
     * Message : Appointment Details
     * Data : {"sp_attched":[],"sp_business_info":[{"bus_service_list":[{"service_name":"Bartender","subsericelist":[{"_id":"616ff0dcea122f58f9d09803","isservice":false,"service_id":"616ff0a4ea122f58f9d09802","title":"Bartender Cleaning"},{"_id":"616ff0f3ea122f58f9d09804","isservice":true,"service_id":"616ff0a4ea122f58f9d09802","title":"Bartender Services"}]},{"service_name":"Cleaning Services","subsericelist":[{"_id":"616feeb83d269556cda2bd99","isservice":true,"service_id":"616feca476f39652d97de5d2","title":"Plumber Services"},{"_id":"616feee93d269556cda2bd9a","isservice":true,"service_id":"616feca476f39652d97de5d2","title":"cleaning & spring"}]}],"bus_spec_list":[{"bus_spec_list":"Kennel Cut"}],"bus_service_gall":[{"bus_service_gall":"http://54.193.18.54:3000/api/uploads/1634205785736.jpg"}],"bus_certif":[{"bus_certif":"http://54.193.18.54:3000/api/uploads/1634205831306.jpg"}],"_id":"616ff54f0c57d55e425b7816","user_id":"6167cca3e605fa4c7206e49f","bus_user_name":"DINESH","bus_user_email":"iddineshkumar@gmail.com","bussiness_name":"fgg","bus_user_phone":"9638527410","bus_profile":"http://54.193.18.54:3000/api/uploads/1634205803590.jpg","bus_proof":"http://54.193.18.54:3000/api/uploads/1634205820909.jpg","date_and_time":"14/10/2021 03:34 PM","mobile_type":"Android","profile_status":true,"profile_verification_status":"Not verified","thumbnail_image":"","sp_loc":"Chennai 2A, 3rd Main Rd, Ram Nagar, Ramnagar South, Dhadeswaram Nagar, Velachery, Chennai, Tamil Nadu 600042, India","sp_lat":12.983240215899578,"sp_long":80.22349512276435,"city_name":"Chennai","delete_status":false,"calender_status":false,"comments":0,"rating":5,"sp_info":"gh","updatedAt":"2021-10-20T11:43:18.466Z","createdAt":"2021-10-20T10:54:07.463Z","__v":0}],"_id":"6170053c6456aa6e363001d8","sp_id":{"_id":"6167cca3e605fa4c7206e49f","first_name":"DINESH","last_name":"Kumar","user_email":"iddineshkumar@gmail.com","user_phone":"9638527410","date_of_reg":"14/10/2021 11:52 AM","user_type":2,"ref_code":"","my_ref_code":"YDVHJVD","user_status":"complete","otp":123456,"profile_img":"","user_email_verification":false,"fb_token":"","device_id":"","device_type":"","mobile_type":"Android","delete_status":false,"updatedAt":"2021-10-20T10:08:19.464Z","createdAt":"2021-10-14T06:22:27.990Z","__v":0},"appointment_UID":"SP-1634731324147","booking_date":"20-10-2021","booking_time":"5:31 PM","booking_date_time":"20-10-2021 5:31 PM","user_id":{"_id":"6163d60a489ccc3d894683d2","first_name":"Maddy","last_name":"Sam","user_email":"maddykrish@gmail.com","user_phone":"9999999998","date_of_reg":"11/10/2021 11:43 AM","user_type":1,"ref_code":"","my_ref_code":"VMNWKAB","user_status":"complete","otp":123456,"profile_img":"","user_email_verification":false,"fb_token":"","device_id":"","device_type":"","mobile_type":"Android","delete_status":false,"updatedAt":"2021-10-20T11:33:26.596Z","createdAt":"2021-10-11T06:13:30.236Z","__v":0},"pet_id":"","additional_info":"","appoinment_status":"Incomplete","start_appointment_status":"Not Started","end_appointment_status":"Not End","sp_feedback":"","sp_rate":"","user_feedback":"","user_rate":"0","display_date":"2021-10-20 17:31:00","server_date_time":"","payment_id":"","payment_method":"Online","service_name":"Cleaning Services","service_amount":"100","service_time":"5:31 PM","completed_at":"","missed_at":"","mobile_type":"Android","delete_status":false,"date_and_time":"20-10-2021 05:32 PM","hrs":"1","total_hours":"","additional_hours":"","addition_amount":"","addition_payment_method":"","addition_payment_status":"","total_paid_amount":"","start_otp":"904208","end_otp":"428340","address_text":"103, Vmr Theatre Back Side Rd, Shevapet, Salem, Tamil Nadu 636002, India","state":"Tamil Nadu","city":"Salem","pin_code":"636002","lat":"","lon":"","updatedAt":"2021-10-20T12:02:04.163Z","createdAt":"2021-10-20T12:02:04.157Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * sp_attched : []
     * sp_business_info : [{"bus_service_list":[{"service_name":"Bartender","subsericelist":[{"_id":"616ff0dcea122f58f9d09803","isservice":false,"service_id":"616ff0a4ea122f58f9d09802","title":"Bartender Cleaning"},{"_id":"616ff0f3ea122f58f9d09804","isservice":true,"service_id":"616ff0a4ea122f58f9d09802","title":"Bartender Services"}]},{"service_name":"Cleaning Services","subsericelist":[{"_id":"616feeb83d269556cda2bd99","isservice":true,"service_id":"616feca476f39652d97de5d2","title":"Plumber Services"},{"_id":"616feee93d269556cda2bd9a","isservice":true,"service_id":"616feca476f39652d97de5d2","title":"cleaning & spring"}]}],"bus_spec_list":[{"bus_spec_list":"Kennel Cut"}],"bus_service_gall":[{"bus_service_gall":"http://54.193.18.54:3000/api/uploads/1634205785736.jpg"}],"bus_certif":[{"bus_certif":"http://54.193.18.54:3000/api/uploads/1634205831306.jpg"}],"_id":"616ff54f0c57d55e425b7816","user_id":"6167cca3e605fa4c7206e49f","bus_user_name":"DINESH","bus_user_email":"iddineshkumar@gmail.com","bussiness_name":"fgg","bus_user_phone":"9638527410","bus_profile":"http://54.193.18.54:3000/api/uploads/1634205803590.jpg","bus_proof":"http://54.193.18.54:3000/api/uploads/1634205820909.jpg","date_and_time":"14/10/2021 03:34 PM","mobile_type":"Android","profile_status":true,"profile_verification_status":"Not verified","thumbnail_image":"","sp_loc":"Chennai 2A, 3rd Main Rd, Ram Nagar, Ramnagar South, Dhadeswaram Nagar, Velachery, Chennai, Tamil Nadu 600042, India","sp_lat":12.983240215899578,"sp_long":80.22349512276435,"city_name":"Chennai","delete_status":false,"calender_status":false,"comments":0,"rating":5,"sp_info":"gh","updatedAt":"2021-10-20T11:43:18.466Z","createdAt":"2021-10-20T10:54:07.463Z","__v":0}]
     * _id : 6170053c6456aa6e363001d8
     * sp_id : {"_id":"6167cca3e605fa4c7206e49f","first_name":"DINESH","last_name":"Kumar","user_email":"iddineshkumar@gmail.com","user_phone":"9638527410","date_of_reg":"14/10/2021 11:52 AM","user_type":2,"ref_code":"","my_ref_code":"YDVHJVD","user_status":"complete","otp":123456,"profile_img":"","user_email_verification":false,"fb_token":"","device_id":"","device_type":"","mobile_type":"Android","delete_status":false,"updatedAt":"2021-10-20T10:08:19.464Z","createdAt":"2021-10-14T06:22:27.990Z","__v":0}
     * appointment_UID : SP-1634731324147
     * booking_date : 20-10-2021
     * booking_time : 5:31 PM
     * booking_date_time : 20-10-2021 5:31 PM
     * user_id : {"_id":"6163d60a489ccc3d894683d2","first_name":"Maddy","last_name":"Sam","user_email":"maddykrish@gmail.com","user_phone":"9999999998","date_of_reg":"11/10/2021 11:43 AM","user_type":1,"ref_code":"","my_ref_code":"VMNWKAB","user_status":"complete","otp":123456,"profile_img":"","user_email_verification":false,"fb_token":"","device_id":"","device_type":"","mobile_type":"Android","delete_status":false,"updatedAt":"2021-10-20T11:33:26.596Z","createdAt":"2021-10-11T06:13:30.236Z","__v":0}
     * pet_id :
     * additional_info :
     * appoinment_status : Incomplete
     * start_appointment_status : Not Started
     * end_appointment_status : Not End
     * sp_feedback :
     * sp_rate :
     * user_feedback :
     * user_rate : 0
     * display_date : 2021-10-20 17:31:00
     * server_date_time :
     * payment_id :
     * payment_method : Online
     * service_name : Cleaning Services
     * service_amount : 100
     * service_time : 5:31 PM
     * completed_at :
     * missed_at :
     * mobile_type : Android
     * delete_status : false
     * date_and_time : 20-10-2021 05:32 PM
     * hrs : 1
     * total_hours :
     * additional_hours :
     * addition_amount :
     * addition_payment_method :
     * addition_payment_status :
     * total_paid_amount :
     * work_status :
     * start_otp : 904208
     * end_otp : 428340
     * address_text : 103, Vmr Theatre Back Side Rd, Shevapet, Salem, Tamil Nadu 636002, India
     * state : Tamil Nadu
     * city : Salem
     * pin_code : 636002
     * lat :
     * lon :
     * updatedAt : 2021-10-20T12:02:04.163Z
     * createdAt : 2021-10-20T12:02:04.157Z
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
        /**
         * _id : 6167cca3e605fa4c7206e49f
         * first_name : DINESH
         * last_name : Kumar
         * user_email : iddineshkumar@gmail.com
         * user_phone : 9638527410
         * date_of_reg : 14/10/2021 11:52 AM
         * user_type : 2
         * ref_code :
         * my_ref_code : YDVHJVD
         * user_status : complete
         * otp : 123456
         * profile_img :
         * user_email_verification : false
         * fb_token :
         * device_id :
         * device_type :
         * mobile_type : Android
         * delete_status : false
         * updatedAt : 2021-10-20T10:08:19.464Z
         * createdAt : 2021-10-14T06:22:27.990Z
         * __v : 0
         */

        private SpIdBean sp_id;
        private String appointment_UID;
        private String booking_date;
        private String booking_time;
        private String booking_date_time;
        /**
         * _id : 6163d60a489ccc3d894683d2
         * first_name : Maddy
         * last_name : Sam
         * user_email : maddykrish@gmail.com
         * user_phone : 9999999998
         * date_of_reg : 11/10/2021 11:43 AM
         * user_type : 1
         * ref_code :
         * my_ref_code : VMNWKAB
         * user_status : complete
         * otp : 123456
         * profile_img :
         * user_email_verification : false
         * fb_token :
         * device_id :
         * device_type :
         * mobile_type : Android
         * delete_status : false
         * updatedAt : 2021-10-20T11:33:26.596Z
         * createdAt : 2021-10-11T06:13:30.236Z
         * __v : 0
         */

        private UserIdBean user_id;
        private String pet_id;
        private String additional_info;
        private String appoinment_status;
        private String start_appointment_status;
        private String end_appointment_status;
        private String sp_feedback;
        private String sp_rate;
        private String user_feedback;
        private String user_rate;
        private String display_date;
        private String server_date_time;
        private String payment_id;
        private String payment_method;
        private String service_name;
        private String service_amount;
        private String service_time;
        private String completed_at;
        private String missed_at;
        private String mobile_type;
        private boolean delete_status;
        private String date_and_time;
        private String hrs;
        private String total_hours;
        private String additional_hours;
        private String addition_amount;
        private String addition_payment_method;
        private String addition_payment_status;
        private String total_paid_amount;
        private String work_status;
        private String start_otp;
        private String end_otp;
        private String address_text;
        private String state;
        private String city;
        private String pin_code;
        private String lat;
        private String lon;
        private String updatedAt;
        private String createdAt;
        private int __v;
        private List<?> sp_attched;

        /**
         * bus_service_list : [{"service_name":"Bartender","subsericelist":[{"_id":"616ff0dcea122f58f9d09803","isservice":false,"service_id":"616ff0a4ea122f58f9d09802","title":"Bartender Cleaning"},{"_id":"616ff0f3ea122f58f9d09804","isservice":true,"service_id":"616ff0a4ea122f58f9d09802","title":"Bartender Services"}]},{"service_name":"Cleaning Services","subsericelist":[{"_id":"616feeb83d269556cda2bd99","isservice":true,"service_id":"616feca476f39652d97de5d2","title":"Plumber Services"},{"_id":"616feee93d269556cda2bd9a","isservice":true,"service_id":"616feca476f39652d97de5d2","title":"cleaning & spring"}]}]
         * bus_spec_list : [{"bus_spec_list":"Kennel Cut"}]
         * bus_service_gall : [{"bus_service_gall":"http://54.193.18.54:3000/api/uploads/1634205785736.jpg"}]
         * bus_certif : [{"bus_certif":"http://54.193.18.54:3000/api/uploads/1634205831306.jpg"}]
         * _id : 616ff54f0c57d55e425b7816
         * user_id : 6167cca3e605fa4c7206e49f
         * bus_user_name : DINESH
         * bus_user_email : iddineshkumar@gmail.com
         * bussiness_name : fgg
         * bus_user_phone : 9638527410
         * bus_profile : http://54.193.18.54:3000/api/uploads/1634205803590.jpg
         * bus_proof : http://54.193.18.54:3000/api/uploads/1634205820909.jpg
         * date_and_time : 14/10/2021 03:34 PM
         * mobile_type : Android
         * profile_status : true
         * profile_verification_status : Not verified
         * thumbnail_image :
         * sp_loc : Chennai 2A, 3rd Main Rd, Ram Nagar, Ramnagar South, Dhadeswaram Nagar, Velachery, Chennai, Tamil Nadu 600042, India
         * sp_lat : 12.983240215899578
         * sp_long : 80.22349512276435
         * city_name : Chennai
         * delete_status : false
         * calender_status : false
         * comments : 0
         * rating : 5
         * sp_info : gh
         * updatedAt : 2021-10-20T11:43:18.466Z
         * createdAt : 2021-10-20T10:54:07.463Z
         * __v : 0
         */

        private List<SpBusinessInfoBean> sp_business_info;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public SpIdBean getSp_id() {
            return sp_id;
        }

        public void setSp_id(SpIdBean sp_id) {
            this.sp_id = sp_id;
        }

        public String getAppointment_UID() {
            return appointment_UID;
        }

        public void setAppointment_UID(String appointment_UID) {
            this.appointment_UID = appointment_UID;
        }

        public String getBooking_date() {
            return booking_date;
        }

        public void setBooking_date(String booking_date) {
            this.booking_date = booking_date;
        }

        public String getBooking_time() {
            return booking_time;
        }

        public void setBooking_time(String booking_time) {
            this.booking_time = booking_time;
        }

        public String getBooking_date_time() {
            return booking_date_time;
        }

        public void setBooking_date_time(String booking_date_time) {
            this.booking_date_time = booking_date_time;
        }

        public UserIdBean getUser_id() {
            return user_id;
        }

        public void setUser_id(UserIdBean user_id) {
            this.user_id = user_id;
        }

        public String getPet_id() {
            return pet_id;
        }

        public void setPet_id(String pet_id) {
            this.pet_id = pet_id;
        }

        public String getAdditional_info() {
            return additional_info;
        }

        public void setAdditional_info(String additional_info) {
            this.additional_info = additional_info;
        }

        public String getAppoinment_status() {
            return appoinment_status;
        }

        public void setAppoinment_status(String appoinment_status) {
            this.appoinment_status = appoinment_status;
        }

        public String getStart_appointment_status() {
            return start_appointment_status;
        }

        public void setStart_appointment_status(String start_appointment_status) {
            this.start_appointment_status = start_appointment_status;
        }

        public String getEnd_appointment_status() {
            return end_appointment_status;
        }

        public void setEnd_appointment_status(String end_appointment_status) {
            this.end_appointment_status = end_appointment_status;
        }

        public String getSp_feedback() {
            return sp_feedback;
        }

        public void setSp_feedback(String sp_feedback) {
            this.sp_feedback = sp_feedback;
        }

        public String getSp_rate() {
            return sp_rate;
        }

        public void setSp_rate(String sp_rate) {
            this.sp_rate = sp_rate;
        }

        public String getUser_feedback() {
            return user_feedback;
        }

        public void setUser_feedback(String user_feedback) {
            this.user_feedback = user_feedback;
        }

        public String getUser_rate() {
            return user_rate;
        }

        public void setUser_rate(String user_rate) {
            this.user_rate = user_rate;
        }

        public String getDisplay_date() {
            return display_date;
        }

        public void setDisplay_date(String display_date) {
            this.display_date = display_date;
        }

        public String getServer_date_time() {
            return server_date_time;
        }

        public void setServer_date_time(String server_date_time) {
            this.server_date_time = server_date_time;
        }

        public String getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(String payment_id) {
            this.payment_id = payment_id;
        }

        public String getPayment_method() {
            return payment_method;
        }

        public void setPayment_method(String payment_method) {
            this.payment_method = payment_method;
        }

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public String getService_amount() {
            return service_amount;
        }

        public void setService_amount(String service_amount) {
            this.service_amount = service_amount;
        }

        public String getService_time() {
            return service_time;
        }

        public void setService_time(String service_time) {
            this.service_time = service_time;
        }

        public String getCompleted_at() {
            return completed_at;
        }

        public void setCompleted_at(String completed_at) {
            this.completed_at = completed_at;
        }

        public String getMissed_at() {
            return missed_at;
        }

        public void setMissed_at(String missed_at) {
            this.missed_at = missed_at;
        }

        public String getMobile_type() {
            return mobile_type;
        }

        public void setMobile_type(String mobile_type) {
            this.mobile_type = mobile_type;
        }

        public boolean isDelete_status() {
            return delete_status;
        }

        public void setDelete_status(boolean delete_status) {
            this.delete_status = delete_status;
        }

        public String getDate_and_time() {
            return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
            this.date_and_time = date_and_time;
        }

        public String getHrs() {
            return hrs;
        }

        public void setHrs(String hrs) {
            this.hrs = hrs;
        }

        public String getTotal_hours() {
            return total_hours;
        }

        public void setTotal_hours(String total_hours) {
            this.total_hours = total_hours;
        }

        public String getAdditional_hours() {
            return additional_hours;
        }

        public void setAdditional_hours(String additional_hours) {
            this.additional_hours = additional_hours;
        }

        public String getAddition_amount() {
            return addition_amount;
        }

        public void setAddition_amount(String addition_amount) {
            this.addition_amount = addition_amount;
        }

        public String getAddition_payment_method() {
            return addition_payment_method;
        }

        public void setAddition_payment_method(String addition_payment_method) {
            this.addition_payment_method = addition_payment_method;
        }

        public String getAddition_payment_status() {
            return addition_payment_status;
        }

        public void setAddition_payment_status(String addition_payment_status) {
            this.addition_payment_status = addition_payment_status;
        }

        public String getTotal_paid_amount() {
            return total_paid_amount;
        }

        public void setTotal_paid_amount(String total_paid_amount) {
            this.total_paid_amount = total_paid_amount;
        }

        public String getWork_status() {
            return work_status;
        }

        public void setWork_status(String work_status) {
            this.work_status = work_status;
        }
        public String getStart_otp() {
            return start_otp;
        }

        public void setStart_otp(String start_otp) {
            this.start_otp = start_otp;
        }

        public String getEnd_otp() {
            return end_otp;
        }

        public void setEnd_otp(String end_otp) {
            this.end_otp = end_otp;
        }

        public String getAddress_text() {
            return address_text;
        }

        public void setAddress_text(String address_text) {
            this.address_text = address_text;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPin_code() {
            return pin_code;
        }

        public void setPin_code(String pin_code) {
            this.pin_code = pin_code;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
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

        public List<?> getSp_attched() {
            return sp_attched;
        }

        public void setSp_attched(List<?> sp_attched) {
            this.sp_attched = sp_attched;
        }

        public List<SpBusinessInfoBean> getSp_business_info() {
            return sp_business_info;
        }

        public void setSp_business_info(List<SpBusinessInfoBean> sp_business_info) {
            this.sp_business_info = sp_business_info;
        }

        public static class SpIdBean {
            private String _id;
            private String first_name;
            private String last_name;
            private String user_email;
            private String user_phone;
            private String date_of_reg;
            private int user_type;
            private String ref_code;
            private String my_ref_code;
            private String user_status;
            private int otp;
            private String profile_img;
            private boolean user_email_verification;
            private String fb_token;
            private String device_id;
            private String device_type;
            private String mobile_type;
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

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getUser_email() {
                return user_email;
            }

            public void setUser_email(String user_email) {
                this.user_email = user_email;
            }

            public String getUser_phone() {
                return user_phone;
            }

            public void setUser_phone(String user_phone) {
                this.user_phone = user_phone;
            }

            public String getDate_of_reg() {
                return date_of_reg;
            }

            public void setDate_of_reg(String date_of_reg) {
                this.date_of_reg = date_of_reg;
            }

            public int getUser_type() {
                return user_type;
            }

            public void setUser_type(int user_type) {
                this.user_type = user_type;
            }

            public String getRef_code() {
                return ref_code;
            }

            public void setRef_code(String ref_code) {
                this.ref_code = ref_code;
            }

            public String getMy_ref_code() {
                return my_ref_code;
            }

            public void setMy_ref_code(String my_ref_code) {
                this.my_ref_code = my_ref_code;
            }

            public String getUser_status() {
                return user_status;
            }

            public void setUser_status(String user_status) {
                this.user_status = user_status;
            }

            public int getOtp() {
                return otp;
            }

            public void setOtp(int otp) {
                this.otp = otp;
            }

            public String getProfile_img() {
                return profile_img;
            }

            public void setProfile_img(String profile_img) {
                this.profile_img = profile_img;
            }

            public boolean isUser_email_verification() {
                return user_email_verification;
            }

            public void setUser_email_verification(boolean user_email_verification) {
                this.user_email_verification = user_email_verification;
            }

            public String getFb_token() {
                return fb_token;
            }

            public void setFb_token(String fb_token) {
                this.fb_token = fb_token;
            }

            public String getDevice_id() {
                return device_id;
            }

            public void setDevice_id(String device_id) {
                this.device_id = device_id;
            }

            public String getDevice_type() {
                return device_type;
            }

            public void setDevice_type(String device_type) {
                this.device_type = device_type;
            }

            public String getMobile_type() {
                return mobile_type;
            }

            public void setMobile_type(String mobile_type) {
                this.mobile_type = mobile_type;
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

        public static class UserIdBean {
            private String _id;
            private String first_name;
            private String last_name;
            private String user_email;
            private String user_phone;
            private String date_of_reg;
            private int user_type;
            private String ref_code;
            private String my_ref_code;
            private String user_status;
            private int otp;
            private String profile_img;
            private boolean user_email_verification;
            private String fb_token;
            private String device_id;
            private String device_type;
            private String mobile_type;
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

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getUser_email() {
                return user_email;
            }

            public void setUser_email(String user_email) {
                this.user_email = user_email;
            }

            public String getUser_phone() {
                return user_phone;
            }

            public void setUser_phone(String user_phone) {
                this.user_phone = user_phone;
            }

            public String getDate_of_reg() {
                return date_of_reg;
            }

            public void setDate_of_reg(String date_of_reg) {
                this.date_of_reg = date_of_reg;
            }

            public int getUser_type() {
                return user_type;
            }

            public void setUser_type(int user_type) {
                this.user_type = user_type;
            }

            public String getRef_code() {
                return ref_code;
            }

            public void setRef_code(String ref_code) {
                this.ref_code = ref_code;
            }

            public String getMy_ref_code() {
                return my_ref_code;
            }

            public void setMy_ref_code(String my_ref_code) {
                this.my_ref_code = my_ref_code;
            }

            public String getUser_status() {
                return user_status;
            }

            public void setUser_status(String user_status) {
                this.user_status = user_status;
            }

            public int getOtp() {
                return otp;
            }

            public void setOtp(int otp) {
                this.otp = otp;
            }

            public String getProfile_img() {
                return profile_img;
            }

            public void setProfile_img(String profile_img) {
                this.profile_img = profile_img;
            }

            public boolean isUser_email_verification() {
                return user_email_verification;
            }

            public void setUser_email_verification(boolean user_email_verification) {
                this.user_email_verification = user_email_verification;
            }

            public String getFb_token() {
                return fb_token;
            }

            public void setFb_token(String fb_token) {
                this.fb_token = fb_token;
            }

            public String getDevice_id() {
                return device_id;
            }

            public void setDevice_id(String device_id) {
                this.device_id = device_id;
            }

            public String getDevice_type() {
                return device_type;
            }

            public void setDevice_type(String device_type) {
                this.device_type = device_type;
            }

            public String getMobile_type() {
                return mobile_type;
            }

            public void setMobile_type(String mobile_type) {
                this.mobile_type = mobile_type;
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

        public static class SpBusinessInfoBean {
            private String _id;
            private String user_id;
            private String bus_user_name;
            private String bus_user_email;
            private String bussiness_name;
            private String bus_user_phone;
            private String bus_profile;
            private String bus_proof;
            private String date_and_time;
            private String mobile_type;
            private boolean profile_status;
            private String profile_verification_status;
            private String thumbnail_image;
            private String sp_loc;
            private double sp_lat;
            private double sp_long;
            private String city_name;
            private boolean delete_status;
            private boolean calender_status;
            private int comments;
            private int rating;
            private String sp_info;
            private String updatedAt;
            private String createdAt;
            private int __v;
            /**
             * service_name : Bartender
             * subsericelist : [{"_id":"616ff0dcea122f58f9d09803","isservice":false,"service_id":"616ff0a4ea122f58f9d09802","title":"Bartender Cleaning"},{"_id":"616ff0f3ea122f58f9d09804","isservice":true,"service_id":"616ff0a4ea122f58f9d09802","title":"Bartender Services"}]
             */

            private List<BusServiceListBean> bus_service_list;
            /**
             * bus_spec_list : Kennel Cut
             */

            private List<BusSpecListBean> bus_spec_list;
            /**
             * bus_service_gall : http://54.193.18.54:3000/api/uploads/1634205785736.jpg
             */

            private List<BusServiceGallBean> bus_service_gall;
            /**
             * bus_certif : http://54.193.18.54:3000/api/uploads/1634205831306.jpg
             */

            private List<BusCertifBean> bus_certif;

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

            public String getBus_user_name() {
                return bus_user_name;
            }

            public void setBus_user_name(String bus_user_name) {
                this.bus_user_name = bus_user_name;
            }

            public String getBus_user_email() {
                return bus_user_email;
            }

            public void setBus_user_email(String bus_user_email) {
                this.bus_user_email = bus_user_email;
            }

            public String getBussiness_name() {
                return bussiness_name;
            }

            public void setBussiness_name(String bussiness_name) {
                this.bussiness_name = bussiness_name;
            }

            public String getBus_user_phone() {
                return bus_user_phone;
            }

            public void setBus_user_phone(String bus_user_phone) {
                this.bus_user_phone = bus_user_phone;
            }

            public String getBus_profile() {
                return bus_profile;
            }

            public void setBus_profile(String bus_profile) {
                this.bus_profile = bus_profile;
            }

            public String getBus_proof() {
                return bus_proof;
            }

            public void setBus_proof(String bus_proof) {
                this.bus_proof = bus_proof;
            }

            public String getDate_and_time() {
                return date_and_time;
            }

            public void setDate_and_time(String date_and_time) {
                this.date_and_time = date_and_time;
            }

            public String getMobile_type() {
                return mobile_type;
            }

            public void setMobile_type(String mobile_type) {
                this.mobile_type = mobile_type;
            }

            public boolean isProfile_status() {
                return profile_status;
            }

            public void setProfile_status(boolean profile_status) {
                this.profile_status = profile_status;
            }

            public String getProfile_verification_status() {
                return profile_verification_status;
            }

            public void setProfile_verification_status(String profile_verification_status) {
                this.profile_verification_status = profile_verification_status;
            }

            public String getThumbnail_image() {
                return thumbnail_image;
            }

            public void setThumbnail_image(String thumbnail_image) {
                this.thumbnail_image = thumbnail_image;
            }

            public String getSp_loc() {
                return sp_loc;
            }

            public void setSp_loc(String sp_loc) {
                this.sp_loc = sp_loc;
            }

            public double getSp_lat() {
                return sp_lat;
            }

            public void setSp_lat(double sp_lat) {
                this.sp_lat = sp_lat;
            }

            public double getSp_long() {
                return sp_long;
            }

            public void setSp_long(double sp_long) {
                this.sp_long = sp_long;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            public boolean isDelete_status() {
                return delete_status;
            }

            public void setDelete_status(boolean delete_status) {
                this.delete_status = delete_status;
            }

            public boolean isCalender_status() {
                return calender_status;
            }

            public void setCalender_status(boolean calender_status) {
                this.calender_status = calender_status;
            }

            public int getComments() {
                return comments;
            }

            public void setComments(int comments) {
                this.comments = comments;
            }

            public int getRating() {
                return rating;
            }

            public void setRating(int rating) {
                this.rating = rating;
            }

            public String getSp_info() {
                return sp_info;
            }

            public void setSp_info(String sp_info) {
                this.sp_info = sp_info;
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

            public List<BusServiceListBean> getBus_service_list() {
                return bus_service_list;
            }

            public void setBus_service_list(List<BusServiceListBean> bus_service_list) {
                this.bus_service_list = bus_service_list;
            }

            public List<BusSpecListBean> getBus_spec_list() {
                return bus_spec_list;
            }

            public void setBus_spec_list(List<BusSpecListBean> bus_spec_list) {
                this.bus_spec_list = bus_spec_list;
            }

            public List<BusServiceGallBean> getBus_service_gall() {
                return bus_service_gall;
            }

            public void setBus_service_gall(List<BusServiceGallBean> bus_service_gall) {
                this.bus_service_gall = bus_service_gall;
            }

            public List<BusCertifBean> getBus_certif() {
                return bus_certif;
            }

            public void setBus_certif(List<BusCertifBean> bus_certif) {
                this.bus_certif = bus_certif;
            }

            public static class BusServiceListBean {
                private String service_name;
                /**
                 * _id : 616ff0dcea122f58f9d09803
                 * isservice : false
                 * service_id : 616ff0a4ea122f58f9d09802
                 * title : Bartender Cleaning
                 */

                private List<SubsericelistBean> subsericelist;

                public String getService_name() {
                    return service_name;
                }

                public void setService_name(String service_name) {
                    this.service_name = service_name;
                }

                public List<SubsericelistBean> getSubsericelist() {
                    return subsericelist;
                }

                public void setSubsericelist(List<SubsericelistBean> subsericelist) {
                    this.subsericelist = subsericelist;
                }

                public static class SubsericelistBean {
                    private String _id;
                    private boolean isservice;
                    private String service_id;
                    private String title;

                    public String get_id() {
                        return _id;
                    }

                    public void set_id(String _id) {
                        this._id = _id;
                    }

                    public boolean isIsservice() {
                        return isservice;
                    }

                    public void setIsservice(boolean isservice) {
                        this.isservice = isservice;
                    }

                    public String getService_id() {
                        return service_id;
                    }

                    public void setService_id(String service_id) {
                        this.service_id = service_id;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }
                }
            }

            public static class BusSpecListBean {
                private String bus_spec_list;

                public String getBus_spec_list() {
                    return bus_spec_list;
                }

                public void setBus_spec_list(String bus_spec_list) {
                    this.bus_spec_list = bus_spec_list;
                }
            }

            public static class BusServiceGallBean {
                private String bus_service_gall;

                public String getBus_service_gall() {
                    return bus_service_gall;
                }

                public void setBus_service_gall(String bus_service_gall) {
                    this.bus_service_gall = bus_service_gall;
                }
            }

            public static class BusCertifBean {
                private String bus_certif;

                public String getBus_certif() {
                    return bus_certif;
                }

                public void setBus_certif(String bus_certif) {
                    this.bus_certif = bus_certif;
                }
            }
        }
    }
}
