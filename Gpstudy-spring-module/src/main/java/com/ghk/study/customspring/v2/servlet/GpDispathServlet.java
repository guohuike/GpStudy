package com.ghk.study.customspring.v2.servlet;

import com.ghk.study.customspring.v2.servlet.annotation.*;
import com.ghk.study.customspring.v2.servlet.core.GpApplicationContext;
import com.ghk.study.customspring.v2.servlet.mvc.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    //private Map<String,Method> handlerMap = new HashMap<>();
    private List<GpHandlerMapping> handlerMappings = new ArrayList<>();

    private Map<GpHandlerMapping, GpHandlerAdapter> handlerAdapterMap = new HashMap<>();

    private List<GpViewResolver> viewResolvers = new ArrayList<>();

    private GpApplicationContext gpApplicationContext;

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
        try{
            doDispatch(req,resp);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //1.得到handlerMapping
        GpHandlerMapping mappedHandler = getHandler(request);
        if(null == mappedHandler){
            processDispatchResult(request,response,new GpModelAndView("404"));
        }
        //得到handlerAdapter
        GpHandlerAdapter ha = getHandlerAdapter(mappedHandler);
        //通过handlerAdapter调用相应的handler的method
        GpModelAndView gpModelAndView = ha.handler(request,response,mappedHandler);
        //将结果集进行转换
        processDispatchResult(request,response,gpModelAndView);
    }

    private GpHandlerAdapter getHandlerAdapter(GpHandlerMapping mappedHandler) {
        if(!handlerAdapterMap.containsKey(mappedHandler)) return null;
        return handlerAdapterMap.get(mappedHandler);
    }

    private void processDispatchResult(HttpServletRequest request, HttpServletResponse response, GpModelAndView gpModelAndView) throws Exception{
        //1.寻找对应的视图
        if(CollectionUtils.isEmpty(viewResolvers)) return;
        for (GpViewResolver viewResolver : viewResolvers) {
            GpView gpView = viewResolver.resolveViewName(gpModelAndView.getViewName());
            if(null != gpView){
                gpView.rendor(request,response, gpModelAndView.getModel());
                return;
            }
        }
    }

    private GpHandlerMapping getHandler(HttpServletRequest request) {
        String requestUrl = request.getRequestURI().replaceAll(request.getContextPath(), "").replaceAll("/+", "/");
        for (GpHandlerMapping handlerMapping : this.handlerMappings) {
            Matcher matcher = handlerMapping.getPattern().matcher(requestUrl);
            if(matcher.matches()){
                return handlerMapping;
            }
        }
        return null;

    }

    /***
     * @author: huike.guo
     * @description: 根据url去获取对应的method,获取到了就执行,获取不到404
     * @date: 2021年5月17日15:27:38
     * @param req,resp 参数
     * @return void
     */
    /*private void doHandlerMethod(HttpServletRequest req, HttpServletResponse resp) {
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
            Object targetObj = gpApplicationContext.getBean(className);
            method.invoke(targetObj,paramObj);
        } catch (Exception e){
            e.printStackTrace();
        }
    }*/

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        gpApplicationContext = new GpApplicationContext(servletContext.getInitParameter("contextConfigLocation"));
        //2 进行MVC映射
        System.out.println("=====进行MVC映射=====");
        onRefresh(gpApplicationContext);
    }
    protected void onRefresh(GpApplicationContext context) {
        this.initStrategies(context);
    }
    protected void initStrategies(GpApplicationContext context) {
        //初始化handlerMappings
        this.initHandlerMappings(context);
        //初始化handlerAdapter
        this.initHandlerAdapters(context);
        //初始化视图解析器
        this.initViewResolvers(context);
    }

    private void initViewResolvers(GpApplicationContext context) {
        String config = context.getConfig().getProperty("resource.path");
        String filePath = this.getClass().getClassLoader().getResource(config).getFile();
        File file = new File(filePath);
        for (File listFile : file.listFiles()) {
            viewResolvers.add(new GpViewResolver(listFile));
        }
    }

    private void initHandlerAdapters(GpApplicationContext context) {
        if(CollectionUtils.isEmpty(handlerMappings)) return;
        for (GpHandlerMapping handlerMapping : handlerMappings) {
            handlerAdapterMap.put(handlerMapping,new GpHandlerAdapter());
        }
    }

    /***
     * @author: huike.guo
     * @description: 只处理IOC容器中被指定注解修饰的类,得到类的methods,
     * 然后进行获取方法上面的注释获取注释中的url.然后将url和method进行匹配存储
     * @date: 2021年5月17日15:27:31
     * @param
     * @return void
     */
    private void initHandlerMappings(GpApplicationContext context) {
        for (String beanName: context.getBeanDefinitionNames()) {
            Object bean = context.getBean(beanName);
            if(!bean.getClass().isAnnotationPresent(GpController.class)) continue;
            String classUrl = "";
            if(bean.getClass().isAnnotationPresent(GpRequestMapping.class)){
                GpRequestMapping annotation = bean.getClass().getAnnotation(GpRequestMapping.class);
                classUrl = annotation.value();
            }
            for (Method method : bean.getClass().getMethods()) {
                if(!method.isAnnotationPresent(GpRequestMapping.class))continue;
                String methodUrl = method.getAnnotation(GpRequestMapping.class).value();
                String reqUrl = ("/" + classUrl + "/" +  methodUrl).replaceAll("/+","/");
                System.out.println("====="+reqUrl+"====="+methodUrl);
                Pattern compile = Pattern.compile(reqUrl);
                handlerMappings.add(new GpHandlerMapping(compile,method,bean));
            }

        }
    }

    private String toLowCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0]+=32;
        return new String(chars);
    }
}