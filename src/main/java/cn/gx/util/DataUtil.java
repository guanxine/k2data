package cn.gx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guanxine on 16-4-18.
 */
public class DataUtil {
    public static Date str2Date(String value, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat( format );
        dateFormat.setLenient( false );
        return dateFormat.parse(value);
    }

    public static String date2Str(Date date,String format){

        SimpleDateFormat dateFormat = new SimpleDateFormat( format );
        return dateFormat.format(date);
    }

}
