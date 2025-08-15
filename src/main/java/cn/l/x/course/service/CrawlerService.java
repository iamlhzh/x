/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.course.service;

import javax.script.ScriptException;

import cn.l.x.common.base.Result;
import cn.l.x.course.bean.Course;

public interface CrawlerService {

    Result<Course> getCourseInfoBySchoolCourseId(String schoolCourseId);

    Result<Course> getCourseDetailByCourse(Course course) throws ScriptException;

    Result<Course> downloadFileByCourse(Course course);

}
