/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.l.x.bean.Test;
import cn.l.x.dao.TestDao;
import cn.l.x.service.TestService;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestDao testDao;

    @Override
    public void addMsg(Test t) {
        System.out.println("1111111111111111111111111");
        testDao.add(t);

    }
}
