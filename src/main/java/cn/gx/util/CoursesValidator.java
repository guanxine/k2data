package cn.gx.util;

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


    @Override
    public boolean supports(Class<?> clazz) {

        return CourseView.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
       // ValidationUtils.re

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","not null","课程名称不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"time","not null","课程时间不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"estimatedTime","not null","课程课时不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"facilitator","not null","老师不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"time.start","not null","课程开始时间不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"time.end","not null","课程结束时间不能为空");

        CourseView cv=(CourseView)target;
        try {
            errors.pushNestedPath("time");
            ValidationUtils.invokeValidator(this.timeValidator, cv.getTime(), errors);
        } finally {
            errors.popNestedPath();
        }
    }
}
