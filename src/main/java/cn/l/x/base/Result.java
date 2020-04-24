/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.base;

import cn.l.x.base.message.CommonMessages;

/**
 * HTTP请求返回消息类型。
 * 
 * @author 马岳
 *
 */
public class Result<T> extends CodeMessage {
    /**
     * 序列化版本号。
     */
    private static final long serialVersionUID = -6421279427859768728L;

    /**
     * 返回对象(具体类型参照具体的HTTP请求)。
     */
    private T obj;

    public Result() {
        setCodeMsg(CommonMessages.SUCCESS);
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "code->" + getCode() + ",msg->" + getMsg() + ",obj->" + obj;
    }
}
