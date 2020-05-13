/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.controller;

import cn.l.x.base.Result;
import cn.l.x.bean.Course;
import cn.l.x.dictionary.Messages;
import cn.l.x.service.CrawlerService;
import cn.l.x.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ResponseBody
@RequestMapping("/crawler")
public class CrawlerController {

    private static final String baseFileFolder = "static/file/";

    static int i;
    // 单个字符的正则表达式
    private static final String singlePattern = "[0-9|a-f|A-F]";
    // 4个字符的正则表达式
    private static final String pattern = singlePattern + singlePattern + singlePattern + singlePattern;

    private static final Integer limitNum = 50;

    @Autowired
    private CrawlerService crawlerService;

    // 爬取视频
    @RequestMapping("/crawlerCourse")
    public Result crawlerCourse(String schoolCourseId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result<Course> rst = new Result<>();
        if (DataUtils.isNull(schoolCourseId)) {
            rst.equals(Messages.E0002);
        }
        // 获取课程基本信息
        if (rst.isSucceeded()) {
            // 第一步根据前台传来的ID获取课程基本信息(courseName和TermList)
            rst = crawlerService.getCourseInfoBySchoolCourseId(schoolCourseId);
        }
        // 获取课程详细信息
        if (rst.isSucceeded()) {
            Course course = rst.getObj();
            if (DataUtils.isNotNull(course) && DataUtils.isNotNull(course.getCourseName()) && !CollectionUtils.isEmpty(course.getTermList())) {
                // 根据course基本信息获取详细信息
                rst = crawlerService.getCourseDetailByCourse(course);
            } else {
                rst.setCodeMsg(Messages.E0002);
            }
        }
        // 下载信息
        if (rst.isSucceeded()) {
            Course course = rst.getObj();
            if (DataUtils.isNotNull(course) && DataUtils.isNotNull(course.getCourseName()) && !CollectionUtils.isEmpty(course.getChapters())) {
                // 根据课程详细信息下载相关文件
                rst = crawlerService.downloadFileByCourse(course);
            } else {
                rst.setCodeMsg(Messages.E0002);
            }
        }
        return rst;

    }

    // 爬取视频
    @RequestMapping("/crawlerCourseByCourse")
    public Result crawlerCourseByCourse(@RequestBody Course cour, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result<Course> rst = new Result<>();
        // 获取课程详细信息
            if (DataUtils.isNotNull(cour) && DataUtils.isNotNull(cour.getCourseName())) {
                // 根据course基本信息获取详细信息
                rst = crawlerService.getCourseDetailByCourse(cour);
            } else {
                rst.setCodeMsg(Messages.E0002);
            }
        // 下载信息
        if (rst.isSucceeded()) {
            Course course = rst.getObj();
            if (DataUtils.isNotNull(course) && DataUtils.isNotNull(course.getCourseName()) && !CollectionUtils.isEmpty(course.getChapters())) {
                // 根据课程详细信息下载相关文件
                rst = crawlerService.downloadFileByCourse(course);
            } else {
                rst.setCodeMsg(Messages.E0002);
            }
        }
        return rst;
    }

}
