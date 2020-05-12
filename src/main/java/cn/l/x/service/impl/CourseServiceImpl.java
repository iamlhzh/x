/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.service.impl;


import cn.l.x.base.Result;
import cn.l.x.bean.School;
import cn.l.x.bean.SchoolCourseQuery;
import cn.l.x.service.CourseService;
import cn.l.x.service.SchoolService;
import cn.l.x.utils.HtmlUtil;
import cn.l.x.utils.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private static String HOSTURL="https://www.icourse163.org";

    @Override
    public Result getAllCourse(SchoolCourseQuery schoolCourseQuery) {
        Result rst=new Result();
        String url=HOSTURL+"/web/j/courseBean.getCourseListBySchoolId.rpc?csrfKey=330c9bb517f545958eff0da2404f7f98";
        String params="schoolId="+schoolCourseQuery.getSchoolId()
                +"&p="+schoolCourseQuery.getP()+"&psize="+schoolCourseQuery.getPsize()+
                "&type="+schoolCourseQuery.getType()+"&courseStatus="+schoolCourseQuery.getCourseStatus();
        String cookie="NTESSTUDYSI=330c9bb517f545958eff0da2404f7f98";
        String sendPost=HttpRequest.sendMyPost(url,params,cookie);
        JSONObject jsonObject = JSON.parseObject(sendPost);
        rst.setObj(jsonObject);
        return rst;
    }
}
