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

        //SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Time time=new Time();
//        time.setStart(sdf.format(course.getStart()));
//        time.setEnd(sdf.format(course.getEnd()));

        time.setStart(course.getStart());
        time.setEnd(course.getEnd());

        CourseView courseView=new CourseView();
        courseView.setEstimatedTime(course.getEstimatedTime());
        courseView.setFacilitator(course.getFacilitator());
        courseView.setName(course.getName());
        courseView.setTime(time);
        return courseView;
    }

    public Course saveCourse(CourseView courseView) throws Exception{
        Course course=new Course();
        course.setName(courseView.getName());
        Time time = courseView.getTime();
        Date start=time.getStart();
        Date end=time.getEnd();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        Date start = sdf.parse(time.getStart());
//        Date end = sdf.parse(time.getEnd());
//        try {
//            start = sdf.parse(time.getStart());
//        } catch (ParseException e) {
//            throw new InvalidRequestException()
//        }
//        Date end = null;
//        try {
//            end = sdf.parse(time.getEnd());
//        } catch (ParseException e) {
//            throw new
//        }
        if (start.getTime()>end.getTime()){
            throw new RuntimeException("开始日期应小于结束日期");
        }
        course.setStart(start);
        course.setEnd(end);
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
