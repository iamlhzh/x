/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.common.base.message;

public class CommonMessageBuilder extends AbstractMessageBuilder {
    /**
     * 声明CommonMessageBuilder。
     */
    public static final CommonMessageBuilder INSTANCE = new CommonMessageBuilder();

    private CommonMessageBuilder() {
        // 禁止外部生成实例
    }

    @Override
    public String getSubSystemCode() {
        return CommonSubSystemCodes.COMMON;
    }
}
