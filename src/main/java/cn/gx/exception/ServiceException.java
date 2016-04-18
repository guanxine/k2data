package cn.gx.exception;

import org.springframework.validation.Errors;

/**
 * Created by guanxine on 16-4-17.
 */
public class ServiceException extends RuntimeException{

    private Errors errors;

    public ServiceException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() { return errors; }
}