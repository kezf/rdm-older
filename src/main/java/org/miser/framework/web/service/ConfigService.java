package org.miser.framework.web.service;

import org.miser.core.system.config.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 首创 html调用 thymeleaf 实现参数管理
 *
 * @author Barry
 */
@Service("config")
public class ConfigService {
    @Autowired
    private IConfigService configService;

    /**
     * 根据键名查询参数配置信息
     *
     * @return 参数键值
     */
    public String getKey(String configKey) {
        return configService.selectConfigByKey(configKey);
    }

}
