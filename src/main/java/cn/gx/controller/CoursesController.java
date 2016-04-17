package cn.gx.controller;

import cn.gx.bean.Course;
import cn.gx.entity.CourseView;
import cn.gx.exception.InvalidRequestException;
import cn.gx.exception.NotFoundException;
import cn.gx.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by guanxine on 16-4-17.
 */

@RestController
public class CoursesController {


    @Autowired
    CoursesService coursesService;

    public static final String GET_COURSE = "/courses/{id}";
    public static final String GET_ALL_COURSES = "/courses";
    public static final String CREATE_COURSE = "/courses";
    public static final String DELETE_COURSE = "/courses/{id}";


    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = GET_ALL_COURSES, method = RequestMethod.GET)
    public ResponseEntity<List<CourseView>> listAllCourses(HttpServletRequest request) {

        String path = request.getServletPath();
        List<CourseView>  courses = coursesService.findAllCourses(path);
        if(courses.isEmpty()){
            return new ResponseEntity<List<CourseView>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<CourseView>>(courses, HttpStatus.OK);
    }

    //-------------------Retrieve Single User--------------------------------------------------------

    @RequestMapping(value = GET_COURSE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseView> getUser(
            @PathVariable("id") Integer id) {
        System.out.println("Fetching User with id " + id);
        CourseView course = coursesService.findById(id);
        if (course == null) {
            System.out.println("User with id " + id + " not found");
            //return new ResponseEntity<CourseView>(HttpStatus.NOT_FOUND);
            throw new NotFoundException(id);
        }
        return new ResponseEntity<CourseView>(course, HttpStatus.OK);
    }

    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(
            value = CREATE_COURSE,
            method = RequestMethod.POST,
            produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(
            @RequestBody @Valid  CourseView courseView,
            UriComponentsBuilder ucBuilder,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            throw new InvalidRequestException("Invalid Courses", bindingResult);
        }



        System.out.println("Creating User " + courseView.getName());

        Course course=null;
        try {
            course = coursesService.saveCourse(courseView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path(GET_COURSE).buildAndExpand(course.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);


    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = DELETE_COURSE, method = RequestMethod.DELETE)
    public ResponseEntity<CourseView> deleteUser(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting User with id " + id);

        if (!coursesService.isCourseExist(id)) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<CourseView>(HttpStatus.NOT_FOUND);
        }else {
            coursesService.deleteCourseById(id);
            return new ResponseEntity<CourseView>(HttpStatus.NO_CONTENT);
        }


    }



}
