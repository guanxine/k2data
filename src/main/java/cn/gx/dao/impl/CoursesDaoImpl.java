package cn.gx.dao.impl;

import cn.gx.bean.Course;
import cn.gx.dao.CoursesDao;
import cn.gx.exception.NotFoundRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * 课程 Dao 类，实现与数据库相关逻辑
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
            throw new NotFoundRuntimeException("课程列表为空");
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
            throw new NotFoundRuntimeException(id);
        }else{
            return courses.get(0);
        }

    }

    public Course save(final Course course) {
        final String sql=" INSERT INTO courses (name,start,end,estimatedTime,facilitator) VALUES (?,?,?,?,?)";

        KeyHolder keyHolder=new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,course.getName());
                ps.setObject(2,course.getStart());
                ps.setObject(3,course.getEnd());
                ps.setInt(4,course.getEstimatedTime());
                ps.setString(5,course.getFacilitator());
                return ps;
            }
        }, keyHolder);
        course.setId(keyHolder.getKey().intValue());
        return course;
    }


    private RowMapper<Course> courseRowMapper=new RowMapper<Course>() {
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course c=new Course();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setStart(rs.getDate("start"));
            c.setEnd(rs.getDate("end"));
            c.setFacilitator(rs.getString("facilitator"));
            c.setEstimatedTime(rs.getInt("estimatedTime"));
            return c;
        }
    };


}
