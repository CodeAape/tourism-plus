package com.tourism.utils;

import java.io.Serializable;
import java.util.Map;

public class ResponseObject implements Serializable {
    public static final int CODE_1 = 1; // 登录过期失效
    private static final long serialVersionUID = 1L;
    private Boolean responseStatus;
    private String responseMessage;
    private int responseCode;//方法错误标识
    private Map<String, Object> responseData;

    public ResponseObject() {
        this.responseMessage = "";
    }

    public ResponseObject(Boolean responseStatus) {
        this.responseStatus = responseStatus;
        this.responseMessage = "";
    }

    public ResponseObject(Boolean responseStatus, String responseMessage) {
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
    }

    public Boolean getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Boolean responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Map<String, Object> getResponseData() {
        return responseData;
    }

    public void setResponseData(Map<String, Object> responseData) {
        this.responseData = responseData;
    }
}
