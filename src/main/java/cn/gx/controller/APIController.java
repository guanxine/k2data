package cn.gx.controller;

import cn.gx.util.APIHelper;
import cn.gx.util.CoursesAPIHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guanxine on 16-4-19.
 */
@RestController
@RequestMapping("/api")
public class APIController {


    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity helpAPI(HttpServletRequest request){

        String url = request.getRequestURL().toString();
        String substring = url.substring(0, url.lastIndexOf("/api"));

        APIHelper cHelper=new CoursesAPIHelper(substring);
        List apiMap=new ArrayList(1);
        List courses=cHelper.getAllRequest();
        apiMap.add(courses);

        return new ResponseEntity(apiMap, HttpStatus.OK);
    }
}
