/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.controller;

import cn.l.x.base.Result;
import cn.l.x.bean.Course;
import cn.l.x.bean.School;
import cn.l.x.bean.SchoolCourseQuery;
import cn.l.x.service.CourseService;
import cn.l.x.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SchoolService schoolService;

    private static String HOSTURL="https://www.icourse163.org";

    // 爬取视频
    @RequestMapping("/getAllCourse")
    public Result getAllCourse(SchoolCourseQuery SchoolCourseQuery, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(SchoolCourseQuery);
        String url=HOSTURL+SchoolCourseQuery.getAbbreviation();
        String schoolId=schoolService.getSchoolIdByAbbreviation(url);
        SchoolCourseQuery.setSchoolId(schoolId);
        Result rst=courseService.getAllCourse(SchoolCourseQuery);
        return rst;
    }
}
