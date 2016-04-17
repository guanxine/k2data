package cn.gx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by guanxine on 16-4-16.
 */
public interface Validable {

    boolean isVaild();

    default boolean isValidDate(String str) {
        boolean convertSuccess=true;// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess=false;
        }
        return convertSuccess;
    }
}
