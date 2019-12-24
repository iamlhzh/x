/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.l.x.bean.Test;
import cn.l.x.service.TestService;

/**
 * 测试类。
 * 
 * @author 卢宏政去吧
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public String test(HttpServletRequest request) {
        return "Hello!";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        Test t = new Test();
        t.setId("1");
        t.setName("张三");
        testService.addMsg(t);
        return "OK";
    }

}
