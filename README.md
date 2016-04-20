
# 项目说明

使用 springmvc 作为 REST API 框架

使用 MySQL 作为后台存储

数据库配置在文件 `kedata/WEB-INF/db/db.properties`

# 项目 Docker 部署


用 Fig 部署

```
fig up -d
```

然后 `fig ps`

```
                   Name                                 Command               State             Ports          
--------------------------------------------------------------------------------------------------------------
dockersamplejavamysqltomcatmaster_app_1      /bin/sh -e /usr/local/bin/run    Up       0.0.0.0:32772->8080/tcp 
dockersamplejavamysqltomcatmaster_db_1       /usr/local/bin/run               Up       0.0.0.0:32771->3306/tcp 
dockersamplejavamysqltomcatmaster_dbinit_1   /bin/bash -c sleep 4; mysq ...   Exit 0                           
```

访问部署项目

```
http://\<docker-host-ip\>:\<app-port\>/k2data/courses
```

# 测试项目

* 查看项目 api

```
➜  ~  curl -i -X GET  http://10.42.0.1:32772/k2data/api
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json
Transfer-Encoding: chunked
Date: Tue, 19 Apr 2016 17:05:34 GMT


[
    [
        {
            "name": "创建课程", 
            "url": "http://10.42.0.1:32772/k2data/courses", 
            "method": "POST", 
            "example": "http://10.42.0.1:32772/k2data/courses", 
            "param": {
                "name": "REST API Best Practices", 
                "time": {
                    "start": "2016-01-04", 
                    "end": "2016-01-06"
                }, 
                "estimatedTime": 24, 
                "facilitator": "Awesome Developer"
            }
        }, 
        {
            "name": "查看课程列表", 
            "url": "http://10.42.0.1:32772/k2data/courses", 
            "method": "GET", 
            "example": "http://10.42.0.1:32772/k2data/courses"
        }, 
        {
            "name": "查看课程", 
            "url": "http://10.42.0.1:32772/k2data/courses/{id}", 
            "method": "GET", 
            "example": "http://10.42.0.1:32772/k2data/courses/1"
        }, 
        {
            "name": "删除课程", 
            "url": "http://10.42.0.1:32772/k2data/courses/{id}", 
            "method": "DELETE", 
            "example": "http://10.42.0.1:32772/k2data/courses/1"
        }
    ]
]
```

* 查看课程列表

```
➜  ~   curl -i -X GET  http://10.42.0.1:32772/k2data/courses

HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Tue, 19 Apr 2016 17:09:24 GMT


{
    "courses": [
        {
            "name": "java", 
            "time": {
                "start": "2016-04-10", 
                "end": "2016-04-22"
            }, 
            "estimatedTime": 20, 
            "facilitator": "shaonian", 
            "link": {
                "rel": "self", 
                "href": "http://10.42.0.1:32772/k2data/courses/1"
            }
        }
    ], 
    "info": {
        "status": "success", 
        "code": "OK", 
        "message": "查询课程列表成功"
    }
}
```

* 创建课程


```
➜  ~ curl -i -X POST -H "Content-Type:application/json" -d '{"name": "REST API Best Practices", "time": {"start": "2016-01-04", "end": "2016-01-06"},"estimatedTime": 24,"facilitator": "Awesome Developer"}'  http://10.42.0.1:32772/k2data/courses


HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
Location: http://10.42.0.1:32772/k2data/courses/2
Content-Type: application/json
Transfer-Encoding: chunked
Date: Tue, 19 Apr 2016 17:18:14 GMT

{
    "status": "success", 
    "code": "OK", 
    "message": "创建课程成功", 
    "link": {
        "rel": "self", 
        "href": "http://10.42.0.1:32772/k2data/courses/2"
    }
}
```
* 查看课程

