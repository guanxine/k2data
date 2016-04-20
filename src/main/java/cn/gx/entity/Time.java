package cn.gx.entity;

import cn.gx.util.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

/**
 * 课程时间类
 */
public class Time {
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date start; //开始时间

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date end;//结束时间


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

    /**
     * 判断开始时间与结束时间是否有效
     * @return true 有效，false 无效
     */
    @JsonIgnore
    public boolean isVaild(){

        return start!=null&&end!=null&&start.getTime()<=end.getTime();
    }


    @Override
    public String toString() {
        return "Time{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

