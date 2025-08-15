/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.course.service;


import cn.l.x.common.base.Result;
import cn.l.x.course.bean.SchoolCourseQuery;

public interface CourseService {


    Result getAllCourse(SchoolCourseQuery schoolCourseQuery);
}
