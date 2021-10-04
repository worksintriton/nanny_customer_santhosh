package com.triton.nanny.requestpojo;

public class SPDetailsRequest {


    /**
     * cata_id : 612746acdc2406057b5aeb13
     * sp_id : 6127891a4f7e5d1561c63783
     * user_id : 611be7560f35883dd3b7181c
     * flag : true
     */

    private String cata_id;
    private String sp_id;
    private String user_id;
    private boolean flag;

    public String getCata_id() {
        return cata_id;
    }

    public void setCata_id(String cata_id) {
        this.cata_id = cata_id;
    }

    public String getSp_id() {
        return sp_id;
    }

    public void setSp_id(String sp_id) {
        this.sp_id = sp_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
