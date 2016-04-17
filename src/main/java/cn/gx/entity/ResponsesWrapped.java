package cn.gx.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by guanxine on 16-4-16.
 *
 * Successful
 * {"code":200,"status":"success","data":
 * {"lacksTOS":false,"invalidCredentials":false,"authToken":"4ee683baa2a3332c3c86026d"}}
 *
 * Error
 * {"code":401,"status":"error","message":"token is invalid","data":"UnauthorizedException"}
 *
 */
public class ResponsesWrapped {

    public enum Status{
        success,fail,error
    }
    /**
     * 响应状态码
     */
    private int code;
    /**
     * 响应的值
     * success:
     * fail:500~599
     * error:400~499
     */
    private Status status;
    /**
     *  only used for “fail” and “error” statuses to contain the error message
     */
    private String message;
    /**
     *
     *
     */
    private Map<String,Object> data= new HashMap<>();


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(String key,Object value) {
        data.put(key,value);
    }
}
