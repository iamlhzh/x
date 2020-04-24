package cn.l.x.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;

import cn.l.x.bean.Term;

public class HtmlUtil {
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
}
