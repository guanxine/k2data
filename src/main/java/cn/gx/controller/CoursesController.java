package cn.gx.controller;

import cn.gx.bean.Course;
import cn.gx.entity.CourseView;
import cn.gx.entity.Link;
import cn.gx.entity.ResponsesWrapped;
import cn.gx.service.CoursesService;
import cn.gx.validation.CoursesValidator;
import cn.gx.validation.TimeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.gx.util.CoursesAPIHelper.*;

/**
 * 课程控制类
 */
@RestController
public class CoursesController {


    @Autowired
    CoursesService coursesService;


    /**
     * 设置校验器，对用户提交数据进行校验
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new CoursesValidator(new TimeValidator()));
    }


    /**
     * 查询课程列表
     * @param request
     * @return
     */
    @RequestMapping(value =GET_ALL_COURSES,method = RequestMethod.GET)
    public ResponseEntity<List<CourseView>> listAllCourses(HttpServletRequest request) {

        String href = request.getRequestURL().toString();
        // 得到所有课程列表
        List<CourseView>  courses = coursesService.findAllCourses(href);
        // 返回提示信息
        ResponsesWrapped responsesWrapped=new ResponsesWrapped();
        responsesWrapped.setCode(HttpStatus.OK);
        responsesWrapped.setStatus(ResponsesWrapped.Status.success);
        responsesWrapped.setMessage("查询课程列表成功");

        Map<String,Object> objectMap=new HashMap<String,Object>();
        objectMap.put("courses",courses);
        objectMap.put("info",responsesWrapped);

        return new ResponseEntity(objectMap, HttpStatus.OK);
    }

    /**
     * 查询指定 id 课程信息
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(
            value = GET_COURSE,
            method = RequestMethod.GET)
    public ResponseEntity<CourseView> getCourse(
            @PathVariable("id") Integer id,
            HttpServletRequest request) {

        String href = request.getRequestURL().toString();
        //得到查询用户
        CourseView course = coursesService.findById(id,href);
        // 返回提示信息
        ResponsesWrapped responsesWrapped=new ResponsesWrapped();
        responsesWrapped.setCode(HttpStatus.OK);
        responsesWrapped.setStatus(ResponsesWrapped.Status.success);
        responsesWrapped.setMessage("查询课程成功");

        Map<String,Object> objectMap=new HashMap<String,Object>();
        objectMap.put("course",course);
        objectMap.put("info",responsesWrapped);

        return new ResponseEntity(objectMap, HttpStatus.OK);
    }


    /**
     * 创建课程
     *
     * @param ucBuilder
     * @param courseView
     * @return
     */
    @RequestMapping(
            value = CREATE_COURSE,
            method = RequestMethod.POST,
            produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCourse(
            UriComponentsBuilder ucBuilder,
            @RequestBody @Valid  CourseView courseView) {


        Course course=coursesService.saveCourse(courseView);

        //设置响应头
        URI href = ucBuilder.path(CREATE_COURSE).buildAndExpand(course.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(href);

        //返回提示信息
        ResponsesWrapped responsesWrapped=new ResponsesWrapped();
        responsesWrapped.setCode(HttpStatus.OK);
        responsesWrapped.setStatus(ResponsesWrapped.Status.success);
        responsesWrapped.setMessage("创建课程成功");
        responsesWrapped.setLink(new Link(href.toString()));

        return new ResponseEntity(responsesWrapped,headers,HttpStatus.CREATED);
    }

    /**
     * 删除指定 id 课程
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(
            value =DELETE_COURSE,
            method = RequestMethod.DELETE)
    public ResponseEntity deleteCourse(
            @PathVariable("id") Integer id,
            HttpServletRequest request) {

        // 删除用户
        coursesService.deleteCourseById(id);
        // 返回提示信息
        ResponsesWrapped responsesWrapped=new ResponsesWrapped();
        responsesWrapped.setCode(HttpStatus.OK);
        responsesWrapped.setStatus(ResponsesWrapped.Status.success);
        responsesWrapped.setMessage("课程删除成功");
        String href=request.getRequestURL().toString();
        responsesWrapped.setLink(new Link(href));

        return new ResponseEntity(responsesWrapped,HttpStatus.OK);
    }



}
