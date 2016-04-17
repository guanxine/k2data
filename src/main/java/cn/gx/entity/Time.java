package cn.gx.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by always on 16/4/14.
 */
public class Time {

    @NotNull
    @DateTimeFormat(pattern = "yyyy-DD-mm")
    public Date start;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-DD-mm")
    public Date end;


    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
}

