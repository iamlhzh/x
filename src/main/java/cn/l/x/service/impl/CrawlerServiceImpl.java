/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.l.x.base.Result;
import cn.l.x.bean.Course;
import cn.l.x.bean.Term;
import cn.l.x.bean.Unit;
import cn.l.x.dictionary.Messages;
import cn.l.x.service.CrawlerService;
import cn.l.x.utils.DataUtils;
import cn.l.x.utils.HtmlUtil;
import cn.l.x.utils.HttpRequest;

@Service
public class CrawlerServiceImpl implements CrawlerService {

    @Value("${crawler.baseHostUrl}")
    private String baseHostUrl;

    // = "https://www.icourse163.org/course/PKU-1002530002";

    @Override
    public Result<Course> getCourseInfoBySchoolCourseId(String schoolCourseId) {
        Result<Course> rst = new Result<>();
        Course c = new Course();
        String url = baseHostUrl + "course/" + schoolCourseId;
        // 获取网页源码
        String html = HttpRequest.GetHTML(url);
        if (DataUtils.isNull(html)) {
            rst.setCodeMsg(Messages.E0001);
        }
        if (rst.isSucceeded()) {
            // 获取课程名称
            String courseName = HtmlUtil.getCourseNameByHtml(html);
            if (DataUtils.isNotNull(courseName)) {
                c.setCourseName(courseName);
            }
            // // 新建一个文件夹
            // String filePath = System.getProperty("user.dir");
            // File path = new File(filePath);
            // File baseDirectory = new File(path, baseFileFolder);
            // File toDirectory = new File(baseDirectory, courseName);
            // boolean existDircetory = false;
            // if (toDirectory.exists()) {
            // existDircetory = true;
            // } else {
            // existDircetory = toDirectory.mkdirs();
            // }
            // 获取课程的信息；可能有多个但是他们的termId是一样的
            List<Term> termList = HtmlUtil.getTermByHtml(html);
            if (!CollectionUtils.isEmpty(termList)) {
                c.setTermList(termList);
            }
        }
        rst.setObj(c);
        return rst;
    }

    @Override
    public Result<Course> getCourseDetailByCourse(Course course) {
        List<Term> termList = course.getTermList();
        // 根据tid获取每一集的id
        List<Unit> bizList = new ArrayList<>();
        for (Term term : termList) {
            bizList.addAll(getBizIdListByTid(term.getId()));
        }
        return null;
    }

}
