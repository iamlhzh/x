/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import cn.l.x.bean.AreaEntity;
import cn.l.x.dao.AreaDao;

@Controller
@RequestMapping("/add")
public class AddAreaCodeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddAreaCodeController.class);

    /**
     * 定义 applicationContext。
     */
    @Autowired
    private ApplicationContext applicationContext;
    /**
     * 
     */
    @Autowired
    private AreaDao areaDao;

    // 文件下载相关代码
    @RequestMapping("/addAreaCode")
    public String addAreaCode(HttpServletRequest request, HttpServletResponse response, String filePath) throws Exception {
        List<AreaEntity> list = getJsonData(filePath);
        for (int k = 0; k < list.size(); k = k + 5000) {
            List<AreaEntity> subList = new ArrayList<>();
            if ((k + 5000) < list.size()) {
                subList = list.subList(k, k + 5000);
            } else {
                subList = list.subList(k, list.size());
            }
            areaDao.addArea(subList);
        }
        return filePath;
    }

    /**
     * 获取json文件中的配置数据。
     *
     * @author sws 2018年12月20日 下午3:38:43
     * @param configPath 参数
     * @return jsonData
     */
    public List<AreaEntity> getJsonData(String configPath) {
        String jsonData = "";
        try {
            final Resource resource = applicationContext.getResource(configPath);
            final InputStream inputStream = resource.getInputStream();
            jsonData = jsonRead(inputStream);
            // LOGGER.info(jsonData);
        } catch (final Exception e) {
            // e.printStackTrace();
            LOGGER.error("getJsonData出现异常", e);
        }
        // List<AreaEntity> areaTreeList = JSONObject.parseArray(jsonData,
        // AreaEntity.class);
        List<AreaEntity> areaList = treeToList(JSONObject.parseArray(jsonData, AreaEntity.class), 0);
        return areaList;
    }

    private String jsonRead(InputStream inputStream) {
        // Scanner scanner = null;
        final StringBuilder buffer = new StringBuilder();
        try (Scanner scanner = new Scanner(inputStream, "utf-8")) {
            // scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
        } catch (final Exception e) {
            LOGGER.error("jsonRead出现异常", e);
            // 不处理本异常
            // } finally {
            // if (scanner != null) {
            // scanner.close();
            // }
        }
        return buffer.toString();
    }

    public List<AreaEntity> treeToList(List<AreaEntity> treelist, Integer level) {
        level++;
        List<AreaEntity> resultList = new ArrayList<>();
        for (AreaEntity area : treelist) {
            area.setLevel(level);
            changeAreaId(area);
            List<AreaEntity> childrenList = area.getChildren();
            resultList.add(area);
            if (!CollectionUtils.isEmpty(childrenList)) {
                resultList.addAll(treeToList(childrenList, level));
                area.setChildren(null);
            }
        }
        return resultList;
    }

    private void changeAreaId(AreaEntity area) {
        String id = area.getCode();
        String pid = area.getPcode();
        area.setCode(id + "000000");
        area.setPcode(pid + "000000");

    }

}
