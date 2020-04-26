package cn.l.x.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;

import cn.l.x.bean.Term;

public class HtmlUtil {

    // 单个字符的正则表达式
    private static final String singlePattern = "[0-9|a-f|A-F]";

    // 4个字符的正则表达式
    private static final String pattern = singlePattern + singlePattern + singlePattern + singlePattern;

    /**
     * 从html源码(字符串)中去掉标题
     * 
     * @param htmlSource
     * @return
     */
    public static String getCourseNameByHtml(String htmlSource) {
        List<String> list = new ArrayList<String>();
        String title = "";

        // Pattern pa = Pattern.compile("<title>.*?</title>", Pattern.CANON_EQ);也可以
        Pattern pa = Pattern.compile("<title>.*?</title>");// 源码中标题正则表达式
        Matcher ma = pa.matcher(htmlSource);
        while (ma.find())// 寻找符合el的字串
        {
            list.add(ma.group());// 将符合el的字串加入到list中
        }
        for (int i = 0; i < list.size(); i++) {
            title = title + list.get(i);
        }
        return outTag(title);
    }

    /**
     * 去掉html源码中的标签
     * 
     * @param s
     * @return
     */
    public static String outTag(String s) {
        return s.replaceAll("<.*?>", "");
    }

    public static List<Term> getTermByHtml(String html) {
        String reg = "window.termInfoList = \\[[^`]*?\\]";
        Matcher m = Pattern.compile(reg).matcher(html);
        String info = null;
        while (m.find()) {
            // System.out.println(m.end());
            // System.out.println(m.group(0));
            info = m.group(0);
        }
        System.out.println(info);

        String[] split = info.split("=");
        List<Term> parseArray = JSON.parseArray("[" + split[1].substring(2, split[1].length() - 1) + "]", Term.class);
        return parseArray;
    }

    public static String removeHeadAndLast(String sendPost) {
        String reg = "var[^`]*;dwr";
        Matcher m = Pattern.compile(reg).matcher(sendPost);
        String info = null;
        while (m.find()) {
            info = m.group(0);
        }
        return info.substring(0, info.length() - 3);
    }

    /**
     * 字符串中，所有以 \\u 开头的UNICODE字符串，全部替换成汉字
     * 
     * @param strParam
     * @return
     */
    public static String unicodeToCn(String str) {
        // 用于构建新的字符串
        StringBuilder sb = new StringBuilder();
        // 从左向右扫描字符串。tmpStr是还没有被扫描的剩余字符串。
        // 下面有两个判断分支：
        // 1. 如果剩余字符串是Unicode字符开头，就把Unicode转换成汉字，加到StringBuilder中。然后跳过这个Unicode字符。
        // 2.反之， 如果剩余字符串不是Unicode字符开头，把普通字符加入StringBuilder，向右跳过1.
        int length = str.length();
        for (int i = 0; i < length;) {
            String tmpStr = str.substring(i);
            if (isStartWithUnicode(tmpStr)) { // 分支1
                sb.append(ustartToCn(tmpStr));
                i += 6;
            } else { // 分支2
                sb.append(str.substring(i, i + 1));
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * 把 \\u 开头的单字转成汉字，如 \\u6B65 -> 步
     * 
     * @param str
     * @return
     */
    private static String ustartToCn(final String str) {
        StringBuilder sb = new StringBuilder().append("0x").append(str.substring(2, 6));
        Integer codeInteger = Integer.decode(sb.toString());
        int code = codeInteger.intValue();
        char c = (char) code;
        return String.valueOf(c);
    }

    /**
     * 字符串是否以Unicode字符开头。约定Unicode字符以 \\u开头。
     * 
     * @param str
     *            字符串
     * @return true表示以Unicode字符开头.
     */
    private static boolean isStartWithUnicode(final String str) {
        if (null == str || str.length() == 0) {
            return false;
        }
        if (!str.startsWith("\\u")) {
            return false;
        }
        // \u6B65
        if (str.length() < 6) {
            return false;
        }
        String content = str.substring(2, 6);
        boolean isMatch = Pattern.matches(pattern, content);
        return isMatch;
    }

    public static List<String> getTsList(String sendGet) {
        String reg = ",[^`]*?\\.ts";
        List<String> tsList = new ArrayList<>();
        Matcher m = Pattern.compile(reg).matcher(sendGet);
        System.out.println(m.groupCount());
        while (m.find()) {
            String info = m.group(0);
            tsList.add(info.substring(1));
            // System.out.println(info);
            // String allUrl=baseUrl+"/"+info.substring(1);
            // HttpRequest.downLoad(allUrl);
        }
        return tsList;

    }
}
