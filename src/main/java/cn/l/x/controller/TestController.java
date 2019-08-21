/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类。
 * 
 * @author 卢宏政
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test")
    public String Test(HttpServletRequest request) {
        return "Hello!";
    }

}
