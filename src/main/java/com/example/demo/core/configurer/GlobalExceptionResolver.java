package com.example.demo.core.configurer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.core.ret.RetCode;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 全局异常处理
 * @author 张瑶
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    /**
     * 不满足@RequiresGuest注解时抛出的异常信息
     */
    private static final String GUEST_ONLY = "Attempting to perform a guest-only operation";

    @ExceptionHandler(UnauthenticatedException.class)
    public void page401(HttpServletResponse response, ServiceException e) {
        RetResult<Object> result = new RetResult<>();
        String eMsg = e.getMessage();
        if (StringUtils.startsWithIgnoreCase(eMsg,GUEST_ONLY)){
            result.setCode(RetCode.UNAUTHZ).setMsg("只允许游客访问，若您已登录，请先退出登录").setData(null);
        }else{
            result.setCode(RetCode.UNAUTHEN).setMsg("用户未登录").setData(null);
        }
        responseResult(response, result);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public void page403(HttpServletResponse response) {
        RetResult<Object> result = new RetResult<>();
        result.setCode(RetCode.UNAUTHZ).setMsg("用户没有访问权限").setData(null);
        responseResult(response, result);
    }
    /**
     * 业务异常的处理
     */
    @ExceptionHandler(value = ServiceException.class)
    public void serviceExceptionHandler(HttpServletResponse response, ServiceException e) {
        RetResult<Object> result = new RetResult<>();
        result.setCode(RetCode.FAIL).setMsg(e.getMessage()).setData(null);
        responseResult(response, result);
    }
    /**
     * 其他异常统一处理
     */
    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(HttpServletResponse response, Exception e) {
        RetResult<Object> result = new RetResult<>();
        result.setCode(RetCode.INTERNAL_SERVER_ERROR).setMsg("服务器打酱油了，请稍后再试~");
        logger.error(e.getMessage(), e);
        responseResult(response, result);
    }
    /**
     * @param response
     * @param result
     * @Title: responseResult
     * @Description: 响应结果
     * @Reutrn void
     */
    private void responseResult(HttpServletResponse response, RetResult<Object> result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
