package cn.gx.dao.impl;

import cn.gx.bean.Course;
import cn.gx.dao.CoursesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

/**
 * Created by always on 16/4/14.
 */
@Repository("coursesDao")
public class CoursesDaoImpl implements CoursesDao{


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }

    public List<Course> selectAll() {

        String sql="SELECT ID,NAME,START,END,ESTIMATEDTIME,FACILITATOR FROM courses";
        List<Course> courses = jdbcTemplate.query(sql, courseRowMapper);

        if (courses==null || courses.isEmpty()){
            return Collections.emptyList();
        }else{
            return courses;
        }
    }

    public void deleteById(Integer id) {

        String sql="DELETE FROM courses WHERE id=?";
        jdbcTemplate.update(sql,id);
    }

    public Course selectById(Integer id) {
        String sql="SELECT ID,NAME,START,END,ESTIMATEDTIME,FACILITATOR FROM courses WHERE ID=?";
        List<Course> courses = jdbcTemplate.query(sql, courseRowMapper, id);

        if (courses==null||courses.isEmpty()){
            return null;
        }else{
            return courses.get(0);
        }

    }

    public Course save(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql=" INSERT INTO courses (name,start,end,estimatedTime,facilitator) VALUES (?,?,?,?,?)";
//        jdbcTemplate.update(con -> {
//            PreparedStatement p = con.prepareStatement(sql, new String[]{"id"});
//            return null;
//        },keyHolder);

        jdbcTemplate.update(sql, course.getName(), course.getStart(), course.getEnd(), course.getEstimatedTime(), course.getFacilitator());

        return course;
    }


    private RowMapper<Course> courseRowMapper=(rs,rowNum)->{
        Course c=new Course();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setStart(rs.getDate("start"));
        c.setEnd(rs.getDate("end"));
        c.setFacilitator(rs.getString("facilitator"));
        c.setEstimatedTime(rs.getInt("estimatedTime"));
        return c;
    };
}
