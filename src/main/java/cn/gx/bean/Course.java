package cn.gx.bean;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by always on 16/4/13.
 *
 * 持久化数据库的 课程实体类
 */
public class Course implements Serializable{


    private Integer id;

    @NotNull
    private String name;
    @NotNull
    @DateTimeFormat(pattern ="yyyy-DD-mm")
    private Date start;
    @NotNull
    @DateTimeFormat(pattern ="yyyy-DD-mm")
    private Date end;
    @NotNull
    private Integer estimatedTime;
    @NotNull
    private String facilitator;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getFacilitator() {
        return facilitator;
    }

    public void setFacilitator(String facilitator) {


        this.facilitator = facilitator;
    }
}
