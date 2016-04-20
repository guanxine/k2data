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
 *  API 控制类
 *
 */
@RestController
public class APIController {


    /**
     * 显示所有 restful api 信息。
     * @param request
     * @return
     */
    @RequestMapping(value = "/api",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
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
