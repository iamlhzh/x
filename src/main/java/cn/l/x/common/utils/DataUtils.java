/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;

/**
 * 数据转换工具类。
 * 
 * @author 马岳
 *
 */
public class DataUtils {
    /**
     * Logger 实例。
     */
    private static Logger logger = Logger.getLogger(DataUtils.class);

    /**
     * 随机数生成器。
     */
    private static Random rand = new Random();

    /**
     * 图形验证码可用字符。
     */
    private static final String BATH_STRING = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";

    /**
     * 默认除法运算精度。
     */
    private static final int DEF_DIV_SCALE = 2;

    /**
     * 保留位数参数错误提示。
     */
    private static final String SCALE_VERIFY_MSG = "The scale must be a positive integer or zero";

    /**
     * yyyy-MM-dd HH:mm:ss 格式。
     */
    public static final String YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 转换为BigDecimal。
     *
     * @param obj 输入参数
     * @return BigDecimal
     */
    public static BigDecimal $B(Object obj) {
        return $B(obj, 0);
    }

    /**
     * 转换为BigDecimal。
     *
     * @param obj 输入参数
     * @param defaultValue 默认值
     * @return BigDecimal
     */
    public static BigDecimal $B(Object obj, Object defaultValue) {
        if (isNull(obj)) {
            return new BigDecimal(String.valueOf(defaultValue));
        }
        return new BigDecimal(String.valueOf(obj));
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        final BigDecimal b1 = new BigDecimal(Double.toString(v1));
        final BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 转换为Double。
     *
     * @param obj 输入参数
     * @return Double
     */
    public static Double D$(Object obj) {
        return D$(obj, 0);
    }

    /**
     * 转换为Double。
     *
     * @param obj 输入参数
     * @param defaultValue 默认值
     * @return Double
     */
    public static Double D$(Object obj, double defaultValue) {
        double tmp = 0;
        try {
            tmp = Double.valueOf(String.valueOf(obj));
        } catch (final Exception e) {
            logger.error("DataUtils-D$ error", e);
            tmp = defaultValue;
        }
        return tmp;
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(SCALE_VERIFY_MSG);
        }
        final BigDecimal b1 = new BigDecimal(Double.toString(v1));
        final BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示需要精确到小数点以后几位
     * @return 两个参数的商
     */
    public static String div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(SCALE_VERIFY_MSG);
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 将字符编码成 utf-8 字节流。
     *
     * @param str 字符串
     * @return 编码后的字节流
     */
    public static byte[] encode(String str) {
        return encode(str, "utf-8");
    }

    /**
     * 将字符按指定字符集编码成字节流。
     *
     * @param str 字符串
     * @param charset 字符集
     * @return 编码后的字节流
     * @return
     */
    public static byte[] encode(String str, String charset) {
        byte[] arr = null;
        try {
            arr = str.getBytes(charset);
        } catch (final Exception e) {
            logger.error("encode() error", e);
        }
        return arr;
    }

    /**
     * 判断map是否存在list中。
     *
     * @param list Map列表
     * @param key key
     * @param val value
     * @return 是否存在
     */
    public static boolean exists(List<Map<String, Object>> list, String key, Object val) {
        boolean exists = false;
        for (final Map<String, Object> map : list) {
            if (String.valueOf(map.get(key)).equals(String.valueOf(val))) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * 从list中找到map。
     *
     * @param list Map列表
     * @param key key
     * @param val value
     * @return 所在Map
     */
    public static Map<String, Object> findMapFromList(List<Map<String, Object>> list, String key, Object val) {
        Map<String, Object> retMap = null;
        for (final Map<String, Object> map : list) {
            if (val.equals(map.get(key))) {
                retMap = map;
                break;
            }
        }
        return retMap;
    }

    /**
     * 制定字符串长度不足len在字符串前端 补 "0"。
     *
     * @param text 输入字符串
     * @param len 期望长度
     * @return 补全后的字符串
     */
    public static String padStr(String text, int len) {
        if (text.length() < len) {
            final int cl = len - text.length();
            final StringBuffer sbf = new StringBuffer();
            for (int i = 0; i < cl; i++) {
                sbf.append("0");
            }
            return sbf.append(text).toString();
        }
        return text;
    }

    /**
     * 获取用户真实ip地址。
     *
     * @param request HTTP请求
     * @return 真实 IP 地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        try {
            String ip = request.getHeader("X-Real-IP");
            if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
            ip = request.getHeader("X-Forwarded-For");
            if (null != ip && !"".equals(ip.trim()) && !"unknown".equalsIgnoreCase(ip)) {
                // get first ip from proxy ip
                final int index = ip.indexOf(',');
                if (index != -1) {
                    return ip.substring(0, index);
                } else {
                    return ip;
                }
            }
        } catch (final Exception e) {
            logger.error("getIpAddr() error", e);
        }
        return request.getRemoteAddr();
    }

    /**
     * 根据权重获取随机索引。
     *
     * @param key key
     * @param list Map数组
     * @return 随机索引
     */
    public static int getRdmIndex(String key, List<Map<String, Object>> list) {
        final List<Integer> rdmList = new ArrayList<>();
        Map<String, Object> map = null;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
            final int sum = Integer.parseInt(String.valueOf(map.get(key)));
            for (int j = 0; j < sum; j++) {
                rdmList.add(i);
            }
            // Collections.shuffle(rdmList,new
            // Random(UUID.randomUUID().getMostSignificantBits()));
        }
        Collections.shuffle(rdmList, new Random(UUID.randomUUID().getMostSignificantBits()));
        return rdmList.get((int) (rand.nextInt() * rdmList.size()));
    }

    /**
     * 获取签名串。
     *
     * @param param 签名字典
     * @return 签名串
     */
    public static synchronized String getSignStr(Map<String, String> param) {
        if (param == null) {
            return "";
        }
        final StringBuffer sbf = new StringBuffer();
        // for (final String key : param.keySet()) {
        // if (DataUtils.isNotNull(param.get(key))) {
        // sbf.append(param.get(key));
        // }
        // }
        for (final Map.Entry<String, String> entry : param.entrySet()) {
            String value = entry.getValue();
            if (DataUtils.isNotNull(value)) {
                sbf.append(value);
            }
        }
        return sbf.toString();
    }

    /**
     * 如果为空值‘null’,返回空字符串。
     *
     * @param str 输入字符串
     * @return 加工后的字符串
     */
    public static String getString(String str) {
        return (isNull(str) || str.equals("null")) ? "" : str;
    }

    /**
     * 生成UUID。
     *
     * @return UUID字符串
     */
    public static synchronized String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 从指定位置开始截取到指定位置内容。
     *
     * @param text 输入字符串
     * @param beginText 开始字符串
     * @param endText 结束字符串
     * @return 截取出的字符串
     */
    public static synchronized String getValue(String text, String beginText, String endText) {
        if (isNull(text) || text.indexOf(beginText) < 0 || text.indexOf(endText) < 0) {
            return "";
        }
        final String tmpStr = text.substring(text.indexOf(beginText) + beginText.length());
        return tmpStr.substring(0, tmpStr.indexOf(endText));
    }

    /**
     * 从指定位置开始截取到指定位置内容 分两次细化位置。
     *
     * @param text 输入字符串
     * @param beginTextA 开始字符串A
     * @param beginTextB 开始字符串B
     * @param endText 结束字符串
     * @return 截取出的字符串
     */
    public static synchronized String getValue(String text, String beginTextA, String beginTextB, String endText) {
        String tmpStr = text.substring(text.indexOf(beginTextA) + beginTextA.length());
        tmpStr = tmpStr.substring(tmpStr.indexOf(beginTextB) + beginTextB.length());
        return tmpStr.substring(0, tmpStr.indexOf(endText));
    }

    /**
     * 从指定未知开始截取 倒序查找截取开始和结束标志间的字符串。
     *
     * @param text 输入字符串
     * @param tag tag
     * @param start 开始字符串
     * @param end 结束字符串
     * @return 截取出的字符串
     */
    public static synchronized String getValueFromBack(String text, String tag, String start, String end) {
        String tmp = "";
        if (DataUtils.isNull(text) || text.indexOf(tag) < 0 || text.indexOf(start) < 0 || text.indexOf(end) < 0) {
            return tmp;
        }
        tmp = text.substring(0, text.indexOf(tag));
        tmp = tmp.substring(tmp.indexOf(start) + start.length());
        return tmp.substring(0, tmp.indexOf(end));
    }

    /**
     * 转换为Integer。
     *
     * @param obj 输入参数
     * @return Integer
     */
    public static Integer I$(Object obj) {
        return I$(obj, 0);
    }

    /**
     * 转换为Integer。
     *
     * @param obj 输入参数
     * @param defaultValue 默认值
     * @return Integer
     */
    public static Integer I$(Object obj, int defaultValue) {
        int tmp = 0;
        try {
            tmp = Integer.valueOf(String.valueOf(obj));
        } catch (final Exception e) {
            logger.error("DataUtils-I$ error", e);
            tmp = defaultValue;
        }
        return tmp;
    }

    /**
     * 判断字符串是否为2位小数double型数值。
     *
     * @param str 字符串
     * @return 判断结果
     */
    public static boolean isDouble2(String str) {
        if (isNull(str)) {
            return false;
        }
        try {
            Double.parseDouble(str);
        } catch (final Exception e) {
            logger.error("isDouble2 error", e);
            return false;
        }
        if (!(str.split("\\.").length > 1 && str.split("\\.")[1].length() <= 2)) {
            return false;
        }
        return true;
    }

    /**
     * 判断指定对象是否不为空。
     *
     * @param obj 指定对象
     * @return 判断结果
     */
    public static boolean isNotNull(Object obj) {
        if (obj == null || obj.toString().length() == 0 || obj.toString().equalsIgnoreCase("NULL")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断指定对象是否为空。
     *
     * @param obj 指定对象
     * @return 判断结果
     * @return
     */
    public static boolean isNull(Object obj) {
        if (obj == null || obj.toString().length() == 0 || obj.toString().equalsIgnoreCase("NULL")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否为数字(支持长数字验证)。
     *
     * @param str 字符串
     * @return 判断结果
     */
    public static boolean isNumeric(String str) {
        if (isNull(str)) {
            return false;
        }
        int length = str.length();
        int cycleTime = 0;
        String string = str;
        try {
            while (length > 18) {
                final String newStr = str.substring(18 * cycleTime, 18 * (cycleTime + 1));
                Long.parseLong(newStr);
                string = str.substring(18 * (cycleTime + 1));
                length = string.length();
                cycleTime++;
            }
            Long.parseLong(string);
        } catch (final Exception e) {
            logger.error("isNumeric error", e);
            return false;
        }
        return true;
    }

    /**
     * 生成指定长度数字。
     * 
     * @param len 长度
     * @return 生成字符串
     */
    public static synchronized String makeNum(int len) {
        final StringBuffer sbf = new StringBuffer();
        while (sbf.length() < len) {
            sbf.append(new Random().nextInt(10));
        }
        return sbf.toString();
    }

    /**
     * 生成扫码订单号。
     *
     * @return 扫码订单号
     */
    public static synchronized String makeOrder() {
        final StringBuffer sbf = new StringBuffer();
        sbf.append(new SimpleDateFormat("yyMMdd").format(new Date()));
        sbf.append(String.format("%019d", System.nanoTime()));
        return sbf.toString();
    }

    /**
     * 生成随机手机号 移动号码。
     *
     * @return 手机号
     */
    public static synchronized String makePhone() {
        final int[] arr = { 139, 138, 137, 136, 135, 134, 159, 150, 151, 158, 157, 188, 187, 152, 182, 147 };
        return arr[new Random().nextInt(arr.length)] + makeRdmCode(8);
    }

    /**
     * 随机生成6位数字验证码。
     *
     * @return 随机验证码
     */
    public static String makeRandom() {
        final String sources = "0123456789"; // 设置随机数取值范围
        final StringBuffer codeNum = new StringBuffer();
        for (int j = 0; j < 6; j++) {
            codeNum.append(sources.charAt(rand.nextInt(9)) + "");
        }
        return codeNum.toString();
    }

    /**
     * 产生len个不重复的数字 数字在0~len-1之间 数字以逗号隔开。
     *
     * @param size 最大数值
     * @param len 数值长度
     * @return 生成的数字列表
     */
    public static Object[] makeRdmArr(int size, int len) {
        // 默认：size > len
        final Set<Integer> set = new HashSet<>();
        while (set.toArray().length < len) {
            // set.add((int) (rand.nextInt() * size));
            set.add(rand.nextInt(size));
        }
        return set.toArray();
    }

    /**
     * 生产指定长度的数字串。
     *
     * @param length 长度
     * @return 生成的数字串
     */
    public static synchronized String makeRdmCode(int length) {
        final StringBuffer sbf = new StringBuffer();
        while (sbf.length() < length) {
            sbf.append(new Random().nextInt(10));
        }
        return sbf.toString();
    }

    /**
     * 生成指定长度的图形验证码。
     * 
     * @param length 长度
     * @return 生成的图形验证码
     */
    public static synchronized String makeRdmImageCode(int length) {
        final StringBuffer sbf = new StringBuffer();
        while (sbf.length() < length) {
            final int idx = (int) (BATH_STRING.length() * rand.nextInt());
            sbf.append(BATH_STRING.charAt(idx));
        }
        return sbf.toString();
    }

    /**
     * 产生随机数0~num。
     *
     * @param num 数字范围
     * @return 生成的随机数
     */
    public static int makeRdmNum(int num) {
        // return (int) (rand.nextInt() * num);
        return (int) (rand.nextInt(num));
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        final BigDecimal b1 = new BigDecimal(Double.toString(v1));
        final BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 将请求参数串(如：a=1&b=2&c=3)转换为map。
     *
     * @param requestParam 请求参数字符串
     * @param splitLimt 分隔数组个数
     * @return 解析出的map
     */
    public static Map<String, String> paramDataToMap(String requestParam, int splitLimt) {
        String[] arr = null;
        // 如果splitLimt不为空则分隔指定长度数据
        if (DataUtils.isNotNull(splitLimt)) {
            arr = requestParam.split("&", splitLimt);
        } else {
            arr = requestParam.split("&");
        }
        final Map<String, String> param = new HashMap<>();
        for (final String str : arr) {
            int equalStr = str.indexOf('=');
            if (equalStr < 0) {
                continue;
            }
            final String[] keys = str.split("=");
            if (keys[0].trim().length() > 0 && keys.length == 2) {
                param.put(keys[0], keys[1]);
            } else if (keys[0].trim().length() > 0 && keys.length > 2) {
                param.put(keys[0], str.substring(equalStr + 1));
            } else {
                param.put(keys[0], "");
            }
        }
        return param;
    }

    /**
     * 将参数map转换为参数串(如：a=1&b=2&c=3)。
     *
     * @param paramMap 参数map
     * @return 生成的参数串
     */
    public static String paramMapDataToString(Map<String, String> paramMap) {
        if (paramMap == null) {
            return "";
        }
        final StringBuffer sbf = new StringBuffer();
        // for (final String key : param.keySet()) {
        // if (isNull(key)) {
        // continue;
        // }
        // if (isNotNull(param.get(key))) {
        // sbf.append("&").append(key).append("=").append(param.get(key));
        // } else {
        // sbf.append("&").append(key).append("=").append("");
        // }
        // }
        for (final Map.Entry<String, String> entry : paramMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (isNull(key)) {
                continue;
            }
            if (isNotNull(value)) {
                sbf.append("&").append(key).append("=").append(value);
            } else {
                sbf.append("&").append(key).append("=").append("");
            }
        }
        if (sbf.length() > 0) {
            return sbf.substring(1);
        }
        return "";
    }

    /**
     * 随机生成ipv4地址。
     *
     * @return IPv4地址
     */
    public static String randomAddr() {
        final StringBuffer sbf = new StringBuffer();
        sbf.append((int) (rand.nextInt() * 255));
        sbf.append(".");
        sbf.append((int) (rand.nextInt() * 255));
        sbf.append(".");
        sbf.append((int) (rand.nextInt() * 255));
        sbf.append(".");
        sbf.append((int) (rand.nextInt() * 255));
        return sbf.toString();
    }

    /**
     * 将list map中值为null的替换为空字符串。
     *
     * @param list Map列表
     * @return 更新后的Map列表
     */
    public static List<Map<String, Object>> replaceListMapNullToString(List<Map<String, Object>> list) {
        if (list == null) {
            return list;
        }
        final List<Map<String, Object>> rtnList = new ArrayList<>();
        for (final Map<String, Object> map : list) {
            rtnList.add(replaceMapNullToString(map));
        }
        return rtnList;
    }

    /**
     * 将 map中值为null的替换为空字符串。
     *
     * @param map 原始map
     * @return 更新后的map
     */
    public static Map<String, Object> replaceMapNullToString(Map<String, Object> map) {
        if (map == null) {
            return map;
        }
        final Map<String, Object> temp = new HashMap<>();
        // for (final String key : map.keySet()) {
        // temp.put(key, map.get(key) == null ? "" : map.get(key));
        // }
        for (final Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            temp.put(entry.getKey(), value == null ? "" : value);
        }
        return temp;
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(SCALE_VERIFY_MSG);
        }
        final BigDecimal b = new BigDecimal(Double.toString(v));
        final BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将对象转换为字符串。
     *
     * @param obj 输入对象
     * @return 转换后的字符串
     */
    public static String S$(Object obj) {
        return S$(obj, "");
    }

    /**
     * 将对象转换为字符串。
     *
     * @param obj 输入对象
     * @param defaultValue 默认值
     * @return 转换后的字符串
     */
    public static String S$(Object obj, String defaultValue) {
        if (obj == null || obj.toString().trim().length() == 0) {
            return defaultValue;
        } else {
            return String.valueOf(obj);
        }
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        final BigDecimal b1 = new BigDecimal(Double.toString(v1));
        final BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 从开始位置截取到指定子串。
     *
     * @param text 字符串
     * @param subText 目标子字符串
     * @return 截取出的字符串
     */
    public static synchronized String subEnd(String text, String subText) {
        return text.substring(0, text.indexOf(subText));
    }

    /**
     * 从指定子串截取字符串。
     *
     * @param text 字符串
     * @param subText 目标子字符串
     * @return 截取出的字符串
     */
    public static synchronized String subStart(String text, String subText) {
        return text.substring(text.indexOf(subText) + subText.length());
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static BigDecimal divForObj(Object v1, Object v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(SCALE_VERIFY_MSG);
        }
        final BigDecimal b1 = $B(v1);
        final BigDecimal b2 = $B(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入,最终化为百分比。
     *
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商的百分比
     */
    public static String percentForObj(Object v1, Object v2, int scale) {
        String percentVal = "";
        try {
            if (scale < 0) {
                throw new IllegalArgumentException(SCALE_VERIFY_MSG);
            }
            final BigDecimal b1 = $B(v1);
            final BigDecimal b2 = $B(v2);
            BigDecimal divVal = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
            DecimalFormat df = new DecimalFormat("0.00%");
            percentVal = df.format(divVal);
        } catch (Exception e) {
            percentVal = "0.00%";
        }
        return percentVal;
    }

    /**
     * 获取当前时刻的 yyyy-MM-dd HH:mm:ss 格式字符串。
     *
     * @return 字符串
     */
    public static String getDatetime() {
        return getDatetime(new Date());
    }

    /**
     * 获取指定时刻的 yyyy-MM-dd HH:mm:ss 格式字符串。
     *
     * @param dateTime 时刻
     * @return 字符串
     */
    public static String getDatetime(Date dateTime) {
        return dateTime != null ? new SimpleDateFormat(YYYYMMDD_HHMMSS).format(dateTime) : null;
    }

    /**
     * 获取指定时刻的 yyyy-MM-dd 格式字符串。
     *
     * @param dateTime 时刻
     * @return 字符串
     */
    public static String getDateYearMonthDay(Date dateTime) {
        return dateTime != null ? new SimpleDateFormat("yyyy-MM-dd").format(dateTime) : null;
    }

    /**
     * 计算时间差-返回INT（返回值以秒为单位）。
     *
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return 字符串
     */
    public static int getTimeDifferent(Date beginTime, Date endTime) {
        long diff = endTime.getTime() - beginTime.getTime();
        return (int) diff / 1000;
    }

    /**
     * 计算时间差-返回BigDecimal（返回值以秒为单位）。
     *
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return 字符串
     */
    public static BigDecimal getTimeDifferentB(Date beginTime, Date endTime) {
        long diff = endTime.getTime() - beginTime.getTime();
        return new BigDecimal(String.valueOf(diff / 1000));
    }
}
