package cn.gx.entity;

import cn.gx.util.DatePatternValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

/**
 * Created by guanxine on 16-4-18.
 */

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=DateRangeValidator.class)
public @interface DateRange {

    long min() default 0;

    long max() default 0;

    String message() default "日期格式应为 yyyy-MM-dd，如 2016-04-13";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
