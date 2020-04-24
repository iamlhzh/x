/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.base.message;

public class SystemMessageBuilder extends AbstractMessageBuilder {
    /**
     * 声明SystemMessageBuilder。
     */
    public static final SystemMessageBuilder INSTANCE = new SystemMessageBuilder();

    private SystemMessageBuilder() {
        // 禁止外部生成实例
    }

    @Override
    public String getSubSystemCode() {
        return CommonSubSystemCodes.SYSTEM;
    }
}
