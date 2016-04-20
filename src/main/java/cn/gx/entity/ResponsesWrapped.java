package cn.gx.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

/**
 * 返回客户端信息实体类
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
public class ResponsesWrapped {

    private Status status;// 状态信息


    public enum Status{
        success,fail,error
    }

    private HttpStatus code;//响应状态码信息

    private String message;// 状态详细信息

    private Link link; // 资源链接


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
