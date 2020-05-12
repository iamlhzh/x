/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.service;


import cn.l.x.base.Result;
import cn.l.x.bean.School;
import cn.l.x.bean.SchoolCourseQuery;

import java.util.List;

public interface CourseService {


    Result getAllCourse(SchoolCourseQuery schoolCourseQuery);
}
