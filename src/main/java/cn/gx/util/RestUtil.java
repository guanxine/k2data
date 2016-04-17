package cn.gx.util;

import org.springframework.http.HttpStatus;

/**
 * Created by guanxine on 16-4-17.
 */
public class RestUtil {

    public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series)
                || HttpStatus.Series.SERVER_ERROR.equals(series));
    }
}
