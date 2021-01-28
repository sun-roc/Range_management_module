package cn.edu.sdu.ise.labs.controller;

import cn.edu.sdu.ise.labs.dto.DepartmentDTO;
import cn.edu.sdu.ise.labs.dto.DepartmentQueryDTO;
import cn.edu.sdu.ise.labs.model.ResultContext;
import cn.edu.sdu.ise.labs.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理后端服务模块
 *
 * @author 李洪文
 * @description
 * @date 2019/12/3 11:07
 */

@RestController
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("add")
    public ResultContext add(@RequestBody DepartmentDTO departmentDTO) {
        return ResultContext.returnSuccess(departmentService.addDepartment(departmentDTO));
    }

    @PostMapping("update")
    public ResultContext update(@RequestBody DepartmentDTO departmentDTO) {
        return ResultContext.returnSuccess(departmentService.updateDepartment(departmentDTO));
    }

    @PostMapping("list")
    public ResultContext list(@RequestBody DepartmentQueryDTO queryDTO) {
        return ResultContext.returnSuccess(departmentService.listByPage(queryDTO));
    }

    @GetMapping("listByName")
    public ResultContext listByName(String departmentName) {
        return ResultContext.returnSuccess(departmentService.listByName(departmentName));
    }

    @PostMapping("delete")
    public ResultContext delete(@RequestBody List<String> codeList) {
        departmentService.deleteByCodes(codeList);
        return ResultContext.returnSuccess(true);
    }
}
