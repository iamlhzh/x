/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.service.impl;


import cn.l.x.bean.School;
import cn.l.x.service.SchoolService;
import cn.l.x.utils.HtmlUtil;
import cn.l.x.utils.HttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {


    @Override
    public List<School> getAllSchool(String url) {
        String sendGet= HttpRequest.sendGet(url);
        List<School>schoolList= HtmlUtil.getSchoolList(sendGet);
        return schoolList;
    }

    @Override
    public String getSchoolIdByAbbreviation(String url) {
        String sendGet= HttpRequest.sendGet(url);
        String schoolId=HtmlUtil.getItemByStr(sendGet,"window.schoolId");
        return schoolId;
    }
}
