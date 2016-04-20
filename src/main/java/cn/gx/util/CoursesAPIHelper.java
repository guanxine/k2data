package cn.gx.util;

import cn.gx.entity.API;
import cn.gx.entity.CourseView;
import cn.gx.entity.Time;
import cn.gx.exception.CustomDateRuntimeException;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  与课程相关的课程帮助类。
 */
public class CoursesAPIHelper extends APIHelper{



    public static final String GET_COURSE = "/courses/{id}";
    public static final String GET_ALL_COURSES = "/courses";
    public static final String CREATE_COURSE = "/courses";
    public static final String DELETE_COURSE = "/courses/{id}";
    public static String EXAMPLE="/courses";

    public CoursesAPIHelper(String url) {
        super(url);
    }


    public List getAllRequest(){


        API createCourse=new API("创建课程", RequestMethod.POST,url+CREATE_COURSE,url+CREATE_COURSE);
        CourseView courseView = getExampleCourseView();
        createCourse.setParam(courseView);
        API getAllCourse=new API("查看课程列表",RequestMethod.GET,url+GET_ALL_COURSES,url+GET_ALL_COURSES);
        API getCourse=new API("查看课程",RequestMethod.GET,url+GET_COURSE,url+EXAMPLE+"/1");
        API deleteCourse=new API("删除课程",RequestMethod.DELETE,url+DELETE_COURSE,url+EXAMPLE+"/1");

        List courses=new ArrayList(4);
        courses.add(createCourse);
        courses.add(getAllCourse);
        courses.add(getCourse);
        courses.add(deleteCourse);
        return courses;
    }

    private CourseView getExampleCourseView(){
        CourseView courseView=new CourseView();
        courseView.setName("REST API Best Practices");
        Time time=new Time();
        courseView.setTime(time);
        courseView.setEstimatedTime(24);
        courseView.setFacilitator("Awesome Developer");


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String start = "2016-01-04";
        String end ="2016-01-06";
        try{
            Date startDate = df.parse(start);
            Date endDate=df.parse(end);
            time.setStart(startDate);
            time.setEnd(endDate);
        }catch (Exception e){
            throw new  CustomDateRuntimeException("例子日期格式错误");
        }

        return courseView;
    }
}
