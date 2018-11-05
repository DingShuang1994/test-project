package com.douniwan.logAop;

import com.douniwan.controller.LogAopController;
import com.douniwan.domain.SysLog;
import com.douniwan.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect  //切面类 设置各种通知和切点
public class LogAop {
    private Date visitTime; //访问时间
    private Class executionClass;  // 访问的类
    private Method executionMethod; //访问的方法对象

    //获取request对象
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;

    //在切点之前配置要做的事
    @Before("execution(* com.douniwan.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date(); // 设置访问时间
        //访问的类
        executionClass =  jp.getTarget().getClass();
        //访问的方法
        //方法名
        String methodName = jp.getSignature().getName();
        //方法的参数
        Object[] args = jp.getArgs();
        Class[]  nameArgs = new Class[args.length];
        if (args != null && args.length != 0){
            for (int i = 0; i < args.length; i++) {
                nameArgs[i] =args[i].getClass() ;
            }
            executionMethod = executionClass.getMethod(methodName, nameArgs);
        }else{
            executionMethod = executionClass.getMethod(methodName);
        }
    }

    //获取时长,url ,ip ,用户名
    @After("execution(* com.douniwan.controller.*.*(..))")
    public void doAfter() throws Exception {
        //获取url
        String url = "";
        //获取访问类的注解的value值
        if (executionClass != LogAopController.class && executionClass != null){
            RequestMapping requestMapping =(RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            String[] classUrl = requestMapping.value();
            if (executionMethod != null){
                RequestMapping methodRequestMapping =(RequestMapping) executionMethod.getAnnotation(RequestMapping.class);
                String[] methodUrl = methodRequestMapping.value();
                url = classUrl[0] +methodUrl[0] ;

                //获取时长访问
                Date endTime = new Date();
                Long  executionTime = endTime.getTime()-visitTime.getTime();
                //获取ip
                String ip = request.getRemoteAddr();
                //获取url 用request对象
                String url1 = request.getRequestURI().toString();
                System.out.println(url+";;"+url1);
                //获取访问者名称
                SecurityContext context = SecurityContextHolder.getContext();
                String username = ((User) context.getAuthentication().getPrincipal()).getUsername();

                //创建一个sysLog对象
                SysLog sysLog = new SysLog();
                //添加访问时长
                sysLog.setVisitTime(visitTime);
                sysLog.setExecutionTime(executionTime);
                sysLog.setIp(ip);
                sysLog.setMethod("[类名]" + executionClass.getName()+"[方法名]"+executionMethod.getName());
                sysLog.setUrl(url);
                sysLog.setUsername(username);

                sysLogService.save(sysLog);
            }
        }
    }
}
