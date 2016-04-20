package cn.gx.exception;

import cn.gx.entity.ErrorResource;
import cn.gx.entity.FieldErrorResource;
import cn.gx.entity.Link;
import cn.gx.entity.ResponsesWrapped;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice{

    protected Logger logger;

    public GlobalExceptionHandlingControllerAdvice() {
        logger = LoggerFactory.getLogger(getClass());
    }


    /**
     * 处理数据库异常类
     *
     * @param exception
     * @return
     */
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler({ DataIntegrityViolationException.class,SQLException.class,DataAccessException.class})
    @ResponseBody
    public ResponsesWrapped handleDatabaseError(Exception exception) {

        ResponsesWrapped responsesWrapped=new ResponsesWrapped();
        responsesWrapped.setCode(HttpStatus.CONFLICT);
        responsesWrapped.setStatus(ResponsesWrapped.Status.error);

        Throwable cause = exception.getCause();
        if (cause instanceof SQLException){
            SQLException sqlEx= (SQLException) cause;
            responsesWrapped.setMessage(String.format("msg:%s,SQLState:%s,cause:%s",sqlEx.getMessage(),sqlEx.getSQLState(),cause.getClass().getSimpleName()));
        }else{
            responsesWrapped.setMessage(cause.getLocalizedMessage());
        }

        logger.error("Request raised " + exception.getClass().getSimpleName());
        return responsesWrapped;
    }


    /**
     * 处理请求参数字段异常
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    ErrorResource handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        BindingResult bindingResult = ex.getBindingResult();
        String errorMesssage = "Invalid Request:\n";
        List<FieldErrorResource> fieldErrorResources = new ArrayList<FieldErrorResource>();

        List<FieldError>  fieldErrors= bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            FieldErrorResource fieldErrorResource = new FieldErrorResource();
            fieldErrorResource.setResource(fieldError.getObjectName());
            fieldErrorResource.setField(fieldError.getField());
            fieldErrorResource.setCode(fieldError.getCode());
            fieldErrorResource.setMessage(fieldError.getDefaultMessage());
            fieldErrorResources.add(fieldErrorResource);
        }

        ErrorResource error = new ErrorResource("InvalidRequest", ex.getMessage());
        error.setFieldErrors(fieldErrorResources);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

       return error;
    }

    /**
     * 处理数据格式转换异常
     * @param req
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({HttpMessageNotReadableException.class,JsonMappingException.class})
    @ResponseBody
    ResponsesWrapped handleHttpMessageNotReadableException(HttpServletRequest req, Exception ex) {
        ResponsesWrapped responsesWrapped=new ResponsesWrapped();
        responsesWrapped.setCode(HttpStatus.UNPROCESSABLE_ENTITY);
        responsesWrapped.setStatus(ResponsesWrapped.Status.error);
        Throwable cause = ex.getCause();
        if (cause instanceof JsonMappingException){
            JsonMappingException jsonEx= (JsonMappingException) cause;
            responsesWrapped.setMessage(jsonEx.getLocalizedMessage());
        }else {
            responsesWrapped.setMessage(ex.getMessage());
        }

        responsesWrapped.setLink(new Link(req.getRequestURL().toString()));
        logger.error("Request raised " + ex.getClass().getSimpleName());

        return responsesWrapped;
    }

    /**
     * 处理 找不到 相关异常
     * @param req
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundRuntimeException.class,HttpRequestMethodNotSupportedException.class,NoHandlerFoundException.class})
    @ResponseBody
    ResponsesWrapped handleBadRequest(HttpServletRequest req, Exception ex) {

        ResponsesWrapped responsesWrapped=new ResponsesWrapped();
        responsesWrapped.setCode(HttpStatus.NOT_FOUND);
        responsesWrapped.setStatus(ResponsesWrapped.Status.error);
        responsesWrapped.setMessage(ex.getLocalizedMessage());
        responsesWrapped.setLink(new Link(req.getRequestURL().toString()));
        logger.error("Request raised " + ex.getClass().getSimpleName());
        return responsesWrapped;
    }


    /**
     *
     * 处理其他异常
     * @param req
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody ResponsesWrapped handleException(HttpServletRequest req, Exception ex) {
        ResponsesWrapped responsesWrapped=new ResponsesWrapped();
        responsesWrapped.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
        responsesWrapped.setStatus(ResponsesWrapped.Status.fail);
        responsesWrapped.setMessage(ex.getLocalizedMessage());
        responsesWrapped.setLink(new Link(req.getRequestURL().toString()));
        logger.error("Request raised " + ex.getClass().getSimpleName());

        return responsesWrapped;
    }


}
