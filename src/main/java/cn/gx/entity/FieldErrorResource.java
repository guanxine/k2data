package cn.gx.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by guanxine on 16-4-17.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
public class FieldErrorResource {

    private String resource;
    private String field;
    private String code;
    private String message;

    public String getResource() { return resource; }

    public void setResource(String resource) { this.resource = resource; }

    public String getField() { return field; }

    public void setField(String field) { this.field = field; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
