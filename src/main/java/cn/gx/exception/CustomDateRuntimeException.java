package cn.gx.exception;

/**
 * 自定义日期转换异常类
 */
public class CustomDateRuntimeException extends RuntimeException {

    public CustomDateRuntimeException(String field) {
        super(String.format("属性 %s 日期格式错误，应为：yyyy-MM-dd，例如：2016-04-13。",field));
    }
}
