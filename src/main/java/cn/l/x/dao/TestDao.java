/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.l.x.bean.AdminUser;
import cn.l.x.bean.Test;

@Mapper
public interface TestDao {

    void add(Test t);

    void addAdminUser(AdminUser adu);

}
