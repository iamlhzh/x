/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.course.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 地区实体类。
 * 
 * @author 卢宏政
 * @date 2020年2月19日
 */
public class AreaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 父级编码。
     */
    private String pcode;
    /**
     * 地区编码。
     */
    private String code;
    /**
     * 地区名称。
     */
    private String name;

    private Integer level;

    /**
     * 子级地区。
     */
    private List<AreaEntity> children;

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<AreaEntity> getChildren() {
        return children;
    }

    public void setChildren(List<AreaEntity> children) {
        this.children = children;
    }

}