```
➜  ~   curl -i -X GET  http://10.42.0.1:32772/k2data/courses/2
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json
Transfer-Encoding: chunked
Date: Tue, 19 Apr 2016 17:24:52 GMT

{
    "course": {
        "name": "REST API Best Practices", 
        "time": {
            "start": "2016-01-04", 
            "end": "2016-01-06"
        }, 
        "estimatedTime": 24, 
        "facilitator": "Awesome Developer", 
        "link": {
            "rel": "self", 
            "href": "http://10.42.0.1:32772/k2data/courses/2"
        }
    }, 
    "info": {
        "status": "success", 
        "code": "OK", 
        "message": "查询课程成功"
    }
}
```


* 删除课程

```
➜  ~  curl -i -X DELETE   http://10.42.0.1:32772/k2data/courses/2
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Tue, 19 Apr 2016 17:36:35 GMT

{
    "status": "success", 
    "code": "OK", 
    "message": "课程删除成功", 
    "link": {
        "rel": "self", 
        "href": " http://10.42.0.1:32772/k2data/courses/2"
    }
}
```

* 异常处理

若提交 课程名字，课程时间为空。

```
➜  ~ curl -i -X POST -H "Content-Type:application/json" -d '{"time": {},"estimatedTime": 24,"facilitator": "Awesome Developer"}'  http://10.42.0.1:32772/k2data/courses

HTTP/1.1 422 Unprocessable Entity
Server: Apache-Coyote/1.1
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 20 Apr 2016 01:29:01 GMT


{
    "code": "InvalidRequest", 
    "message": "Validation failed for argument at index 1 in method: public org.springframework.http.ResponseEntity cn.gx.controller.CoursesController.createCourse(org.springframework.web.util.UriComponentsBuilder,cn.gx.entity.CourseView), with 5 error(s): [Field error in object 'courseView' on field 'name': rejected value [null]; codes [notnull.courseView.name,notnull.name,notnull.java.lang.String,notnull]; arguments []; default message [name 不能为空]] [Field error in object 'courseView' on field 'time.start': rejected value [null]; codes [not null.courseView.time.start,not null.time.start,not null.start,not null.java.util.Date,not null]; arguments []; default message [start 不能为空]] [Field error in object 'courseView' on field 'time.end': rejected value [null]; codes [not null.courseView.time.end,not null.time.end,not null.end,not null.java.util.Date,not null]; arguments []; default message [end 不能为空]] [Field error in object 'courseView' on field 'time.start': rejected value [null]; codes [start 不能晚于 end.courseView.time.start,start 不能晚于 end.time.start,start 不能晚于 end.start,start 不能晚于 end.java.util.Date,start 不能晚于 end]; arguments []; default message [null]] [Field error in object 'courseView' on field 'time.end': rejected value [null]; codes [ end 不能早于 start.courseView.time.end, end 不能早于 start.time.end, end 不能早于 start.end, end 不能早于 start.java.util.Date, end 不能早于 start]; arguments []; default message [null]] ", 
    "fieldErrors": [
        {
            "resource": "courseView", 
            "field": "name", 
            "code": "notnull", 
            "message": "name 不能为空"
        }, 
        {
            "resource": "courseView", 
            "field": "time.start", 
            "code": "not null", 
            "message": "start 不能为空"
        }, 
        {
            "resource": "courseView", 
            "field": "time.end", 
            "code": "not null", 
            "message": "end 不能为空"
        }, 
        {
            "resource": "courseView", 
            "field": "time.start", 
            "code": "start 不能晚于 end"
        }, 
        {
            "resource": "courseView", 
            "field": "time.end", 
            "code": " end 不能早于 start"
        }
    ]
}
```

* 若提交无法映射的请求

```
curl -i -X GET   http://10.42.0.1:32772/k2data/courses/2/shaonian

HTTP/1.1 404 Not Found
Server: Apache-Coyote/1.1
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Wed, 20 Apr 2016 01:35:44 GMT

{
    "status": "error", 
    "code": "NOT_FOUND", 
    "message": "No handler found for GET /courses/2/shaonian", 
    "link": {
        "rel": "self", 
        "href": " http://10.42.0.1:32772/k2data/courses/2/shaonian"
    }
}
```



