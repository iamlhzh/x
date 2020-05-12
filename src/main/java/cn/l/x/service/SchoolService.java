/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.service;


import cn.l.x.bean.School;

import java.util.List;

public interface SchoolService {


    List<School> getAllSchool(String url);

    String getSchoolIdByAbbreviation(String url);
}
