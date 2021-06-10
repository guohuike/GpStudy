package com.ghk.study.customspring.v2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: GpView
 * @Package: com.ghk.study.customspring.v2.servlet.mvc
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/20 13:52
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpView {

    private File file;
    public GpView(String fileStr) {
        file = new File(fileStr);
    }

    public void rendor(HttpServletRequest request, HttpServletResponse response, Map<String,?> model) throws Exception{
        StringBuilder sb = new StringBuilder();
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");
        String line = null;
        while(null != (line = randomAccessFile.readLine())){
            line = new String(line.getBytes("iso-8859-1"),"UTF-8");
            Pattern pattern = Pattern.compile("￥\\{[^\\}]+\\}", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()){
                String paramName = matcher.group();
                paramName = paramName.replaceAll("￥\\{", "");
                paramName = paramName.replaceAll("}","");
                Object value = model.get(paramName);
                if(null == value) continue;
                line = matcher.replaceFirst(value.toString());
                pattern.matcher(line);
            }
            sb.append(line);
        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(sb.toString());
    }
}