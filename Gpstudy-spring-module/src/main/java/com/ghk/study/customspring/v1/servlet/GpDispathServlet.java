package com.ghk.study.customspring.v1.servlet;

import com.ghk.study.customspring.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.MessageFormat;
import java.util.*;

/**
 * @Title: GpDispathServlet
 * @Package: com.ghk.study.customspring.v1.servlet
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/10 17:30
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class GpDispathServlet extends HttpServlet {

    private Properties propertiesLoad = new Properties();

    private List<String> classNameList = new ArrayList<>();

    private Map<String,Object> ioc = new HashMap<>();

    private Map<String,Method> handlerMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.service(req, resp);
    }
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //6 根据url调用相应的handler
        System.out.println("=====根据url调用相应的handler=====");
        doHandlerMethod(req,resp);

    }
    /***
     * @author: huike.guo
     * @description: 根据url去获取对应的method,获取到了就执行,获取不到404
     * @date: 2021/5/11 16:21
     * @param [req, resp]
     * @return void
     */
    private void doHandlerMethod(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String reqUrl = requestURI.replace(contextPath, "").replaceAll("/+", "/");
        if(!handlerMap.containsKey(reqUrl)){
            try {
                resp.getWriter().write("404 ERROR!");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Method method = handlerMap.get(reqUrl);
        Map<String, Integer> indexParamMap = new HashMap<>();
        //处理参数
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if(annotation instanceof GpRequestParam){
                    if(!"".equals(((GpRequestParam) annotation).value())){
                        indexParamMap.put(((GpRequestParam) annotation).value().trim(),i);
                    }
                }
            }
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] paramObj = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            if(parameterTypes[i] == HttpServletRequest.class || parameterTypes[i] == HttpServletResponse.class){
                indexParamMap.put(parameterTypes[i].getName(),i);
            }
        }
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> paramEntity : parameterMap.entrySet()) {
            String key = paramEntity.getKey();
            if(!indexParamMap.containsKey(key))continue;
            Integer index = indexParamMap.get(key);
            paramObj[index] = Arrays.toString(paramEntity.getValue()).replaceAll("\\[|\\]","").replace("\\s","");
        }
        if(indexParamMap.containsKey(HttpServletRequest.class.getName())){
            Integer index = indexParamMap.get(HttpServletRequest.class.getName());
            paramObj[index] = req;
        }
        if(indexParamMap.containsKey(HttpServletResponse.class.getName())){
            Integer index = indexParamMap.get(HttpServletResponse.class.getName());
            paramObj[index] = resp;
        }
        try {
            String className = toLowCase(method.getDeclaringClass().getSimpleName());
            Object targetObj = ioc.get(className);
            method.invoke(targetObj,paramObj);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("=====加载配置文件=====");
        //1 加载配置文件
        loadProperties(config);
        //2 扫描注解bean
        System.out.println("=====扫描注解bean=====");
        doSacnner(propertiesLoad.getProperty("scanner.package"));
        //3 初始化IOC,并将bean缓存到IOC中
        System.out.println("=====初始化IOC,并将bean缓存到IOC中=====");
        initIocContainer();
        //4 将初始化好的实例进行DI注入
        System.out.println("=====将初始化好的实例进行DI注入=====");
        doAutowired();
        //5 进行MVC映射
        System.out.println("=====进行MVC映射=====");
        initHandlerMethod();
    }
    /***
     * @author: huike.guo
     * @description: 只处理IOC容器中被指定注解修饰的类,得到类的methods,
     * 然后进行获取方法上面的注释获取注释中的url.然后将url和method进行匹配存储
     * @date: 2021/5/11 16:18
     * @param []
     * @return void
     */
    private void initHandlerMethod() {
        for (Map.Entry<String, Object> entity : ioc.entrySet()) {
            Object value = entity.getValue();
            if(!value.getClass().isAnnotationPresent(GpController.class)) continue;
            String classUrl = "";
            if(value.getClass().isAnnotationPresent(GpRequestMapping.class)){
                GpRequestMapping annotation = value.getClass().getAnnotation(GpRequestMapping.class);
                classUrl = annotation.value();
            }
            for (Method method : value.getClass().getMethods()) {
                if(!method.isAnnotationPresent(GpRequestMapping.class))continue;
                String methodUrl = method.getAnnotation(GpRequestMapping.class).value();
                String reqUrl = ("/" + classUrl + "/" +  methodUrl).replaceAll("/+","/");
                System.out.println("====="+reqUrl+"====="+methodUrl);
                handlerMap.put(reqUrl,method);
            }

        }
    }
    /***
     * @author: huike.guo
     * @description: 通过反射得到已经存在IOC容器中的实体的属性,
     * 然后判断属性是否被指定的注入注解进行修饰,如果修饰的话,进行注入
     * @date: 2021/5/11 16:16
     * @param []
     * @return void
     */
    private void doAutowired() {
        if(CollectionUtils.isEmpty(ioc))return;
        for (Map.Entry<String, Object> entity : ioc.entrySet()) {
            Object value = entity.getValue();
            for (Field declaredField : value.getClass().getDeclaredFields()) {
                declaredField.setAccessible(true);
                if(declaredField.isAnnotationPresent(GpAutowired.class)){
                    String instanceName = declaredField.getName();
                    GpAutowired annotation = declaredField.getAnnotation(GpAutowired.class);
                    if(!"".equals(annotation.value().trim())){
                        instanceName = annotation.value().trim();
                    }
                    try {
                        declaredField.set(value,ioc.get(instanceName));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    /***
     * @author: huike.guo
     * @description: 根据找到的类的路径通过反射进行实例化,并且存储到对应的IOC容器中
     * 只需要处理被指定注释注解的类
     * @date: 2021/5/11 16:15
     * @param
     * @return void
     */
    private void initIocContainer() {
        if(CollectionUtils.isEmpty(classNameList)) return;
        for (String className : classNameList) {
            try{
                Class<?> aClass = Class.forName(className);
                if(aClass.isAnnotationPresent(GpController.class)){
                    Object obj = aClass.newInstance();
                    String simpleName = aClass.getSimpleName();
                    String lowCaseClassName = toLowCase(simpleName);
                    ioc.put(lowCaseClassName,obj);
                }else if(aClass.isAnnotationPresent(GpService.class)){
                    Object obj = aClass.newInstance();
                    String simpleName = aClass.getSimpleName();
                    String lowCaseClassName = toLowCase(simpleName);
                    //获取service里面自定义的名字
                    String value = aClass.getAnnotation(GpService.class).value();
                    if(!"".equals(value)){
                        lowCaseClassName = value;
                    }
                    existInstance(lowCaseClassName);
                    ioc.put(lowCaseClassName,obj);
                    //获取实例的接口,将接口和实例信息进行一一对应存储
                    Class<?>[] interfaces = aClass.getInterfaces();
                    if(interfaces.length > 0){
                        for (Class<?> anInterface : interfaces) {
                            existInstance(anInterface.getName());
                            ioc.put(anInterface.getName(),obj);
                        }
                    }
                }else{
                    continue;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    private void existInstance(String instanceName) {
        if(ioc.containsKey(instanceName)){
            throw new RuntimeException(instanceName+"已存在,请重新命名!!");
        }
    }

    private String toLowCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0]+=32;
        return new String(chars);
    }
    /***
     * @author: huike.guo
     * @description: 根据获取到的扫描的包路径进行扫描.class文件结尾的类
     * 用递归去寻找不是文件夹的文件
     * @date: 2021/5/11 16:12
     * @param
     * @return void
     */
    private void doSacnner(String scannerPackage) {
        URL resource = this.getClass().getClassLoader().getResource("/" + scannerPackage.replace(".", "/"));
        File file = new File(resource.getFile());
        File[] files = file.listFiles();
        for (File file1 : files) {
            if(file1.isDirectory()){
                doSacnner(scannerPackage+"."+file1.getName());
            }else{
                if(!file1.getName().endsWith(".class")){
                    continue;
                }
                //包名加类名
                String className = scannerPackage+"."+file1.getName().replace(".class","");
                classNameList.add(className);
            }
        }
    }

    /***
     * @author: huike.guo
     * @description: 根据Serlet的servletContext获取web.xml中配置的配置文件中的参数
     * @date: 2021/5/11 16:11
     * @param
     * @return
     */
    private void loadProperties(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try{
            propertiesLoad.load(resourceAsStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != resourceAsStream){
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}