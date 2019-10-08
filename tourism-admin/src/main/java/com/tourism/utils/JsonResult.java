package com.tourism.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 通过此对象对控制层数据进行封装
 * 1)正常数据
 * 2)异常数据
 */
@Component
@Data
public class JsonResult implements Serializable {
    private static final int SUCCESS = 1;
    private static final int ERROR = 0;
    /**
     * 状态码
     */
    private int state = SUCCESS;
    /**
     * 状态信息
     */
    private String message;
    /**
     * 具体数据
     */
    private Object data;

    public JsonResult() {
        message = "Action OK";
    }

    public JsonResult(Object data) {
        this.data = data;
    }

    public JsonResult(String message) {
        this.message = message;
    }

    public JsonResult(Throwable exp) {
        this.state = ERROR;
        this.message = exp.getMessage();
    }
}
