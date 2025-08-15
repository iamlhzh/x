/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.course.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.l.x.course.bean.AdminUser;
import cn.l.x.course.bean.Test;
import cn.l.x.course.service.TestService;

/**
 * 测试类。
 * 
 * @author 卢宏政
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;
    
    
    
    /**映射单个url
     * @param request
     * @return
     */
    @RequestMapping("/one")
    public String one(HttpServletRequest request) {
        return "one";
    }
    
    /**映射多个url
     * @param request
     * @return
     */
    @RequestMapping({"/two","/three"})
    public String two(HttpServletRequest request) {
        return "two";
    }
    
    /**Ant 风格的 URL 映射
     * ?

     *匹配任何单字符
     * @param request
     * @return
     */
    @RequestMapping("/?/ant1")
    public String ant1(HttpServletRequest request) {
        return "ant1";
    }
    /**Ant 风格的 URL 映射
     * *
     *匹配任意数量的字符（含 0 个）
     * @param request
     * @return
     */
    @RequestMapping("/*/ant2")
    public String ant2(HttpServletRequest request) {
        return "ant2";
    }
    /**Ant 风格的 URL 映射
     * **
     *匹配任意数量的目录（含 0 个）
     * @param request
     * @return
     */
    @RequestMapping("/**/ant3")
    public String ant3(HttpServletRequest request) {
        return "ant3";
    }
    
    /**占位符URL 映射
     * URL 中可以通过一个或多个 {} 占位符映射
     * 可以通过@PathVariable("") 注解将占位符中的值绑定到方法参数上
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/user/{id}/{name}/placeHolder")
    public String placeHolder(HttpServletRequest request,@PathVariable String id,@PathVariable("name")String name) {
        return id+"-"+name;
    }
    
    /**限制请求方法的URL 映射
     * @param request
     * @return
     */
    @RequestMapping(value="/requestType",method= {RequestMethod.POST,RequestMethod.GET})
    public String requestType(HttpServletRequest request) {
        return "requestType";
    }
    
    /**限制请求参数的URL 映射
     * （1）params="userId" 请求参数中必须包含 userId
     * @param request
     * @param paramId
     * @return
     */
    @RequestMapping(value="/params1",params="userId")
    public String params1(HttpServletRequest request,@RequestParam("userId")String userId) {
        return userId;
    }
    
    /**限制请求参数的URL 映射
     * （2）params="!userId" 请求参数中不能包含 userId
     * @param request
     * @param paramId
     * @return
     */
    @RequestMapping(value="/params2",params="!userId")
    public String params2(HttpServletRequest request,@RequestParam("userId")String userId) {
        return userId;
    }
    /**限制请求参数的URL 映射
     * （3）params="userId!=1" 请求参数中必须包含 userId，但不能为 1
     * @param request
     * @param paramId
     * @return
     */
    @RequestMapping(value="/params3",params="userId!=1")
    public String params3(HttpServletRequest request,@RequestParam("userId")String userId) {
        return userId;
    }
    
    /**限制请求参数的URL 映射
     * （4）params={"userId","userName"} 必须包含 userId 和 userName 参数
     * @param request
     * @param paramId
     * @return
     */
    @RequestMapping(value="/params4",params= {"userId!=1","userName"})
    public String params4(HttpServletRequest request,@RequestParam("userId")String userId,@RequestParam("userName")String userName) {
        return userId+"-"+userName;
    }
    
    
    
    
    
    
    

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

    @RequestMapping("/addAdminUser")
    public String addAdminUser(HttpServletRequest request) {
        AdminUser adu = new AdminUser();
        adu.setId("11");
        adu.setLoginName("lhzh");
        adu.setRealName("卢宏政");
        testService.addAdminUser(adu);
        return "OK";
    }

}
