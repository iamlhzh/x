/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.l.x.bean.Result;

@Controller
@RequestMapping("/upLoad")
public class FileUploadController {

    @Value("${file.baseFileFolder}")
    private String baseFileFolder;

    @ResponseBody
    @PostMapping(value = "/upLoadFile")
    public Result uploadFile(@RequestParam("fileDirectory") String fileDirectory, @RequestParam("filename") MultipartFile file) {
        Result rst = new Result();
        if (file.isEmpty()) {
            rst.setCode("003");
            rst.setMsg("未接收到任何文件");
        } else {
            System.out.println(fileDirectory);
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getSize());
        }
        try {
            String filePath = System.getProperty("user.dir");
            File path = new File(filePath);
            File baseDirectory = new File(path, baseFileFolder);
            File toDirectory = new File(baseDirectory, fileDirectory);

            System.out.println(toDirectory.getAbsolutePath());
            System.out.println(toDirectory.exists());
            boolean existDircetory = false;
            if (toDirectory.exists()) {
                existDircetory = true;
            } else {
                existDircetory = toDirectory.mkdirs();
            }
            if (existDircetory) {
                File saveFile = new File(toDirectory, file.getOriginalFilename());
                file.transferTo(saveFile);
                rst.setMsg("文件上传成功");
            }
        } catch (Exception e) {
            rst.setCode("008");
            rst.setMsg("文件上传失败");
        }
        return rst;
    }

    @ResponseBody
    @PostMapping(value = "/upLoadFileList")
    public Result uploadFileList(@RequestParam("fileDirectory") String fileDirectory, @RequestParam("fileList") MultipartFile[] fileList) {
        Result rst = new Result();
        if (Array.getLength(fileList) == 0) {
            rst.setCode("003");
            rst.setMsg("未接收到任何文件");
        } else {
            System.out.println(fileDirectory);
            System.out.println(fileList.length);
        }
        try {
            String filePath = System.getProperty("user.dir");
            File path = new File(filePath);
            File baseDirectory = new File(path, baseFileFolder);
            File toDirectory = new File(baseDirectory, fileDirectory);

            System.out.println(toDirectory.getAbsolutePath());
            System.out.println(toDirectory.exists());
            boolean existDircetory = false;
            if (toDirectory.exists()) {
                existDircetory = true;
            } else {
                existDircetory = toDirectory.mkdirs();
            }
            if (existDircetory) {
                for (MultipartFile file : fileList) {
                    File saveFile = new File(toDirectory, file.getOriginalFilename());
                    file.transferTo(saveFile);
                }
                rst.setMsg("文件上传成功");
            }
        } catch (Exception e) {
            rst.setCode("008");
            rst.setMsg("文件上传失败");
            System.out.println(e);
        }
        return rst;
    }

    // @RequestMapping("/upLoadFile")
    // public void uploadFile(@RequestParam File file) throws IOException {
    // System.out.println(1111111);
    // // InputStream in = file.getInputStream();
    // // byte[] data = null;
    // // try {
    // // ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
    // // byte[] buff = new byte[in.available()];
    // // int rc = 0;
    // // while ((rc = in.read(buff, 0, 100)) > 0) {
    // // swapStream.write(buff, 0, rc);
    // // }
    // // data = swapStream.toByteArray();
    // // } catch (IOException e) {
    // // e.printStackTrace();
    // // } finally {
    // // if (in != null) {
    // // try {
    // // in.close();
    // // } catch (IOException e) {
    // // e.printStackTrace();
    // // }
    // // }
    // // }
    // // String str = new String(Base64.encodeBase64(data));
    // // //下面就存数据库啦
    // // service.update（id,str);
    // }

    // 文件下载相关代码
    @RequestMapping("/downfileByAbsolutePath")
    public String downfileByAbsolutePath(String fileAbsolutePath, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ip = getIpAddr(request);
        System.out.println(ip);
        if (fileAbsolutePath != null) {
            // 设置文件路径
            File file = new File(fileAbsolutePath);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.setHeader("Content-Length", "" + file.length());
                // response.addHeader("Content-Disposition", "attachment;fileName=" +
                // fileName);// 设置文件名
                //
                response.setContentType("multipart/form-data;charset=UTF-8");// 也可以明确的设置一下UTF-8，测试中不设置也可以。
                response.setHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("GB2312"), "ISO-8859-1"));
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("下载成功");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载成功";
    }

    // 文件下载相关代码
    @RequestMapping("/downfile")
    public String downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ip = getIpAddr(request);
        System.out.println(ip);
        String filePath = System.getProperty("user.dir");
        File path = new File(filePath);
        if (!path.exists()) {
            path = new File("");
        }
        if (fileName != null) {
            // 设置文件路径
            File file = new File(path.getAbsolutePath(), baseFileFolder + fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.setHeader("Content-Length", "" + file.length());
                // response.addHeader("Content-Disposition", "attachment;fileName=" +
                // fileName);// 设置文件名
                // response.setContentType("multipart/form-data;charset=UTF-8");也可以明确的设置一下UTF-8，测试中不设置也可以。
                response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("GB2312"), "ISO-8859-1"));
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("下载成功");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载成功";
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
