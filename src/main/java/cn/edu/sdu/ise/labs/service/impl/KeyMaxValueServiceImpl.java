package cn.edu.sdu.ise.labs.service.impl;

import cn.edu.sdu.ise.labs.dao.KeyMaxValueMapper;
import cn.edu.sdu.ise.labs.model.KeyMaxValue;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.service.KeyMaxValueService;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 业务主键最大值服务接口实现
 * @Author: lishikuan
 * @Date: Created on 2019/5/11
 */
@Service
public class KeyMaxValueServiceImpl implements KeyMaxValueService {
    @Autowired
    private KeyMaxValueMapper keyMaxValueMapper;
    private final int INIT_VALUE = 1;
    private final int MAX_VALUE = 9999;

    /**
     * 生成各业务表唯一编码
     *
     * @param keyPrefix 前缀
     * @return 唯一编码
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String generateBusinessCode(String keyPrefix) {
        Token token = TokenContextHolder.getToken();
        SimpleDateFormat dateFormat = (new SimpleDateFormat("yyMMdd"));
        String datePart = dateFormat.format(new Date());
        KeyMaxValue keyMaxValue = new KeyMaxValue();
        keyMaxValue.setCurrentValue(1);
        keyMaxValue.setKeyPrefix(keyPrefix);
        keyMaxValue.setTenantCode(token.getTenantCode());
        keyMaxValue.setUpdatedAt(new Date());
        keyMaxValueMapper.insertAndUpdate(keyPrefix, datePart, INIT_VALUE, token.getTenantCode());
        keyMaxValue = keyMaxValueMapper.getKeyValue(keyPrefix, datePart, token.getTenantCode());
        if (keyMaxValue.getCurrentValue() > MAX_VALUE) {
            throw new RuntimeException("业务主键序数值超过了9999，请明天再来操作该业务");
        }

        return String.format("%s%s%04d", keyPrefix, datePart, keyMaxValue.getCurrentValue());
    }
}
