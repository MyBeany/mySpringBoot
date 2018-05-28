package com.example.demo.core.aop;

import com.alibaba.fastjson.JSON;
import com.example.demo.core.ret.ServiceException;
import com.example.demo.core.systemlog.SystemLogQueue;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.core.utils.IpUtils;
import com.example.demo.model.SystemLog;
import com.example.demo.model.UserInfo;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: aop记录操作日志
 * @author 张瑶
 * @date 2018年5月28日 18:43:28
 */
@Aspect
@Component
public class AspectLog {

    private static final Logger logger = LoggerFactory.getLogger(AspectLog.class);

    @Resource
    private SystemLogQueue systemLogQueue;

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.example.demo.core.aop.AnnotationLog)")
    public void methodCachePointcut() {
    }

    @Before("methodCachePointcut()")
    public void doBefore(JoinPoint p) throws Exception{
        SystemLog systemLog =getSystemLogInit(p);
        systemLog.setLogType(SystemLog.LOGINFO);
        systemLogQueue.add(systemLog);
    }

    /**
     * 调用后的异常处理
     * @param p
     * @param e
     */
    @AfterThrowing(pointcut = "methodCachePointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint p, Throwable e) throws Throwable {
        //业务异常不用记录
        if(!(e instanceof ServiceException)) {
            try {
                SystemLog systemLog =getSystemLogInit(p);
                systemLog.setLogType(SystemLog.LOGERROR);
                systemLog.setExceptionCode(e.getClass().getName());
                systemLog.setExceptionDetail(e.getMessage());
                systemLogQueue.add(systemLog);
            } catch (Exception ex) {
                logger.error("==异常通知异常==");
                logger.error("异常信息:{}", ex.getMessage());
            }
        }
    }

    private SystemLog getSystemLogInit(JoinPoint p){
        SystemLog systemLog = new SystemLog();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        try{
            //类名
            String targetClass = p.getTarget().getClass().toString();
            //请求的方法名
            String tartgetMethod = p.getSignature().getName();
            //获取类名  UserController
            String classType = p.getTarget().getClass().getName();
            Class<?> clazz = Class.forName(classType);
            String clazzName = clazz.getName();
            //请求参数名+参数值的map
            Map<String, Object> nameAndArgs = getFieldsName(this.getClass(), clazzName, tartgetMethod, p.getArgs());
            systemLog.setId(ApplicationUtils.getUUID());
            systemLog.setDescription(getMthodRemark(p));
            systemLog.setMethod(targetClass+"."+tartgetMethod);
            systemLog.setRequestIp(IpUtils.getIp(request));
            systemLog.setParams(JSON.toJSONString(nameAndArgs));
            systemLog.setUserId(getUserId());
            systemLog.setCreateTime(new Date());
        }catch (Exception ex){
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
        return systemLog;
    }

    /**
     * 通过反射机制 获取被切参数名以及参数值
     *
     * @param cls
     * @param clazzName
     * @param methodName
     * @param args
     * @return
     * @throws NotFoundException
     */
    private Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws NotFoundException {
        Map<String, Object> map = new HashMap<>();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);
        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++) {
            //HttpServletRequest 和HttpServletResponse 不做处理
            if(!(args[i] instanceof HttpServletRequest || args[i] instanceof HttpServletResponse)){
                //paramNames即参数名
                map.put(attr.variableName(i + pos), JSON.toJSONString(args[i]));
            }
        }
        return map;
    }

    /**
     * 获取方法的中文备注____用于记录用户的操作日志描述
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private static String getMthodRemark(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] method = targetClass.getMethods();
        String methode = "";
        for (Method m : method) {
            if (m.getName().equals(methodName)) {
                Class[] tmpCs = m.getParameterTypes();
                if (tmpCs.length == arguments.length) {
                    AnnotationLog methodCache = m.getAnnotation(AnnotationLog.class);
                    if (methodCache != null) {
                        methode = methodCache.remark();
                    }
                    break;
                }
            }
        }
        return methode;
    }

    private static String getUserId() {
        String userId = "";
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        if(userInfo != null){
            userId = userInfo.getId();
        }
        return userId;
    }



}
