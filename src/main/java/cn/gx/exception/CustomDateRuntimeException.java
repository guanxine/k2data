package cn.gx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by guanxine on 16-4-19.
 */
public class CustomDateRuntimeException extends RuntimeException {

    public CustomDateRuntimeException(String field) {
        super(String.format("属性 %s 日期格式错误，应为：yyyy-MM-dd，例如：2016-04-13。",field));
    }
}
