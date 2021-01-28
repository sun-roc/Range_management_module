package cn.edu.sdu.ise.labs.service;
import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.model.Range;
import cn.edu.sdu.ise.labs.vo.RangeVO;

import java.util.List;

/**
 * @author 孙旭鹏
 */
public interface RangeService {
    /**
     * 添加场地
     * @param rangeDTO 场地对象
     * @return 场地编码
     */
    String addRange(RangeDTO rangeDTO);

    /**
     * 更新场地
     * @param rangeDTO 场地对象
     * @return 场地编码
     */
    String updateRange(RangeDTO rangeDTO);

    /**
     * 获取场地列表(分页)
     * @param queryDTO 场地编码
     * @return 场地列表
     */
    Page<RangeVO> listRange(RangeQueryDTO queryDTO);

    /**
     * 删除场地
     * @param rangeCodes 场地编码
     */
    void deleteRange(List<String> rangeCodes);

    /**
     * 获取详细
     * @param rangeCode 场地编码
     * @return 场地对象
     */
    RangeVO getRange(String rangeCode);
}
