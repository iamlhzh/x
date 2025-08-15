/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.course.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.l.x.course.bean.AdminUser;
import cn.l.x.course.bean.Test;
import cn.l.x.course.dao.TestDao;
import cn.l.x.course.service.TestService;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestDao testDao;

    @Override
    public void addMsg(Test t) {
        System.out.println("1111111111111111111111111");
        testDao.add(t);

    }

    @Override
    public void addAdminUser(AdminUser adu) {

        testDao.addAdminUser(adu);
    }
}
