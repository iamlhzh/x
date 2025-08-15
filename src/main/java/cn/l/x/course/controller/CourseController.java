/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.course.controller;

import cn.l.x.common.base.Result;
import cn.l.x.course.bean.Course;
import cn.l.x.course.bean.SchoolCourseQuery;
import cn.l.x.common.dictionary.Messages;
import cn.l.x.course.service.CourseService;
import cn.l.x.course.service.CrawlerService;
import cn.l.x.course.service.SchoolService;
import cn.l.x.common.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ResponseBody
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private CrawlerService crawlerService;

    private static String HOSTURL="https://www.icourse163.org";

    @RequestMapping("/getAllCourse")
    public Result getAllCourse(@RequestBody SchoolCourseQuery SchoolCourseQuery, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(SchoolCourseQuery);
        String url=HOSTURL+SchoolCourseQuery.getAbbreviation();
        String schoolId=schoolService.getSchoolIdByAbbreviation(url);
        SchoolCourseQuery.setSchoolId(schoolId);
        Result rst=courseService.getAllCourse(SchoolCourseQuery);
        return rst;
    }

    // 获取可用版本
    @RequestMapping("/getCourseVersions")
    public Result getCourseVersions(String schoolCourseId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result<Course> rst = new Result<>();
        if (DataUtils.isNull(schoolCourseId)) {
            rst.equals(Messages.E0002);
        }
        // 获取课程基本信息
        if (rst.isSucceeded()) {
            // 第一步根据前台传来的ID获取课程基本信息(courseName和TermList)
            rst = crawlerService.getCourseInfoBySchoolCourseId(schoolCourseId);
        }
        return rst;
    }
}
