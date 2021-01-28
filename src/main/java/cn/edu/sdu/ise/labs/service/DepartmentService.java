package cn.edu.sdu.ise.labs.service;

import cn.edu.sdu.ise.labs.dto.DepartmentDTO;
import cn.edu.sdu.ise.labs.dto.DepartmentQueryDTO;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.vo.DepartmentVO;

import java.util.List;
import java.util.Map;

/**
 * 部门模块服务接口
 *
 * @author 李洪文
 * @date 2019-12-3
 */
public interface DepartmentService {
    Page<DepartmentVO> listByPage(DepartmentQueryDTO queryDTO);

    /**
     * 新建部门
     *
     * @param departmentDTO 部门输入对象
     * @return 部门编码
     */
    String addDepartment(DepartmentDTO departmentDTO);

    /**
     * 更新部门数据
     *
     * @param departmentDTO 部门输入对象
     * @return 部门编码
     */
    String updateDepartment(DepartmentDTO departmentDTO);

    /**
     * 根据编码列表，批量删除部门
     *
     * @param codeList 编码列表
     */
    void deleteByCodes(List<String> codeList);

    /**
     * 根据编码列表，获取部门集合（内部调用）
     *
     * @param codeList 部门编码列表
     * @return 包含部门信息的映射，key是部门编码
     */
    Map<String, DepartmentVO> getDepartmentMap(List<String> codeList);

    /**
     * 根据部门名称获取下拉列表
     *
     * @param departmentName 部门名称（模糊匹配）
     * @return 部门列表
     */
    List<DepartmentVO> listByName(String departmentName);
}
