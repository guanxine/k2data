package cn.gx.exception;

import cn.gx.entity.ErrorInfo;
import cn.gx.entity.ErrorResource;
import cn.gx.entity.FieldErrorResource;
import cn.gx.entity.ResponsesWrapped;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mysql.jdbc.MysqlDataTruncation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guanxine on 16-4-17.
 */
@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice{

    protected Logger logger;

    public GlobalExceptionHandlingControllerAdvice() {
        logger = LoggerFactory.getLogger(getClass());
    }

	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
	/* . . . . . . . . . . . . . EXCEPTION HANDLERS . . . . . . . . . . . . . . */
	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /**
     * Convert a predefined exception to an HTTP Status code
     */
//    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
//    // 409
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponsesWrapped conflict(Exception exception) {
//        // Nothing to do. Return value 'databaseError' used as logical view name
//        // of an error page, passed to view-resolver(s) in usual way.
//
//        ResponsesWrapped responsesWrapped=new ResponsesWrapped();
//        responsesWrapped.setCode(HttpStatus.CONFLICT);
//        responsesWrapped.setMessage(exception.getLocalizedMessage());
//        responsesWrapped.setStatus(ResponsesWrapped.Status.fail);
//
//        logger.error("Request raised " + exception.getClass().getSimpleName());
//        return responsesWrapped;
//    } @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
//    // 409
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponsesWrapped conflict(Exception exception) {
//        // Nothing to do. Return value 'databaseError' used as logical view name
//        // of an error page, passed to view-resolver(s) in usual way.
//
//        ResponsesWrapped responsesWrapped=new ResponsesWrapped();
//        responsesWrapped.setCode(HttpStatus.CONFLICT);
//        responsesWrapped.setMessage(exception.getLocalizedMessage());
//        responsesWrapped.setStatus(ResponsesWrapped.Status.fail);
//
//        logger.error("Request raised " + exception.getClass().getSimpleName());
//        return responsesWrapped;
//    }

    /**
     * Convert a predefined exception to an HTTP Status code and specify the
     * name of a specific view that will be used to display the error.
     *
     * @return Exception view.
     */
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler({ DataIntegrityViolationException.class,SQLException.class,DataAccessException.class})
    @ResponseBody
    public ResponsesWrapped databaseError(Exception exception) {
        // Nothing to do. Return value 'databaseError' used as logical view name
        // of an error page, passed to view-resolver(s) in usual way.

        ResponsesWrapped responsesWrapped=new ResponsesWrapped();
        responsesWrapped.setCode(HttpStatus.CONFLICT);
        responsesWrapped.setStatus(ResponsesWrapped.Status.error);
        Throwable cause = exception.getCause();
        if (cause instanceof SQLException){
            SQLException sqlEx= (SQLException) cause;
            responsesWrapped.setData("message",sqlEx.getMessage());
            responsesWrapped.setData("SQLState",sqlEx.getSQLState());
        }

        responsesWrapped.setData("cause", cause.getClass().getSimpleName());

        logger.error("Request raised " + exception.getClass().getSimpleName());
        return responsesWrapped;
    }


//    /**
//     * Demonstrates how to take total control - setup a model, add useful
//     * information and return the "support" view name. This method explicitly
//     * creates and returns
//     *
//     * @param req
//     *            Current HTTP request.
//     * @param exception
//     *            The exception thrown - always {@link SupportInfoException}.
//     * @return The model and view used by the DispatcherServlet to generate
//     *         output.
//     * @throws Exception
//     */
//    @ExceptionHandler(SupportInfoException.class)
//    public ModelAndView handleError(HttpServletRequest req, Exception exception)
//            throws Exception {
//
//        // Rethrow annotated exceptions or they will be processed here instead.
//        if (AnnotationUtils.findAnnotation(exception.getClass(),
//                ResponseStatus.class) != null)
//            throw exception;
//
//        logger.error("Request: " + req.getRequestURI() + " raised " + exception);
//
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", exception);
//        mav.addObject("url", req.getRequestURL());
//        mav.addObject("timestamp", new Date().toString());
//        mav.addObject("status", 500);
//
//        mav.setViewName("support");
//        return mav;
//    }

//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    @ResponseBody ErrorInfo handleHttpMessageNotReadableException(HttpServletRequest req, Exception ex) {
//        //logger.error(ex.getLocalizedMessage());
//        logger.error(ex.getClass().getTypeName());
//        return new ErrorInfo(req.getRequestURL().toString(), ex);
//    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    @ResponseBody ErrorInfo handleException(HttpServletRequest req, Exception ex) {
        //logger.error(ex.getLocalizedMessage());
        logger.error(ex.getClass().getName());
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody ErrorInfo handleNoHandlerFoundException(HttpServletRequest req, Exception ex) {
        //logger.error(ex.getLocalizedMessage());
        logger.error(ex.getClass().getTypeName());
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }




    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    ErrorResource handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        BindingResult bindingResult = ex.getBindingResult();
        String errorMesssage = "Invalid Request:\n";
        List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

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


    // json 格式错误
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    ErrorInfo handleHttpMessageNotReadableException(HttpServletRequest req, Exception ex) {

        logger.error(ex.getClass().getName());
        logger.error(ex.getLocalizedMessage());
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {
        logger.error(ex.getLocalizedMessage());
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(JsonMappingException.class)
    @ResponseBody
    ErrorInfo handleJsonMappingException(HttpServletRequest req, Exception ex) {
        logger.error(ex.getLocalizedMessage());
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }


}
