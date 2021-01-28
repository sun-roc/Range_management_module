package cn.edu.sdu.ise.labs.controller;
import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.ResultContext;
import cn.edu.sdu.ise.labs.service.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**场地管理后端服务模块
 * @author 孙旭鹏
 */
@RestController
@RequestMapping("range")
public class RangeController {

    @Autowired
    private RangeService rangeService;

    @PostMapping("add")
    public ResultContext add(@RequestBody RangeDTO rangeDTO) {
        return ResultContext.returnSuccess(rangeService.addRange(rangeDTO));
    }
    @PostMapping("update")
    public ResultContext update(@RequestBody RangeDTO rangeDTO){
        return ResultContext.returnSuccess(rangeService.updateRange(rangeDTO));
    }
    @PostMapping("list")
    public ResultContext list(@RequestBody RangeQueryDTO rangeQueryDTO) {
        return ResultContext.returnSuccess(rangeService.listRange(rangeQueryDTO));
    }
    @PostMapping("delete")
    public ResultContext delete(@RequestBody List<String> rangeCodes) {
        rangeService.deleteRange(rangeCodes);
        return ResultContext.returnSuccess(true);
    }
    @GetMapping("get")
    public ResultContext get(String rangeCode){
        return ResultContext.returnSuccess(rangeService.getRange(rangeCode));
    }
}
