/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.course.service;


import cn.l.x.course.bean.School;

import java.util.List;

public interface SchoolService {


    List<School> getAllSchool(String url);

    String getSchoolIdByAbbreviation(String url);
}
