package cn.gx.entity;

import cn.gx.util.DatePattern;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by always on 16/4/14.
 */
//@FieldCompare(first = "start", second = "end", message = "start 应该小于 second")
public class Time {

//    @NotNull
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public Date start;
//    @NotNull
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public Date end;


    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isVaild(){

        return start!=null&&end!=null&&start.getTime()<end.getTime();
    }
}

