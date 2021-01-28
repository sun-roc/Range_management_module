package cn.edu.sdu.ise.labs.dto;

import lombok.Data;

/**
 * @author 孙旭鹏
 */
@Data
public class RangeDTO {

    /**
     * 场地名称
     */
    private String rangeName;
    /**
     * 场地位置
     */
    private String rangeLocation;
    /**
     * 场地状态
     */
    private Integer status;
    /**
     * 关闭原因
     */
    private String closeReason;
    /**
     * 描述
     */
    private String description;
    /**
     * 场地编码
     */
    private String rangeCode;
}

