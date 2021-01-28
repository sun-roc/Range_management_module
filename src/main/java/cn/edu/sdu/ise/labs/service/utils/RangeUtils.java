package cn.edu.sdu.ise.labs.service.utils;

import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.model.Range;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.vo.RangeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

/**
 * @author 孙旭鹏
 */
public class RangeUtils {
    /**
     * 规范并校验rangeDto
     *
     * @param rangeDTO
     */
    public static void validateRange(RangeDTO rangeDTO) {
        FormatUtils.trimFieldToNull(rangeDTO);
        Assert.notNull(rangeDTO, "场地输入数据不能为空");
        Assert.hasText(rangeDTO.getRangeName(), "场地名称不能为空");
    }

    /**
     * 将实体对象转换为VO对象
     *
     * @param range 实体对象
     * @return VO对象
     */
    public static RangeVO convertToVO(Range range) {
        RangeVO rangeVO = new RangeVO();
        BeanUtils.copyProperties(range, rangeVO);
        //通过 swich 语句给statusDesc赋值
        switch (rangeVO.getStatus()){
            case 1:
                rangeVO.setStatusDesc("未开始");
                break;
            case 2:
                rangeVO.setStatusDesc("进行中");
                break;
            default:
                rangeVO.setStatusDesc("已结束");
                break;
        }
        rangeVO.setCreatedAt(FormatUtils.formatFullDate(range.getCreatedAt()));
        rangeVO.setUpdatedAt(FormatUtils.formatFullDate(range.getUpdatedAt()));
        //将VO对象返回
        return rangeVO;
    }
}
