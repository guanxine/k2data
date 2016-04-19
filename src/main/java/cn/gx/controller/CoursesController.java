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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.gx.util.CoursesAPIHelper.*;

/**
 * Created by guanxine on 16-4-17.
 */

@RestController
public class CoursesController {


    @Autowired
    CoursesService coursesService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new CoursesValidator(new TimeValidator()));
    }



    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value =GET_ALL_COURSES,method = RequestMethod.GET)
    public ResponseEntity<List<CourseView>> listAllCourses(HttpServletRequest request) {

        String href = request.getRequestURL().toString();
        List<CourseView>  courses = coursesService.findAllCourses(href);
        ResponsesWrapped responsesWrapped=new ResponsesWrapped();

        responsesWrapped.setCode(HttpStatus.OK);
        responsesWrapped.setStatus(ResponsesWrapped.Status.success);
        responsesWrapped.setMessage("查询课程列表成功");

        Map<String,Object> objectMap=new HashMap<String,Object>();
        objectMap.put("courses",courses);
        objectMap.put("info",responsesWrapped);

        return new ResponseEntity(objectMap, HttpStatus.OK);
    }

    //-------------------Retrieve Single User--------------------------------------------------------

    @RequestMapping(
            value = GET_COURSE,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseView> getCourse(
            @PathVariable("id") Integer id,
            HttpServletRequest request) {

        String href = request.getRequestURL().toString();
        CourseView course = coursesService.findById(id,href);

        ResponsesWrapped responsesWrapped=new ResponsesWrapped();

        responsesWrapped.setCode(HttpStatus.OK);
        responsesWrapped.setStatus(ResponsesWrapped.Status.success);
        responsesWrapped.setMessage("查询课程成功");

        Map<String,Object> objectMap=new HashMap<String,Object>();
        objectMap.put("course",course);
        objectMap.put("info",responsesWrapped);
        return new ResponseEntity(objectMap, HttpStatus.OK);
    }

    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(
            value = CREATE_COURSE,
            method = RequestMethod.POST,
            produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCourse(
            UriComponentsBuilder ucBuilder,
            @RequestBody @Valid  CourseView courseView) {


        Course course=coursesService.saveCourse(courseView);
        URI href = ucBuilder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(href);
        ResponsesWrapped responsesWrapped=new ResponsesWrapped();

        responsesWrapped.setCode(HttpStatus.OK);
        responsesWrapped.setStatus(ResponsesWrapped.Status.success);
        responsesWrapped.setMessage("创建课程成功");

        responsesWrapped.setLink(new Link(href.toString()));

        return new ResponseEntity(responsesWrapped,headers,HttpStatus.CREATED);
    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(
            value =DELETE_COURSE,
            method = RequestMethod.DELETE)
    public ResponseEntity deleteCourse(
            @PathVariable("id") Integer id,
            HttpServletRequest request) {

        coursesService.deleteCourseById(id);
        ResponsesWrapped responsesWrapped=new ResponsesWrapped();

        responsesWrapped.setCode(HttpStatus.NO_CONTENT);
        responsesWrapped.setStatus(ResponsesWrapped.Status.success);
        responsesWrapped.setMessage("课程删除成功");
        responsesWrapped.setLink(new Link(request.getRequestURI()));

        return new ResponseEntity(responsesWrapped,HttpStatus.NO_CONTENT);
    }



}
