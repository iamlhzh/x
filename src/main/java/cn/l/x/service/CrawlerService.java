/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.service;

import cn.l.x.base.Result;
import cn.l.x.bean.Course;

public interface CrawlerService {

    Result<Course> getCourseInfoBySchoolCourseId(String schoolCourseId);

    Result<Course> getCourseDetailByCourse(Course course);

}
