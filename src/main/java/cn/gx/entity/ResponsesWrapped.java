package cn.gx.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

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
@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
public class ResponsesWrapped {

    /**
     * 响应的值 信息
     * success:
     * fail:500~599
     * error:400~499
     */
    private Status status;


    public enum Status{
        success,fail,error
    }
    /**
     * 响应状态码
     */
    private HttpStatus code;
    /**
     *  only used for “fail” and “error” statuses to contain the error message
     */
    private String message;

    private Link link;


    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
