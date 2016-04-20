package cn.gx.service;

import cn.gx.bean.Course;
import cn.gx.entity.CourseView;

import java.util.List;


/**
 * 课程业务接口，定义与课程相关的逻辑
 */
public interface CoursesService {

    /**
     * 查看课程列表
     *
     * @param href 访问课程列表的请求路径，为设置每个课程的请求。
     * @return
     */
    List<CourseView> findAllCourses(String href);

    /**
     * 按 id 删除某一特定课程信息
     *
     * @param id 要删除的课程 id
     */
    void deleteCourseById(Integer id);

    /**
     * 按 id 查询某一特定课程信息
     * @param id 要查询课程 id
     * @param url 该课程的请求路径
     * @return
     */
    CourseView findById(Integer id,String url);

    /**
     * 创建课程
     * @param courseView
     * @return
     */
    Course saveCourse(CourseView courseView);


}
