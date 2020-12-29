package com.example.bolg.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
//异常处理
public class ControllerExceptionHandler {
    //记录日志
    private final Logger logger= LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(Exception.class)
    public ModelAndView  exceptionHander(HttpServletRequest request,Exception e) throws Exception {
     logger.error("Request URL :{},Exception : {}",request.getRequestURL(),e);
     //根据状态返回页面
     if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
         throw e;
     }
        ModelAndView mv =new ModelAndView();
        mv.addObject("url",request.getRequestURI());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
         return  mv;

    }




}
