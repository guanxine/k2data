package cn.gx.bean;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 课程类，对应数据库表 courses
 */
public class Course implements Serializable{


    private Integer id;  // 课程编号

    @NotNull
    private String name; // 课程名
    @NotNull
    @DateTimeFormat(pattern ="yyyy-DD-mm")
    private Date start;  // 课程开始日期
    @NotNull
    @DateTimeFormat(pattern ="yyyy-DD-mm")
    private Date end;    // 课程结束日期
    @NotNull
    private Integer estimatedTime; // 课时
    @NotNull
    private String facilitator; // 讲师


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
