package cn.gx.validation;

import cn.gx.entity.CourseView;
import cn.gx.entity.Time;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by guanxine on 16-4-18.
 */
public class CoursesValidator implements Validator {

    private final Validator timeValidator;

    public CoursesValidator(Validator timeValidator){
        if (timeValidator == null) {
            throw new IllegalArgumentException("The supplied [Validator] is " +
                    "required and must not be null.");
        }
        if (!timeValidator.supports(Time.class)) {
            throw new IllegalArgumentException("The supplied [Validator] must " +
                    "support the validation of [Address] instances." );
        }
        this.timeValidator = timeValidator;
    }


    public boolean supports(Class<?> clazz) {

        return CourseView.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
       // ValidationUtils.re

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","notnull","name 不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"time","not null","time 不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"estimatedTime","not null","estimatedTime 不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"facilitator","not null","facilitator 不能为空");

        CourseView cv=(CourseView)target;
        try {
            errors.pushNestedPath("time");
            ValidationUtils.invokeValidator(this.timeValidator, cv.getTime(), errors);
        } finally {
            errors.popNestedPath();
        }
    }
}
