package com.design.background.common.exception;

import com.design.background.model.ResultData;
import com.design.background.util.DesignFileUtils;
import com.design.background.util.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: DesignExceptionHandler
 * @Description: 全局异常处理
 * @Author: lxt
 * @Date: 2019-02-19 16:16
 * @Version 1.0
 **/
@ControllerAdvice
public class DesignExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(DesignFileUtils.class);
    public final static String ERROR_DEFAULT_PAGE = "error/error";
    /**
     * @Title: handleException
     * @Description: 处理Controller层异常
     * @Author: lxt 
     * @param: e
     * @Date: 2019-02-19 17:01 
     * @return: java.lang.Object
     * @throws: 
     */
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        logger.error("未知异常",e);
        if(!ServletUtils.isAjax()){
            ModelAndView mv = new ModelAndView();
            mv.setViewName(ERROR_DEFAULT_PAGE);
            return mv;
        }else{
            //ajax暂不做处理
            return null;
        }
    }

}
