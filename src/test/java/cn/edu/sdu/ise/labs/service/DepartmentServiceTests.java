package cn.edu.sdu.ise.labs.service;

import cn.edu.sdu.ise.labs.dto.DepartmentDTO;
import cn.edu.sdu.ise.labs.dto.DepartmentQueryDTO;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import cn.edu.sdu.ise.labs.vo.DepartmentVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 李洪文
 * @description
 * @date 2019/12/3 10:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("cn.edu.sdu.ise.labs.dao")
@Slf4j
public class DepartmentServiceTests {
    @Autowired
    private DepartmentService departmentService;

    @Test
    public void testListByPage() {
        initToken();
        DepartmentQueryDTO queryDTO = new DepartmentQueryDTO();
        queryDTO.setPage(1);
        queryDTO.setPageSize(10);
        Page<DepartmentVO> pageData = departmentService.listByPage(queryDTO);
        assert pageData.getList().size() > 0;
    }

    @Test
    public void testAdd() {
        initToken();
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentName("通信所");
        departmentDTO.setContact("张三");
        departmentDTO.setContactPhone("1532384234234");
        departmentDTO.setDescription("这是一条备注");
        assert departmentService.addDepartment(departmentDTO) != null;
    }

    @Test
    public void testListByCodes() {
        initToken();
        List<String> codeList = new ArrayList<>();
        codeList.add("DP1912030006");
        Map<String, DepartmentVO> map = departmentService.getDepartmentMap(codeList);
        assert map.size() != 0;
    }

    @Test
    public void testListByName() {
        initToken();
        List<String> codeList = new ArrayList<>();
        codeList.add("DP1912030006");
        List<DepartmentVO> list = departmentService.listByName("心");
        assert list.size() != 0;
    }

    @Test
    public void testUpdate() {
        initToken();

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentCode("DP0000001");
        departmentDTO.setDepartmentName("通信所");
        departmentDTO.setContact("张三");
        departmentDTO.setContactPhone("1532384234234");
        departmentDTO.setDescription("这是一条备注");
        assert departmentService.addDepartment(departmentDTO) != null;
    }

    private void initToken() {
        Token token = new Token();
        token.setTenantCode("001");
        token.setTeacherCode("TE00000001");
        TokenContextHolder.setToken(token);
    }
}
