package cn.gx.util;

import cn.gx.entity.CourseView;
import cn.gx.entity.Time;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by guanxine on 16-4-18.
 */
public class TimeValidator implements Validator {



    @Override
    public boolean supports(Class<?> clazz) {

        return Time.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
       // ValidationUtils.re


        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"start","not null","课程开始时间不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"end","not null","课程结束时间不能为空");

        Time time=(Time)target;

        if (!time.isVaild()){
            System.out.println(this.getClass().getSimpleName());
            errors.rejectValue("start","start less end");
            errors.rejectValue("end","end great start");
        }



    }
}
