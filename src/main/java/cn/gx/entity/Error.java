package cn.gx.entity;

/**
 * Created by always on 16/4/13.
 *
 * 返回客户端错误信息
 */
public class Error {

    // 状态码
    private String code;
    // 简要信息
    private String message;
    // 详细描述
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
