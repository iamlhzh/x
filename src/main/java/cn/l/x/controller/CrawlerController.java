/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.l.x.base.Result;
import cn.l.x.bean.Course;
import cn.l.x.dictionary.Messages;
import cn.l.x.service.CrawlerService;
import cn.l.x.utils.DataUtils;

@Controller
@ResponseBody
@RequestMapping("/crawler")
public class CrawlerController {

    private static String baseFileFolder = "static/file/";

    static int i;
    // 单个字符的正则表达式
    private static final String singlePattern = "[0-9|a-f|A-F]";
    // 4个字符的正则表达式
    private static final String pattern = singlePattern + singlePattern + singlePattern + singlePattern;

    // FFmpeg全路径
    private static final String FFMPEG_PATH = "C:\\ffmpeg-20200403-52523b6-win64-static\\bin\\ffmpeg.exe";

    private static Integer limitNum = 50;

    @Autowired
    private CrawlerService crawlerService;

    // 爬取视频
    @RequestMapping("/crawlerCourse")
    public Result crawlerCourse(String schoolCourseId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result<Course> rst = new Result<>();
        // 第一步根据前台传来的ID获取课程基本信息(courseName和TermList)
        rst = crawlerService.getCourseInfoBySchoolCourseId(schoolCourseId);
        if (rst.isSucceeded()) {
            Course course = rst.getObj();
            if (DataUtils.isNotNull(course.getCourseName()) && CollectionUtils.isEmpty(course.getTermList())) {
                // 根据course基本信息获取详细信息
                rst = crawlerService.getCourseDetailByCourse(course);
            } else {
                rst.setCodeMsg(Messages.E0002);
            }
        }
        return rst;

    }
}
