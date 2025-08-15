/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.common.dictionary;

import cn.l.x.common.base.CodeMessage;
import cn.l.x.common.base.message.CommonMessages;

/**
 * 本项目中用到的 Result 定义。
 * 
 * @author 卢宏政
 *
 */
public interface Messages extends CommonMessages {

    /**
     * 【爬虫】获取网页源码为空。
     */
    CodeMessage E0001 = MessageBuilder.INSTANCE.error(1, "获取网页源码为空");

    /**
     * 【爬虫】缺乏必要参数。
     */
    CodeMessage E0002 = MessageBuilder.INSTANCE.error(2, "缺乏必要参数");

    /**
     * 【爬虫】获取课程详细信息失败。
     */
    CodeMessage E0003 = MessageBuilder.INSTANCE.error(3, "获取课程详细信息失败");

    /**
     * 【政务信息保存】政务信息主表保存失败。
     */
    CodeMessage E0004 = MessageBuilder.INSTANCE.error(4, "政务信息主表保存失败");

    /**
     * 【政务信息保存】政务信息附件表保存失败。
     */
    CodeMessage E0005 = MessageBuilder.INSTANCE.error(5, "政务信息附件表保存失败");

    /**
     * 【政务信息保存】政务信息内容表保存失败。
     */
    CodeMessage E0006 = MessageBuilder.INSTANCE.error(6, "政务信息内容表保存失败");

    /**
     * 【政务信息保存】政务信息保存失败。
     */
    CodeMessage E0007 = MessageBuilder.INSTANCE.error(7, "政务信息内容表保存失败");

    /**
     * 【政务信息删除】政务信息删除缺少必要参数。
     */
    CodeMessage E0008 = MessageBuilder.INSTANCE.error(8, "政务信息删除缺少必要参数");

    /**
     * 【政务信息更新状态】政务信息更新状态失败。
     */
    CodeMessage E0009 = MessageBuilder.INSTANCE.error(9, "政务信息更新状态失败");

    /**
     * 【政务信息发布】政务信息发布缺少必要参数。
     */
    CodeMessage E0010 = MessageBuilder.INSTANCE.error(10, "政务信息发布缺少必要参数");

    /**
     * 【政务信息废弃】政务信息废弃缺少必要参数。
     */
    CodeMessage E0011 = MessageBuilder.INSTANCE.error(11, "政务信息废弃缺少必要参数");

    /**
     * 【政务信息查询】政务信息获取详情缺少必要参数。
     */
    CodeMessage E0012 = MessageBuilder.INSTANCE.error(12, "政务信息获取详情缺少必要参数");

    /**
     * 【政务信息更新】政务信息状态异常。
     */
    CodeMessage E0013 = MessageBuilder.INSTANCE.error(13, "政务信息状态异常");

    /**
     * 【政务信息附件删除】政务信息附件删除缺少必要参数。
     */
    CodeMessage E0014 = MessageBuilder.INSTANCE.error(14, "附件删除缺少必要参数");

    /**
     * 【政务信息附件删除】政务信息附件删除政务信息附件不可删除。
     */
    CodeMessage E0015 = MessageBuilder.INSTANCE.error(15, "政务信息附件不可删除");

    /**
     * 【政务信息附件删除】政务信息附件删除附件失败。
     */
    CodeMessage E0016 = MessageBuilder.INSTANCE.error(16, "政务信息附件删除失败");

    /**
     * 【政务附件上传】附件上传失败。
     */
    CodeMessage E0017 = MessageBuilder.INSTANCE.error(17, "附件上传失败");

    /**
     * 【政务信息】政务信息主表更新失败。
     */
    CodeMessage E0018 = MessageBuilder.INSTANCE.error(18, "政务信息主表更新失败");

    /**
     * 【政务信息】政务信息内容表更新失败。
     */
    CodeMessage E0019 = MessageBuilder.INSTANCE.error(19, "政务信息内容表更新失败");

