/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.controller;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.l.x.bean.FileInfo;
import cn.l.x.bean.Result;

@Controller
@RequestMapping("/fileList")
public class FileListController {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    // 文件下载相关代码
    @ResponseBody
    @RequestMapping("/getFileList")
    public Result getFileList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result rst = new Result();
        String ip = getIpAddr(request);
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
        // 设置文件路径
        File baseFile = new File(path.getAbsolutePath(), uploadFolder);
        FileInfo resultFileInfo = getAllFileTree(baseFile);
        List<FileInfo> returnList = new ArrayList<>();
        returnList.add(resultFileInfo);
        rst.setObj(returnList);

        return rst;
    }

    private FileInfo getAllFileTree(File baseFile) {
        FileInfo resultFileInfo = new FileInfo();
        resultFileInfo.setFile(baseFile);
        resultFileInfo.setFileName(baseFile.getName());
        resultFileInfo.setIsDirectory(baseFile.isDirectory());
        resultFileInfo.setFileSize(baseFile.length());
        resultFileInfo.setFileUrl(baseFile.getPath());
        if (resultFileInfo.getIsDirectory()) {
            getSubFile(resultFileInfo);
        }
        return resultFileInfo;
    }

    private void getSubFile(FileInfo resultFileInfo) {
        File[] listFiles = resultFileInfo.getFile().listFiles();
        List<FileInfo> fileList = new ArrayList<>();
        for (File file : listFiles) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFile(file);
            fileInfo.setFileName(file.getName());
            fileInfo.setIsDirectory(file.isDirectory());
            fileInfo.setFileSize(file.length());
            fileInfo.setFileAbsolutePath(file.getAbsolutePath());
            fileList.add(fileInfo);
        }
        resultFileInfo.setChildrenFileList(fileList);
        for (FileInfo fileInfo : resultFileInfo.getChildrenFileList()) {
            if (fileInfo.getIsDirectory()) {
                getSubFile(fileInfo);
            }
        }
    }

    private String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

}
