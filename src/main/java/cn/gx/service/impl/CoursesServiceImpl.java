package cn.gx.service.impl;

import cn.gx.bean.Course;
import cn.gx.dao.CoursesDao;
import cn.gx.entity.CourseView;
import cn.gx.entity.Link;
import cn.gx.entity.Time;
import cn.gx.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程业务类，实现与课程相关的逻辑
 */
@Service("coursesService")
public class CoursesServiceImpl implements CoursesService{

    @Autowired
    public CoursesDao coursesDao;


    public  List<CourseView> findAllCourses(String url) {
        List<Course> courses = coursesDao.selectAll();
        
        int size=courses.size();
        List<CourseView> courseViewsList=new ArrayList<CourseView>(size);
        for (int i = 0; i <size; i++) {
            Course course=courses.get(i);
            CourseView courseView = copyProps(course);
            courseView.setLink(new Link(url+course.getId()));// 设置访问到该课程的请求，给客户端友好提示。
            courseViewsList.add(courseView);
        }

        return courseViewsList;
    }

    public void deleteCourseById(Integer id) {
        Course course = coursesDao.selectById(id);
        coursesDao.deleteById(course.getId());
    }

    public CourseView findById(Integer id,String url) {
        Course course = coursesDao.selectById(id);
        CourseView courseView = copyProps(course);
        courseView.setLink(new Link(url));// 设置访问到该课程的请求，给客户端友好提示。
        return courseView;
    }



    public Course saveCourse(CourseView courseView){

        Course course = copyProps(courseView);
        return coursesDao.save(course);
    }

    /**
     * 客户端 CourseView 实体类到数据库实体 Course 类的转换。
     * @param courseView
     * @return Course
     */
    private Course copyProps(CourseView courseView) {
        Time time = courseView.getTime();
        Course course=new Course();
        course.setName(courseView.getName());
        course.setStart(time.getStart());
        course.setEnd(time.getEnd());
        course.setFacilitator(courseView.getFacilitator());
        course.setEstimatedTime(courseView.getEstimatedTime());
        return course;
    }

    /**
     * 数据库实体 Course 类到客户端CourseView 实体类的转换。
     * @param course
     * @return CourseView
     */
    private CourseView copyProps(Course course) {

        Time time=new Time();
        time.setStart(course.getStart());
        time.setEnd(course.getEnd());

        CourseView courseView=new CourseView();
        courseView.setEstimatedTime(course.getEstimatedTime());
        courseView.setFacilitator(course.getFacilitator());
        courseView.setName(course.getName());
        courseView.setTime(time);
        return courseView;
    }
}
