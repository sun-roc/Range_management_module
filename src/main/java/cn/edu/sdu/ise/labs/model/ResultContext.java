package cn.edu.sdu.ise.labs.model;

import com.alibaba.fastjson.JSONObject;

/**
 * 描述：
 *
 * @author yangqiang
 * @date 2018-10-23
 */
public class ResultContext<T> {

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 接口返回的数据
     */
    private T data;
    /**
     * 接口返回的代码类型
     */
    private int code;
    /**
     * 接口返回的提示信息
     */
    private String message;

    public static final int SUCCESS = 200;
    public static final int FAIL = 1000;
    public static final int SESSION_TIMEOUT = 1001;


    /**
     * 提供默认构造
     */
    public ResultContext() {
        this.code = SUCCESS;
        this.success = true;
        this.message = "成功";
    }

    public ResultContext(String jsonObjectString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonObjectString);
        this.code = (Integer) jsonObject.get("code");
        this.success = (Boolean) jsonObject.get("success");
        this.message = (String) jsonObject.get("message");
        this.data = (T) jsonObject.get("data");
    }

    public static ResultContext returnFail(String message) {
        ResultContext resultContext = new ResultContext();
        resultContext.setCode(FAIL);
        resultContext.setMessage(message);
        resultContext.setSuccess(false);
        return resultContext;
    }

    public static ResultContext returnFail(Integer code, String message) {
        ResultContext resultContext = new ResultContext();
        resultContext.setCode(code);
        resultContext.setMessage(message);
        resultContext.setSuccess(false);
        return resultContext;
    }

    public static ResultContext returnSuccess() {
        return new ResultContext();
    }

    public static ResultContext<Object> returnSuccess(Object data) {
        ResultContext resultContext = new ResultContext();
        resultContext.setData(data);
        return resultContext;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {

        return JSONObject.toJSONString(this);
    }
}
