package cn.gx.util;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 *  api 帮助类
 */
public abstract class APIHelper {

    protected String url;
    private APIHelper(){
        super();
    }
    public APIHelper(String url){
        this.url=url;
    }
    public abstract List getAllRequest();
}
