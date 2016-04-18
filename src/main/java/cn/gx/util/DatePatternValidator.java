package cn.gx.util;

import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by guanxine on 16-4-18.
 */
@Validated
public class DatePatternValidator implements ConstraintValidator<DatePattern, String> {

    private String format;
   public void initialize(DatePattern dp) {
       format =dp.value();
   }

   public boolean isValid(String date, ConstraintValidatorContext context) {

       if ( date == null ) {
           return true;
       }

       DateFormat dateFormat = new SimpleDateFormat( format );
       dateFormat.setLenient( false );
       try {
           dateFormat.parse(date);
           return true;
       }
       catch (ParseException e) {
           return false;
       }
   }
}
