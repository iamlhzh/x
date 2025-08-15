/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.course.service.impl;

import javax.annotation.Resource;

import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import cn.l.x.course.bean.AdminUser;
import cn.l.x.course.bean.Test;
import cn.l.x.course.dao.TestDao;
import cn.l.x.course.service.TestService;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void addAdminUser(AdminUser adu) {

        testDao.addAdminUser(adu);
        throw new RuntimeException("测试事务回滚");

    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void addAdminUser2(AdminUser adu) {

//        this.addAdminUser(adu);
        ((TestService)AopContext.currentProxy()).addAdminUser(adu);
    }
}
