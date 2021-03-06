/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.service.impl;

import cn.l.x.base.Result;
import cn.l.x.bean.*;
import cn.l.x.dictionary.Messages;
import cn.l.x.service.CrawlerService;
import cn.l.x.utils.DataUtils;
import cn.l.x.utils.HtmlUtil;
import cn.l.x.utils.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.util.*;
import java.util.Map.Entry;

@Service
public class CrawlerServiceImpl implements CrawlerService {

    @Value("${crawler.baseHostUrl}")
    private String baseHostUrl;
    /**
     * 临时文件存放处
     */
    @Value("${crawler.baseTempFolder}")
    private  String baseTempFolder ;
    /**
     * 存放文件处
     */
    @Value("${crawler.baseFileFolder}")
    private String baseFileFolder ;

    private static final Integer limitNum = 50;

    private static final String chineseParentheses="（";

//    private  static String ffmpegPath = "/home/pan/ffmpeg/linux/ffmpeg-4.2.2-amd64-static/ffmpeg";

    @Value("${crawler.ffmpeg.path}")
    private  String ffmpegPath;

    @Value("${crawler.server.type}")
    private  String serverType;
    // = "https://www.icourse163.org/course/PKU-1002530002";

    private final static String  WINDOWS_SERVER="windows";

    private final static String  LINUX_SERVER="linux";


    @Override
    public Result<Course> getCourseInfoBySchoolCourseId(String schoolCourseId) {
        Result<Course> rst = new Result<>();
        Course c = new Course();
        String url = baseHostUrl + "course/" + schoolCourseId;
        // 获取网页源码
        String html = HttpRequest.GetHTML(url);
        if (DataUtils.isNull(html)) {
            rst.setCodeMsg(Messages.E0001);
        }
        if (rst.isSucceeded()) {
            // 获取课程名称
            String courseName = HtmlUtil.getCourseNameByHtml(html);
            if (DataUtils.isNotNull(courseName)) {
                c.setCourseName(courseName.replaceAll("\\(", chineseParentheses).replaceAll("\\)", "）").replaceAll(" ", "_"));
            }
            // 获取课程的信息；可能有多个但是他们的termId是一样的
            List<Term> termList = HtmlUtil.getTermByHtml(html);
            if (!CollectionUtils.isEmpty(termList)) {
                c.setTermList(termList);
                for (Term term : termList) {
                    if (DataUtils.isNotNull(term)) {
                        c.setId(term.getId());
                        if (DataUtils.isNotNull(c.getId())) {
                            break;
                        }
                    }
                }
            }
        }
        rst.setObj(c);
        return rst;
    }

    @Override
    public Result<Course> getCourseDetailByCourse(Course course) throws ScriptException {
        Result<Course> rst = new Result<>();
        String  tid=course.getId();
        String sendPost = null;
        if (DataUtils.isNotNull(tid)) {
            String url = "https://www.icourse163.org/dwr/call/plaincall/CourseBean.getLastLearnedMocTermDto.dwr";
            String param = "callCount=1&scriptSessionId=${scriptSessionId}190&httpSessionId=b2dc9c47194e4e97a711b7bde5e281b7&c0-scriptName=CourseBean&c0-methodName=getLastLearnedMocTermDto&c0-id=0&c0-param0=number:"
                    + tid + "&batchId=1585985756309";
            sendPost = HttpRequest.sendPost(url, param);
        } else {
            rst.setCodeMsg(Messages.E0003);
        }
        Course c = null;
        if (DataUtils.isNotNull(sendPost)) {
            String strNeed = HtmlUtil.removeHeadAndLast(sendPost);
            // System.out.println(strNeed);
            // List<Unit> bizList = getBizIdList(strNeed);
            //
            // List<Unit> bizList = new ArrayList<>();
            // System.out.println(unicodeToCn(data));
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            engine.eval(HtmlUtil.unicodeToCn(strNeed));
            // javax.script.Invocable 是一个可选的接口
            // 检查你的script engine 接口是否已实现!
            // 注意：JavaScript engine实现了Invocable接口
            // Invocable inv = (Invocable) engine;
            // 获取我们想调用那个方法所属的js对象 8
            Object obj = engine.get("s0");
            // System.out.println(JSON.toJSONString(obj));
            c = JSON.parseObject(JSON.toJSONString(obj), Course.class);
            c.setTermList(course.getTermList());
            c.setCourseName(course.getCourseName());
            rst.setObj(c);
        } else {
            rst.setCodeMsg(Messages.E0003);
        }
        return rst;
    }

