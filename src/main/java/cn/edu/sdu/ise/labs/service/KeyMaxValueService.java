package cn.edu.sdu.ise.labs.service;

/**
 * @Description: 业务主键最大值服务接口
 * @Author: lishikuan
 * @Date: Created on 2019/5/11
 */
public interface KeyMaxValueService {
    /**
     * 生成各业务表唯一编码
     *
     * @param keyPrefix 前缀
     * @return 唯一编码
     */
    String generateBusinessCode(String keyPrefix);
}
