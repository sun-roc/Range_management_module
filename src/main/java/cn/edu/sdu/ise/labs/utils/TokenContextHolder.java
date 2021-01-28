package cn.edu.sdu.ise.labs.utils;

import cn.edu.sdu.ise.labs.model.Token;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * token 存取工具类
 *
 * @author 李洪文
 * @description
 * @date 2019/12/3 9:24
 */
public class TokenContextHolder {
    private static ThreadLocal<Token> tokenHolder = new ThreadLocal<>();

    public static void setToken(Token token) {
        tokenHolder.set(token);
    }

    private static void setFieldValue(Field field, Object o, String value) {
        if (field.getType() != String.class) {
            return;
        }
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(o, value);
        } catch (Exception ex) {
        }

        field.setAccessible(accessible);
    }

    public static Token getToken() {
        Token token = tokenHolder.get();
        Assert.notNull(token, "未找到访问令牌");
        return token;
    }

    public static void formatInsert(Object model) {
        Token token = getToken();
        Field field = ReflectionUtils.findField(model.getClass(), "tenantCode");
        if (field != null) {
            setFieldValue(field, model, token.getTenantCode());
        }

        field = ReflectionUtils.findField(model.getClass(), "createdBy");
        if (field != null) {
            setFieldValue(field, model, token.getTeacherCode());
        }

        field = ReflectionUtils.findField(model.getClass(), "updatedBy");
        if (field != null) {
            setFieldValue(field, model, token.getTeacherCode());
        }
    }

    public static void formatUpdate(Object model) {
        Token token = getToken();
        Field field = ReflectionUtils.findField(model.getClass(), "updatedBy");
        if (field != null) {
            setFieldValue(field, model, token.getTeacherCode());
        }
    }

}