    /**
     * 【政务信息】政务信息更新失败。
     */
    CodeMessage E0020 = MessageBuilder.INSTANCE.error(20, "政务信息更新失败");

    /**
     * 【单点登录】无效的 AppId。
     */
    CodeMessage E0021 = MessageBuilder.INSTANCE.error(21, "无效的 AppId");

    /**
     * 【单点登录】无效的 Token 。
     */
    CodeMessage E0022 = MessageBuilder.INSTANCE.error(22, "您的登录已经过期。请重新登录。");

    /**
     * 【单点登录】行政区编码为空 。
     */
    CodeMessage E0023 = MessageBuilder.INSTANCE.error(23, "您的登录信息不正确。");

    /**
     * 【单点登录】Token 已过期。
     */
    CodeMessage E0024 = MessageBuilder.INSTANCE.error(24, "您的登录已经过期。请重新登录。");

    /**
     * 【单点登录】无效的用户信息。
     */
    CodeMessage E0025 = MessageBuilder.INSTANCE.error(25, "无效的用户信息");

    /**
     * 【单点登录】无效的密钥 。
     */
    CodeMessage E0026 = MessageBuilder.INSTANCE.error(26, "无效的密钥");

    /**
     * 【管理政务公开分类设置】缺乏必要参数。
     */
    CodeMessage E0041 = MessageBuilder.INSTANCE.error(41, "缺乏必要参数");

    /**
     * 【管理政务公开分类设置】添加分类失败。
     */
    CodeMessage E0042 = MessageBuilder.INSTANCE.error(42, "添加分类失败，请检查参数是否重复");

    /**
     * 【管理政务公开分类设置】查询父级分类失败。
     */
    CodeMessage E0043 = MessageBuilder.INSTANCE.error(43, "查询父级分类失败，请检查pid是否正确");

    /**
     * 【管理政务公开分类设置】更新分类失败。
     */
    CodeMessage E0044 = MessageBuilder.INSTANCE.error(44, "更新分类失败。");

    /**
     * 【管理政务公开分类设置】最多只能纳入统计3个分类。
     */
    CodeMessage E0045 = MessageBuilder.INSTANCE.error(45, "最多只能纳入统计3个分类");

    /**
     * 【管理政务公开分类设置】查询分类失败，请检查分类是否存在。
     */
    CodeMessage E0046 = MessageBuilder.INSTANCE.error(46, "查询分类失败，请检查分类是否存在。");
    /**
     * 【模板管理】插入模板管理。
     */
    CodeMessage E0050 = MessageBuilder.INSTANCE.error(50, "插入模板失败，缺少必要参数。");

    /**
     * 【模板管理】插入模板管理。
     */
    CodeMessage E0051 = MessageBuilder.INSTANCE.error(51, "插入模板失败，标题不能为空。");

    /**
     * 【模板管理】插入模板管理。
     */
    CodeMessage E0052 = MessageBuilder.INSTANCE.error(52, "插入模板失败，插入模板表失败。");

    /**
     * 【模板管理】更新模板管理。
     */
    CodeMessage E0053 = MessageBuilder.INSTANCE.error(53, "更新模板失败，分类失效。");

    /**
     * 【模板管理】删除模板管理。
     */
    CodeMessage E0054 = MessageBuilder.INSTANCE.error(54, "删除模板失败，缺少必要参数。");

    /**
     * 【模板管理】插入模板管理。
     */
    CodeMessage E0055 = MessageBuilder.INSTANCE.error(55, "插入模板失败，该模板所属的分类无效。");

    /**
     * 【模板管理】查询模板管理。
     */
    CodeMessage E0056 = MessageBuilder.INSTANCE.error(56, "查询模板失败，请检查id是否正确。");

    /**
     * 【联村干部】查询联村干部。
     */
    CodeMessage E0060 = MessageBuilder.INSTANCE.error(60, "查询失败，缺少必要地区id参数。");

