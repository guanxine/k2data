package cn.gx.dao;

import cn.gx.bean.Course;

import java.util.List;

/**
 * 课程 Dao 接口，定义与数据库相关逻辑
 */
public interface CoursesDao {

    /**
     * 查看课程列表
     *
     * @return 返回所有课程集合,如果不存在,抛自定义 NotFoundRuntimeException 运行时异常
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
     * @return 返回指定课程,如果不存在,抛自定义 NotFoundRuntimeException 运行时异常
     *
     */
    Course selectById(Integer id);

    /**
     * 创建课程
     *
     */
    Course save(Course course);
}
