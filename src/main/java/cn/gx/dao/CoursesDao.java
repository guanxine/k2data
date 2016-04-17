package cn.gx.dao;

import cn.gx.bean.Course;

import java.util.List;

/**
 * Created by always on 16/4/14.
 */
public interface CoursesDao {

    /**
     * 查看课程列表
     *
     * @return 返回所有课程集合,如果不存在,返回空集合
     */
    List<Course> selectAll();

    /**
     * 按 id 删除某一特定课程信息
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 按 id 查询某一特定课程信息
     *
     * @param id
     * @return 返回指定课程,如果不存在,返回 null
     *
     */
    Course selectById(Integer id);

    /**
     * 创建课程
     *
     */
    Course save(Course course);
}
