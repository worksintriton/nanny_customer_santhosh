package com.triton.nanny.responsepojo;

public class TriggerSPSearchResponse {


    /**
     * Status : Failed
     * Message : No Service Provider Available
     * Data : {}
     * Code : 404
     */

    private String Status;
    private String Message;
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

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }
}
