package cn.gx.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by guanxine on 16-4-19.
 */

@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
public class API {

    public String name;
    public String url;
    private RequestMethod method;
    private String example;
    private Object param;


    public API() {
        super();
    }

    public API(String name, RequestMethod method,String url, String example) {
        this.name = name;
        this.url = url;
        this.method = method;
        this.example = example;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }


    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
