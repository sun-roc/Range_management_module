package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.dto.DepartmentQueryDTO;
import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.Department;
import cn.edu.sdu.ise.labs.model.Range;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 孙旭鹏
 */
public interface RangeMapper {
    /**
     * 根据查询条件获取命中个数
     * @param queryDTO 查询条件
     * @return 命中数量
     */
    Integer count(
            @Param("queryDTO") RangeQueryDTO queryDTO);

    /**
     * 根据查询条件获取部门列表
     * @param queryDTO 查询条件
     * @param offset 开始位置
     * @param limit 记录数量
     * @return 场地列表
     */
    List<Range> list(
            @Param("queryDTO") RangeQueryDTO queryDTO,
            @Param("offset") Integer offset,
            @Param("limit") Integer limit);

    /**
     * 新建场地
     * @param record 场地参数
     * @return
     */
    int insert(Range record);

    /**
     * 根据主键更新数据库
     * @param record 场地参数
     * @return
     */
    int updateByPrimaryKey(Range record);

    /**
     * 通过编码查询
     * @param rangeCode 场地编码
     * @return 场地类
     */
    Range getByCode(
            @Param("rangeCode") String rangeCode);

    /**
     * 根据编码删除场地
     * @param rangeCode 场地编码
     */
    void deleteByCode(
            @Param("rangeCode") String rangeCode);

    /**
     * 根据场地名称查询有无重复的场地名
     * @param rangeName 场地名称
     * @return integer
     */
    Integer countByRangeName(
            @Param("rangeName") String rangeName);

}