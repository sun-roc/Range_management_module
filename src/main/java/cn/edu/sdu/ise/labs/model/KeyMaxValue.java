package cn.edu.sdu.ise.labs.model;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 业务主键最大值表实体对象
 * @Author: lishikuan
 * @Date: Created on 2019/5/10
 */
@Data
public class KeyMaxValue {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 租户代码
     */
    private String tenantCode;

    /**
     * 业务主键前缀
     */
    private String keyPrefix;

    /**
     * 业务主键日期部分
     */
    private String datePart;

    /**
     * 后缀
     */
    private Integer currentValue;

    /**
     * 更新时间
     */
    private Date updatedAt;
}