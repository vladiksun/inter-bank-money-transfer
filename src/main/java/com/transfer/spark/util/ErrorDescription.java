package com.transfer.spark.util;

public class ErrorDescription {

    private int errorCode;
    private String userMessage;
    private String developerMessage;

    public ErrorDescription(int errorCode, String userMessage) {
        this.errorCode = errorCode;
        this.userMessage = userMessage;
    }

    public ErrorDescription(int errorCode, String userMessage, String developerMessage) {
        this.errorCode = errorCode;
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
