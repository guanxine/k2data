package cn.gx.entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by guanxine on 16-4-18.
 */
public class DateRangeValidator implements ConstraintValidator<DateRange, String> {


   public void initialize(DateRange constraint) {

   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      return false;
   }
}
