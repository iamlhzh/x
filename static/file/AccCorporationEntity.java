/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package com.fubao.gongkai.db.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 法人信息实体类。
 * 
 * @author 卢宏政
 * @date 2020年3月4日
 */
public class AccCorporationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID。
     */
    private Long id;

    /**
     * 法人名称。
     */
    private String corporationName;

    /**
     * 法人编码，18位无符号整数，前8位是法人ID编码，之后每2位标识一个部门层级（从1开始），最多5层，不足位用0补全
     */
    private Long corporationCode;

    /**
     * 法人类型，1特殊法人，2党组织，3政府机关，类型具体内容待确定
     */
    private Integer corporationType;

    /**
     * 法人级别，1省级 -> 2地市级 -> 3区县级 -> 4街道/乡镇级 -> 5小区/村庄级，其中4、5级别父级ID固定为空
     */
    private Integer corporationLevel;

    /**
     * 省编码
     */
    private Long province;
    /**
     * 市编码
     */
    private Long city;
    /**
     * 区县编码
     */
    private Long area;
    /**
     * 街道/乡镇编码
     */
    private Long street;
    /**
     * 小区/村庄编码
     */
    private Long community;

    /**
     * 法人唯一标识
     */
    private String orgKey;

    /**
     * 父级ID
     */
    private Long pid;

    /**
     * 状态，0停用，1正常 ，2维护状态
     */
    private Integer status;

    /**
     * 数据版本号，记录版本，用于乐观锁处理，默认值为0，每次修改时记录版本+1
     */
    private Integer version;

    /**
     * 新增时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public Long getCorporationCode() {
        return corporationCode;
    }

    public void setCorporationCode(Long corporationCode) {
        this.corporationCode = corporationCode;
    }

    public Integer getCorporationType() {
        return corporationType;
    }

    public void setCorporationType(Integer corporationType) {
        this.corporationType = corporationType;
    }

    public Integer getCorporationLevel() {
        return corporationLevel;
    }

    public void setCorporationLevel(Integer corporationLevel) {
        this.corporationLevel = corporationLevel;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Long getStreet() {
        return street;
    }

    public void setStreet(Long street) {
        this.street = street;
    }

    public Long getCommunity() {
        return community;
    }

    public void setCommunity(Long community) {
        this.community = community;
    }

    public String getOrgKey() {
        return orgKey;
    }

    public void setOrgKey(String orgKey) {
        this.orgKey = orgKey;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
