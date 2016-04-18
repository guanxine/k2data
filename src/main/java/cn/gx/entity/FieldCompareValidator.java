package cn.gx.entity;


import cn.gx.util.DataUtil;
import cn.gx.util.DatePattern;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * Created by guanxine on 16-4-18.
 */
public class FieldCompareValidator implements ConstraintValidator<FieldCompare, String> {

    @DatePattern
    private String firstFieldName;
    @DatePattern
    private String secondFieldName;
    private String format;

   public void initialize(FieldCompare constraint) {

       firstFieldName = constraint.first();
       secondFieldName = constraint.second();




       format = constraint.format();

   }

   public boolean isValid(String value, ConstraintValidatorContext context) {

       try
       {
           String start =BeanUtils.getProperty(value, firstFieldName);
           String end = BeanUtils.getProperty(value, secondFieldName);

           Date startDate= DataUtil.str2Date(start,format);
           Date endDate=DataUtil.str2Date(end,format);

           if (startDate.getTime()<endDate.getTime()){
               return false;
           }else{
               return true;
           }
       }
       catch (final Exception ignore)
       {
           // ignore
           return false;
       }
   }



}
