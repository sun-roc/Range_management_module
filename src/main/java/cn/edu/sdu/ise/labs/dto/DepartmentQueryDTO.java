package cn.edu.sdu.ise.labs.dto;

import lombok.Data;

/**
 * @author 李洪文
 * @description
 * @date 2019/12/3 10:18
 */
@Data
public class DepartmentQueryDTO {
    /**
     * 部门名称，模糊匹配
     */
    private String departmentName;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 每页记录数
     */
    private Integer pageSize;
}
