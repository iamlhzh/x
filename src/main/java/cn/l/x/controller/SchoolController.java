/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.controller;

import cn.l.x.base.Result;
import cn.l.x.bean.Course;
import cn.l.x.bean.School;
import cn.l.x.dictionary.Messages;
import cn.l.x.service.CrawlerService;
import cn.l.x.service.SchoolService;
import cn.l.x.utils.DataUtils;
import cn.l.x.utils.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    private static String SCHOOLURL="https://www.icourse163.org/university/view/all.htm";

    private static String HOSTURL="https://www.icourse163.org";

    // 爬取视频
    @RequestMapping("/getAllSchool")
    public Result getAllSchool(HttpServletRequest request, HttpServletResponse response) {
        Result<Object> rst = new Result<>();
        List<School> schoolList=schoolService.getAllSchool(SCHOOLURL);
        rst.setObj(schoolList);
        return rst;
    }
}
