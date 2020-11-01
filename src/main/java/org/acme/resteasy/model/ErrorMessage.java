package org.acme.resteasy.model;

public class ErrorMessage {

    private String errorMsg;
    private int errorStatus;


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(int errorStatus) {
        this.errorStatus = errorStatus;
    }


    public ErrorMessage() {
    }

    public ErrorMessage(String errorMsg, int errorStatus) {
        this.errorMsg = errorMsg;
        this.errorStatus = errorStatus;
    }
}
