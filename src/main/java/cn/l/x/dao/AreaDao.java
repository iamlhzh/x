/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.l.x.bean.AreaEntity;

@Mapper
public interface AreaDao {

    void addArea(List<AreaEntity> list);

}
