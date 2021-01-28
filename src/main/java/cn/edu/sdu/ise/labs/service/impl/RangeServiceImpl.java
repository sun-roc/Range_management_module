package cn.edu.sdu.ise.labs.service.impl;
import cn.edu.sdu.ise.labs.constant.PrefixConstant;
import cn.edu.sdu.ise.labs.dao.CompetitionEventMapper;
import cn.edu.sdu.ise.labs.dao.RangeMapper;
import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.*;
import cn.edu.sdu.ise.labs.service.KeyMaxValueService;
import cn.edu.sdu.ise.labs.service.RangeService;
import cn.edu.sdu.ise.labs.service.utils.RangeUtils;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.utils.PageUtils;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import cn.edu.sdu.ise.labs.vo.RangeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RangeServiceImpl implements RangeService {
    @Autowired
    private RangeMapper rangeMapper;
    @Autowired
    private KeyMaxValueService keyMaxValueService;
    @Autowired
    private CompetitionEventMapper competitionEventMapper;

    /**
     * 添加场地
     * @param rangeDTO
     * @return
     */
    @Override
    public String addRange(RangeDTO rangeDTO) {
        // 校验输入数据正确性
        RangeUtils.validateRange(rangeDTO);
        // 创建实体对象，用以保存到数据库
        Range range = new Range();
        // 将输入的字段全部复制到实体对象中
        BeanUtils.copyProperties(rangeDTO,range);
        // 自动生成场地编码
        range.setRangeCode(keyMaxValueService.generateBusinessCode(PrefixConstant.RANGE));
        // 将token相关信息填入range对象
        TokenContextHolder.formatInsert(range);
        //设置创建和更新时间
        range.setCreatedAt(new Date());
        range.setUpdatedAt(range.getCreatedAt());
        //检测是否重名
        Integer number = rangeMapper.countByRangeName(range.getRangeName());
        //重名则提供警告
        Assert.isTrue(number == 0,"警告:场地名称已经存在");
        //调用mapper中的insert方法将其插入
        rangeMapper.insert(range);
        return range.getRangeCode();
    }

    /**
     * 更新场地
     * @param rangeDTO
     * @return
     */
    @Override
    public String updateRange(RangeDTO rangeDTO) {
        //获取token
        Token token = TokenContextHolder.getToken();
        // 校验输入数据正确性
        RangeUtils.validateRange(rangeDTO);
        //从数据库中获取需要更新的实体类
        Range range = rangeMapper.getByCode(rangeDTO.getRangeCode());
        //警告
        Assert.notNull(range, "未找到场地，场地编码为：" + rangeDTO.getRangeCode());
        //如果修改的名称和指向的名称不同则要检查是否重名,如果名称没改则不需要检查
        if (!rangeDTO.getRangeName().equals(range.getRangeName()))
        {//检测是否重名
            Integer number = rangeMapper.countByRangeName(rangeDTO.getRangeName());
            //重名则提供警告
            Assert.isTrue(number == 0,"警告:场地名称已经存在");}
        // 将输入的字段全部复制到实体对象中
        BeanUtils.copyProperties(rangeDTO,range);
        //设置更新人的数据
        range.setUpdatedBy(token.getTenantCode());
        //设置更新时间
        range.setUpdatedAt(new Date());
        //将实体类数据传回数据库
        rangeMapper.updateByPrimaryKey(range);
        //将更新的实体类的数据返回
        return range.getRangeCode();

    }

    /**
     * 获取场地列表（分页）
     * @param queryDTO
     * @return
     */
    @Override
    public Page<RangeVO> listRange(RangeQueryDTO queryDTO) {
        if (queryDTO == null) {
            queryDTO = new RangeQueryDTO();
        }
        //构造模糊匹配的字符串
            queryDTO.setRangeName(FormatUtils.makeFuzzySearchTerm(queryDTO.getRangeName()));
        //构造模糊匹配的字符串
            queryDTO.setRangeLocation(FormatUtils.makeFuzzySearchTerm(queryDTO.getRangeLocation()));
        //列表大小
        Integer size = rangeMapper.count(queryDTO);
        //生成Pageutil对象
        PageUtils pageUtils = new PageUtils(queryDTO.getPage(), queryDTO.getPageSize(), size);
        //生成分页查询的Page对象
        Page<RangeVO> pageData = new Page<>(pageUtils.getPage(), pageUtils.getPageSize(), pageUtils.getTotal(), new ArrayList<>());
        //如果查询结果为零返回pagedata
        if (size == 0) {
            // 没有命中，则返回空数据。
            return pageData;
        }
        //命中数量不为零时,获得一个实体类的列表,并把它转为vo对象放到pagedata中
        List<Range> rangeList = rangeMapper.list(queryDTO,pageUtils.getOffset(),pageUtils.getLimit());
        for (Range range: rangeList) {
            //将实体类转为vo对象
            pageData.getList().add(RangeUtils.convertToVO(range));
        }
        return pageData;
    }

    /**
     * 删除场地
     * @param rangeCodes
     */
    @Override
    public void deleteRange(List<String> rangeCodes) {
        //警告
        Assert.notEmpty(rangeCodes, "场地编码列表不能为空");
        //获取token
        TokenContextHolder.getToken();
        //将codeString循环删除
        for(String rangecode : rangeCodes) {
            //查询该场地是否在competition中使用,如果正在使用不能删除
            Integer number = competitionEventMapper.countByRangeCode(rangecode);
            Assert.isTrue(number == 0,"警告:该场地已经被比赛使用，不能删除");
            rangeMapper.deleteByCode(rangecode);
        }
    }

    @Override
    public RangeVO getRange(String rangeCode) {
        Token token = TokenContextHolder.getToken();
        //构造模糊匹配的字符串
        rangeCode = FormatUtils.makeFuzzySearchTerm(rangeCode);
        //通过查询获取实体类
        Range range =  rangeMapper.getByCode(rangeCode);
        //将实体类转为VO对象
        return RangeUtils.convertToVO(range);
    }
}