    @Override
    public Result<Course> downloadFileByCourse(Course course) {
        File baseDirectory;
        File tempDirectory;
        if(WINDOWS_SERVER.equals(serverType)){
            String filePath = System.getProperty("user.dir");
            System.out.println(filePath);
            File path = new File(filePath);
            System.out.println(path.getAbsolutePath());
            if (!path.exists()) {
                path = new File("");
            }
            // 设置文件路径
            baseDirectory = new File(path.getAbsolutePath(), baseFileFolder);
            tempDirectory = new File(path.getAbsolutePath(), baseTempFolder);
        }else {
            // 设置文件路径
            baseDirectory = new File(baseFileFolder);
            tempDirectory = new File(baseTempFolder);
        }
        File courseDirectory = new File(baseDirectory, course.getCourseName());
        if(!tempDirectory.exists()){
            tempDirectory.mkdirs();
        }
        if (!courseDirectory.exists()) {
            courseDirectory.mkdirs();
        }
        // 一门课程的所有章节
        Map<String, Chapter> chapters = course.getChapters();
        Set<Entry<String, Chapter>> entrySet = chapters.entrySet();
        Iterator<Entry<String, Chapter>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            // 一个章节
            Chapter chapter = iterator.next().getValue();
            chapter.setName(chapter.getName().replaceAll(" ", "_").replaceAll("\\(", chineseParentheses).replaceAll("\\)", "）"));
            File toDirectory = new File(courseDirectory, chapter.getName());
            if (!toDirectory.exists()) {
                toDirectory.mkdirs();
            }
            // System.out.println(chapter);
            // 一个章节的所有课
            Map<String, Lesson> lessons = chapter.getLessons();
            Set<Entry<String, Lesson>> lessonSet = lessons.entrySet();
            Iterator<Entry<String, Lesson>> lessonIterator = lessonSet.iterator();
            while (lessonIterator.hasNext()) {
                Entry<String, Lesson> next = lessonIterator.next();
                // 一节课
                Lesson value = next.getValue();
                // System.out.println(value);
                // 一节课的所有组成单元(视频和ppt)
                Map<String, Unit> units = value.getUnits();
                Set<Entry<String, Unit>> unitSet = units.entrySet();
                Iterator<Entry<String, Unit>> unitIterator = unitSet.iterator();
                while (unitIterator.hasNext()) {
                    Entry<String, Unit> unitEntry = unitIterator.next();
                    // 一个小的单元
                    Unit unit = unitEntry.getValue();
                    // 视频文件下载
                    if ("1".equals(unit.getContentType())) {
                        VideoSignDto videoSignDto = getVideoSignDtoByUnitId(unit.getId());
                        if (videoSignDto != null) {
                            String videoUrl = "https://vod.study.163.com/eds/api/v1/vod/video";
                            String param = "videoId=" + videoSignDto.getVideoId() + "&signature=" + videoSignDto.getSignature() + "&clientType=1";
                            String sendPost = HttpRequest.sendPost(videoUrl, param);
                            System.out.println("返回结果是:" + sendPost);
                            if (!"".equals(sendPost)) {
                                // PostResult postResult=JSON.parseObject(sendPost, PostResult.class);
                                JSONObject parsePostResult = JSON.parseObject(sendPost);
                                Object object = parsePostResult.get("result");
                                JSONObject parseResult = JSON.parseObject(object.toString());
                                Object videos = parseResult.get("videos");
                                System.out.println(parseResult.get("name"));
                                String videoName = parseResult.get("name").toString().replaceAll(" ", "_").replaceAll("\\(", chineseParentheses).replaceAll("\\)", "）");
                                List<Video> parseArray = JSON.parseArray(videos.toString(), Video.class);
                                boolean flag = false;
                                List<String> tsList = new ArrayList<>();
                                String baseUrl = null;
                                for (Video video : parseArray) {
                                    baseUrl = video.getVideoUrl().substring(0, video.getVideoUrl().lastIndexOf("/"));
                                    System.out.println(video.getVideoUrl());
                                    if (!flag && video.getQuality() == 1) {
                                        flag = true;
                                        System.out.println("-----------------------------------------------------------------------------");
                                        String sendGet = HttpRequest.sendGet(video.getVideoUrl(), "");
                                        // System.out.println(sendGet);
                                        System.out.println(baseUrl);
                                        tsList = HtmlUtil.getTsList(sendGet);
                                        break;
                                    } else if (!flag && video.getQuality() == 2) {
                                        flag = true;
                                        System.out.println("-----------------------------------------------------------------------------");
                                        String sendGet2 = HttpRequest.sendGet(video.getVideoUrl(), "");
                                        // System.out.println(sendGet);
                                        System.out.println(baseUrl);
                                        tsList = HtmlUtil.getTsList(sendGet2);
                                        break;
                                    } else if (!flag && video.getQuality() == 3) {
                                        flag = true;
                                        System.out.println("-----------------------------------------------------------------------------");
                                        String sendGet3 = HttpRequest.sendGet(video.getVideoUrl(), "");
                                        // System.out.println(sendGet);
                                        System.out.println(baseUrl);
                                        tsList = HtmlUtil.getTsList(sendGet3);
                                        break;
                                    } else if (!flag && video.getQuality() == 4) {
                                        flag = true;
                                        System.out.println("-----------------------------------------------------------------------------");
                                        String sendGet4 = HttpRequest.sendGet(video.getVideoUrl(), "");
                                        // System.out.println(sendGet);
                                        System.out.println(baseUrl);
                                        tsList = HtmlUtil.getTsList(sendGet4);
                                        break;
                                    }

                                }
                                // 去下载ts文件
                                File file = new File(toDirectory, videoName);
                                if (!file.exists()) {
                                    toDownLoadTs(baseUrl, tsList,tempDirectory, toDirectory, videoName);
                                }
                            }
                        }
                    }
                }
            }

        }
        return null;
    }

    private  void toDownLoadTs(String baseUrl, List<String> tsList, File tempDirectory, File toDirectory, String videoName) {
        List<String> filePaths = new ArrayList<>();
        for (String tsStr : tsList) {
            String allUrl = baseUrl + "/" + tsStr;
            File tsFile = new File(tempDirectory, tsStr);
            if (!tsFile.exists()) {
                HttpRequest.downLoad(allUrl, tsFile);
                filePaths.add(tsFile.getAbsolutePath());
            }
        }
        toMergeAllFile(filePaths, toDirectory, videoName);
    }

    private  void toMergeAllFile(List<String> filePaths, File toDirectory, String videoName) {
        if (filePaths.size() <= limitNum) {
            toCombine(toDirectory, filePaths, videoName);
        } else {
            List<String> nowPaths = new ArrayList<>();
            for (int i = 0; i < filePaths.size(); i = i + limitNum) {
                List<String> sub = null;
                if ((i + limitNum) < filePaths.size()) {
                    sub = filePaths.subList(i, i + limitNum);
                } else {
                    sub = filePaths.subList(i, filePaths.size());
                }
                String path = toCombine(toDirectory, sub, UUID.randomUUID().toString() + ".ts");
                nowPaths.add(path);
            }
            if (!CollectionUtils.isEmpty(nowPaths)) {
                toMergeAllFile(nowPaths, toDirectory, videoName);
            }
        }

    }

    private  String toCombine(File toDirectory, List<String> sub, String fileName) {
        StringBuilder sb = new StringBuilder();
        File file = new File(toDirectory, fileName.replace(" ", "_"));
        if (file.exists()) {
            file.delete();
        }
        sb.append(ffmpegPath);
        sb.append(" -loglevel quiet ");
        sb.append(" -i ");
        sb.append("\"concat:");
        for (String tsStr : sub) {
            sb.append(tsStr);
            sb.append("|");
        }
        sb.append("\" -c copy ").append(file.getAbsolutePath().replace(" ", "_"));
        Process p = null;
        try {
            if("linux".equals(serverType)){
                p = Runtime.getRuntime().exec(new String[]{"sh", "-c", sb.toString()});
            }else{
                p = Runtime.getRuntime().exec(sb.toString());
            }

            p.waitFor();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (p == null) {
                p.destroy();
            }
            p = null;
        }
        for (String tsStr : sub) {
            File delFile = new File(tsStr);
            if (delFile.exists()) {
                boolean delete = delFile.delete();
                System.out.println(delete);
            }
        }
        return file.getAbsolutePath();
    }

    private VideoSignDto getVideoSignDtoByUnitId(String id) {
        String url = "https://www.icourse163.org/web/j/resourceRpcBean.getResourceToken.rpc?csrfKey=b2dc9c47194e4e97a711b7bde5e281b7";
        String param = "bizId=" + id + "&bizType=1&contentType=1";
        String cookie="NTESSTUDYSI=b2dc9c47194e4e97a711b7bde5e281b7";
        String sendPost = HttpRequest.sendMyPost(url, param,cookie);
        VideoSignDto videoSignDto = null;
        if (sendPost != "") {
            // System.out.println(sendPost);
            PostResult parseObject = JSON.parseObject(sendPost, PostResult.class);
            // if (parseObject.getCode() == 0) {
            //
            // }
            System.out.println(parseObject);
            // System.out.println(parseObject.getResult());
            if (parseObject.getCode() == 0) {
                VideoResult result = JSON.parseObject(parseObject.getResult().toString(), VideoResult.class);
                // System.out.println(result.getVideoSignDto().toString());
                videoSignDto = result.getVideoSignDto();
            }
        }
        return videoSignDto;
    }

}
