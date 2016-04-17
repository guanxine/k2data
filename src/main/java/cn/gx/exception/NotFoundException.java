package cn.gx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by guanxine on 16-4-17.
 */
//@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "没有找到")
public class NotFoundException extends RuntimeException{

    public NotFoundException(Integer id) {
        super(id+"没有找到");
    }
}
