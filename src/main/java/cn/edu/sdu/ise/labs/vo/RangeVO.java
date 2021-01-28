package cn.edu.sdu.ise.labs.vo;

import lombok.Data;

/**
 * @author 12036
 */
@Data
public class RangeVO {
    /**
     * 场地编码
     */
    private String rangeCode;
    /**
     * 场地名称
     */
    private String rangeName;
    /**
     * 场地位置
     */
    private String rangeLocation;
    /**
     *   场地状态
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
     * 创建时间
     */
    private String createdAt;

    /**
     * 修改日期
     */
    private String updatedAt;
    /**
     * 状态描述
     */
    private String statusDesc;
}
