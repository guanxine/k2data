package cn.gx.util;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by guanxine on 16-4-19.
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
