package com.example.demo.core.configurer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.core.ret.RetCode;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionResolver {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);
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
