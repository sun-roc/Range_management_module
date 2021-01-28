package cn.edu.sdu.ise.labs.model;

import lombok.Data;

import java.util.Date;

/**
 * 令牌实体类
 *
 * @author 李洪文
 * @date 2019/12/3 10:38
 */
@Data
public class Token {
    /**
     * 令牌token
     */
    private String accessToken;

    /**
     * 租户代码
     */
    private String tenantCode;

    /**
     * 教师名称
     */
    private String teacherName;

    /**
     * 教师编码
     */
    private String teacherCode;

    /**
     * 上次动作
     */
    private Date lastAction;
}
