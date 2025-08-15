/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.common.dictionary;

import cn.l.x.common.base.message.AbstractMessageBuilder;

public class MessageBuilder extends AbstractMessageBuilder {
    /**
     * 声明MessageBuilder。
     */
    public static final MessageBuilder INSTANCE = new MessageBuilder();

    private MessageBuilder() {
        // 禁止外部生成实例
    }

    @Override
    public String getSubSystemCode() {
        return SubSystemCodes.BIZ_GONGDAN;
    }
}