    /**
     * 【联村干部】新增联村干部。
     */
    CodeMessage E0061 = MessageBuilder.INSTANCE.error(61, "新增联村干部，必填项不能为空");

    /**
     * 【联村干部】删除联村干部失败。
     */
    CodeMessage E0062 = MessageBuilder.INSTANCE.error(62, "新增联村干部，必填项不能为空");

    /**
     * 【联村干部】版本号查询失败。
     */
    CodeMessage E0063 = MessageBuilder.INSTANCE.error(63, "联村干部版本号查询失败");

    /**
     * 【联村干部】手机号重复。
     */
    CodeMessage E0064 = MessageBuilder.INSTANCE.error(64, "手机号重复");

    /**
     * 【政务信息模板查询】政务信息模板获取详情缺少必要参数。
     */
    CodeMessage E0101 = MessageBuilder.INSTANCE.error(101, "政务信息模板获取详情缺少必要参数");

    /**
     * 【直属公开公示仪表盘】后台异常。
     */
    CodeMessage E0102 = MessageBuilder.INSTANCE.error(102, "仪表盘数据获取异常");

    /**
     * 【直属公开公示仪表盘】行政区划编码为空。
     */
    CodeMessage E0103 = MessageBuilder.INSTANCE.error(103, "仪表盘数据获取失败,行政区划编码为空");
    /**
     * 【直属公开公示仪表盘】政务类型错误。
     */
    CodeMessage E0104 = MessageBuilder.INSTANCE.error(104, "仪表盘数据获取失败,政务类型错误");

    /**
     * 【政务信息保存】政务信息附件表删除失败。
     */
    CodeMessage E0105 = MessageBuilder.INSTANCE.error(105, "政务信息附件表删除失败");

    /**
     * 【四议两公开信息】缺乏必要参数。
     */
    CodeMessage E0106 = MessageBuilder.INSTANCE.error(106, "缺乏必要参数");

    /**
     * 【查询四议两公开信息分页】分页参数错误。
     */
    CodeMessage E0107 = MessageBuilder.INSTANCE.error(107, "分页参数错误");

    /**
     * 【四议两公开信息发布】四议两公开信息发布缺少必要参数。
     */
    CodeMessage E0108 = MessageBuilder.INSTANCE.error(108, "四议两公开信息发布缺少必要参数");

    /**
     * 【四议两公开信息更新】政务信息状态异常。
     */
    CodeMessage E0109 = MessageBuilder.INSTANCE.error(109, "四议两公开信息状态异常");

    /**
     * 【四议两公开信息查询】四议两公开信息获取详情缺少必要参数。
     */
    CodeMessage E0110 = MessageBuilder.INSTANCE.error(110, "四议两公开信息获取详情缺少必要参数");

    /**
     * 【四议两公开信息保存】四议两公开信息保存失败。
     */
    CodeMessage E0111 = MessageBuilder.INSTANCE.error(111, "四议两公开信息保存失败");

    /**
     * 【四议两公开信息保存】四议两公开信息审批记录保存失败。
     */
    CodeMessage E0112 = MessageBuilder.INSTANCE.error(112, "四议两公开信息审批记录保存失败");

    /**
     * 【四议两公开信息更新状态】四议两公开信息更新状态失败。
     */
    CodeMessage E0113 = MessageBuilder.INSTANCE.error(113, "四议两公开信息更新状态失败");

    /**
     * 【四议两公开信息保存】四议两公开信息主表保存失败。
     */
    CodeMessage E0114 = MessageBuilder.INSTANCE.error(114, "四议两公开信息主表保存失败");

    /**
     * 【四议两公开信息】四议两公开信息主表更新失败。
     */
    CodeMessage E0115 = MessageBuilder.INSTANCE.error(115, "四议两公开信息主表更新失败");

    /**
     * 【四议两公开信息】四议两公开信息会议表四会议保存失败。
     */
    CodeMessage E0116 = MessageBuilder.INSTANCE.error(116, "四议两公开信息会议表四会议保存失败");

}
