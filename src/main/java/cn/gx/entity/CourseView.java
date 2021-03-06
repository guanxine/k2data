package cn.gx.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 *
 * 返回给客户端的课程信息
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
public class CourseView {

    private String name;
    private Time time;
    private Integer estimatedTime;
    private String facilitator;
    private Link link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

//    @Override
//    public boolean supports(Class<?> clazz) {
//        return CourseView.class.equals(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//
//        // TODO Auto-generated method stub
//        ValidationUtils.rejectIfEmpty(errors, "username", null, "Username is empty.");
//        CourseView user = (CourseView) target;
//        if (null == user.getPassword() || "".equals(user.getPassword()))
//            errors.rejectValue("password", null, "Password is empty.");
//    }
}
