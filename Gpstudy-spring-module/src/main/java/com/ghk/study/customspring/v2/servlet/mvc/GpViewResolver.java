package com.ghk.study.customspring.v2.servlet.mvc;

import org.springframework.util.StringUtils;

import java.io.File;
import java.net.URL;

/**
 * @Title: GpViewResolver
 * @Package: com.ghk.study.customspring.v2.servlet.mvc
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/20 11:01
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpViewResolver {
    private File file;
    private final static String DEFAULT_FILE_NAME = ".html";
    public GpViewResolver(File listFile) {
        this.file = listFile;
    }

    public GpView resolveViewName(String viewName) {
        if(StringUtils.isEmpty(viewName)) return null;
        String path = file.getPath();
        viewName = viewName.endsWith(DEFAULT_FILE_NAME) ? viewName : (viewName + DEFAULT_FILE_NAME);
        String substring = path.substring(path.lastIndexOf("\\")+1, path.length());
        if(viewName.equals(substring)){
            return new GpView(file.getPath());
        }
        return null;
    }
}