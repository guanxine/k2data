package cn.gx.service.impl;

import cn.gx.bean.Course;
import cn.gx.dao.CoursesDao;
import cn.gx.entity.CourseView;
import cn.gx.entity.ErrorResource;
import cn.gx.entity.Link;
import cn.gx.entity.Time;
import cn.gx.exception.ServiceException;
import cn.gx.service.CoursesService;
import cn.gx.util.DataUtil;
import com.sun.corba.se.impl.naming.namingutil.CorbalocURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by always on 16/4/14.
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
            Link self=new Link();
            self.setHref(url+"/"+course.getId());
            courseView.setLink(self);
            courseViewsList.add(courseView);
        }

        return courseViewsList;
    }

    public void deleteCourseById(Integer id) {
        coursesDao.deleteById(id);
    }

    public CourseView findById(Integer id) {


        Course course = coursesDao.selectById(id);
        if (course!=null){
            return copyProps(course);
        }else{
            return null;
        }
    }


    private CourseView copyProps(Course course) {

        Time time=new Time();
//        time.setStart(sdf.format(course.getStart()));
//        time.setEnd(sdf.format(course.getEnd()));


        String format="yyyy-MM-dd";
        time.setStart(course.getStart());
        time.setEnd(course.getEnd());

        CourseView courseView=new CourseView();
        courseView.setEstimatedTime(course.getEstimatedTime());
        courseView.setFacilitator(course.getFacilitator());
        courseView.setName(course.getName());
        courseView.setTime(time);
        return courseView;
    }

    public Course saveCourse(CourseView courseView) throws RuntimeException{

        Time time = courseView.getTime();
//        if (!time.isVaild()){
//            ErrorResource er=new ErrorResource();
//            return ServiceException(,)
//        }
        Course course=new Course();
        course.setName(courseView.getName());
        course.setStart(time.getStart());
        course.setEnd(time.getEnd());
        course.setFacilitator(courseView.getFacilitator());
        course.setEstimatedTime(courseView.getEstimatedTime());
        return coursesDao.save(course);
    }

    @Override
    public boolean isCourseExist(Integer id) {

        if (id!=null){
            Course dbc = coursesDao.selectById(id);
            return dbc==null?false:true;
        }else{
            return false;
        }
    }
}
