package cn.gx.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *  API 类，显示 api 帮助信息的基础类
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
public class API {

    public String name; // 操作名称
    public String url;  // 请求路径
    private RequestMethod method; //请求方式
    private String example; //例子
    private Object param; // 如果需要请求参数，指明请求参数


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
