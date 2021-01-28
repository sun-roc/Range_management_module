package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.model.CompetitionEvent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author 12036
 */
public interface CompetitionEventMapper {
    /**
     *检查场地是否被比赛项目占用
     * @param rangeCode 场地编码
     * @return 命中数量
     */
    Integer countByRangeCode(
            @Param("rangeCode") String rangeCode);
}