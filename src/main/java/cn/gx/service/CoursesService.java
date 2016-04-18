package cn.gx.service;

import cn.gx.bean.Course;
import cn.gx.entity.CourseView;

import java.util.List;


/**
 * Created by always on 16/4/13.
 */
public interface CoursesService {

    /**
     *  查看课程列表
     */
    public List<CourseView> findAllCourses(String href);

    /**
     * 按 id 删除某一特定课程信息
     *
     * @param id
     */
    public void deleteCourseById(Integer id);

    /**
     * 按 id 查询某一特定课程信息
     *
     * @param id
     * @return
     *
     */
    public CourseView findById(Integer id);

    /**
     * 创建课程
     *
     */

    public Course saveCourse(CourseView courseView)  throws RuntimeException;

    /**
     * 课程是否存在
     *
     * @param id
     * @return
     */
    boolean isCourseExist(Integer id);
}
