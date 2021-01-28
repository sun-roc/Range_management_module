package cn.edu.sdu.ise.labs.config;

import cn.edu.sdu.ise.labs.model.ResultContext;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 李洪文
 * @description
 * @date 2019/11/18 18:11
 */
@ControllerAdvice
public class MyControllerAdvice {
    /**
     * @param ex 异常信息
     * @return json格式出错信息
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String myExceptionHandler(Exception ex) {
        return JSONObject.toJSONString(ResultContext.returnFail(ResultContext.FAIL, ex.getMessage()));
    }
}
