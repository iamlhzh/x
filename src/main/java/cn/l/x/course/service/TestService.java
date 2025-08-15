/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.course.service;

import cn.l.x.course.bean.AdminUser;
import cn.l.x.course.bean.Test;

public interface TestService {

    void addMsg(Test t);

    void addAdminUser(AdminUser adu);

    void addAdminUser2(AdminUser adu);
}
