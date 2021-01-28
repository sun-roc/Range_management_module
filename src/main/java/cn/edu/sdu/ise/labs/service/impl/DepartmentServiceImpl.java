package cn.edu.sdu.ise.labs.service.impl;

import cn.edu.sdu.ise.labs.constant.PrefixConstant;
import cn.edu.sdu.ise.labs.dao.DepartmentMapper;
import cn.edu.sdu.ise.labs.dto.DepartmentDTO;
import cn.edu.sdu.ise.labs.dto.DepartmentQueryDTO;
import cn.edu.sdu.ise.labs.model.Department;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.service.DepartmentService;
import cn.edu.sdu.ise.labs.service.KeyMaxValueService;
import cn.edu.sdu.ise.labs.service.utils.DepartmentUtils;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.utils.PageUtils;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import cn.edu.sdu.ise.labs.vo.DepartmentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 李洪文
 * @description
 * @date 2019/12/3 9:33
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private KeyMaxValueService keyMaxValueService;

    @Override
    public Page<DepartmentVO> listByPage(DepartmentQueryDTO queryDTO) {
        if (queryDTO == null) {
            queryDTO = new DepartmentQueryDTO();
        }

        queryDTO.setDepartmentName(FormatUtils.makeFuzzySearchTerm(queryDTO.getDepartmentName()));
        Token token = TokenContextHolder.getToken();

        Integer size = departmentMapper.count(queryDTO, token.getTenantCode());
        PageUtils pageUtils = new PageUtils(queryDTO.getPage(), queryDTO.getPageSize(), size);
        Page<DepartmentVO> pageData = new Page<>(pageUtils.getPage(), pageUtils.getPageSize(), pageUtils.getTotal(), new ArrayList<>());
        if (size == 0) {
            // 没有命中，则返回空数据。
            return pageData;
        }

        List<Department> list = departmentMapper.list(queryDTO, pageUtils.getOffset(), pageUtils.getLimit(), token.getTenantCode());
        for (Department department : list) {
            pageData.getList().add(DepartmentUtils.convertToVO(department));
        }

        return pageData;
    }


    /**
     * 新建部门
     *
     * @param departmentDTO 部门输入对象
     * @return 部门编码
     */
    @Override
    public String addDepartment(DepartmentDTO departmentDTO) {
        // 校验输入数据正确性
        DepartmentUtils.validateDepartment(departmentDTO);
        // 创建实体对象，用以保存到数据库
        Department department = new Department();
        // 将输入的字段全部复制到实体对象中
        BeanUtils.copyProperties(departmentDTO, department);
        // 生成业务代码
        department.setDepartmentCode(keyMaxValueService.generateBusinessCode(PrefixConstant.DEPARTMENT));
        // 将token相关信息填入department对象
        TokenContextHolder.formatInsert(department);
        // 调用DAO方法保存到数据库表
        departmentMapper.insert(department);
        return department.getDepartmentCode();
    }

    /**
     * 更新部门数据
     *
     * @param departmentDTO 部门输入对象
     * @return 部门编码
     */
    @Override
    public String updateDepartment(DepartmentDTO departmentDTO) {
        //获取token
        Token token = TokenContextHolder.getToken();
        // 校验输入数据正确性
        DepartmentUtils.validateDepartment(departmentDTO);
        //警告部门代码不能为空
        Assert.hasText(departmentDTO.getDepartmentCode(), "部门代码不能为空");
        //从数据库中获取需要更新的实体类
        Department department = departmentMapper.getByCode(departmentDTO.getDepartmentCode(), token.getTenantCode());
        Assert.notNull(department, "为找到部门，代码为：" + departmentDTO.getDepartmentCode());
        // 将输入的字段全部复制到实体对象中
        BeanUtils.copyProperties(departmentDTO, department);
        //设置更新人的数据
        department.setUpdatedBy(token.getTenantCode());
        //将实体类数据传回数据库
        departmentMapper.updateByPrimaryKey(department);
        //将更新的实体类的数据返回
        return department.getDepartmentCode();
    }

    /**
     * 根据编码列表，批量删除部门
     *
     * @param codeList 编码列表
     */
    @Override
    public void deleteByCodes(List<String> codeList) {
        Assert.notEmpty(codeList, "部门编码列表不能为空");
        Token token = TokenContextHolder.getToken();
        departmentMapper.deleteByCodes(codeList, token.getTeacherCode(), token.getTenantCode());
    }

    /**
     * 根据编码列表，获取部门集合（内部调用）
     *
     * @param codeList 部门编码列表
     * @return 包含部门信息的映射，key是部门编码
     */
    @Override
    public Map<String, DepartmentVO> getDepartmentMap(List<String> codeList) {
        if (CollectionUtils.isEmpty(codeList)) {
            return new HashMap<>(1 << 2);
        }

        Token token = TokenContextHolder.getToken();
        List<Department> departmentList = departmentMapper.listByCodes(codeList, token.getTenantCode());
        return departmentList.stream()
                .map(item -> DepartmentUtils.convertToVO(item))
                .collect(Collectors.toMap(DepartmentVO::getDepartmentCode, Function.identity()));
    }

    /**
     * 根据部门名称获取下拉列表
     *
     * @param departmentName 部门名称（模糊匹配）
     * @return 部门列表
     */
    @Override
    public List<DepartmentVO> listByName(String departmentName) {
        Token token = TokenContextHolder.getToken();
        departmentName = FormatUtils.makeFuzzySearchTerm(departmentName);
        List<Department> departmentList = departmentMapper.listByName(departmentName, token.getTenantCode());
        return departmentList.stream()
                .map(item -> DepartmentUtils.convertToVO(item))
                .collect(Collectors.toList());
    }
}
