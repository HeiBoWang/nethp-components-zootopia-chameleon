package ${groupId}.web.configuration;

import com.jd.medicine.base.common.global.id.GlobalIdConfig;
import ${groupId}.common.constants.AppConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 请填写类的描述
 *
 * @author zhangna12
 * @date 2020-02-20
 */
@Configuration
public class IdConfig {

    @Bean(initMethod = "initParam",destroyMethod = "destroyParam")
    public GlobalIdConfig globalIdConfig(){
        GlobalIdConfig globalIdConfig = new GlobalIdConfig();
        globalIdConfig.setAppName(AppConstant.APP_NAME);
        globalIdConfig.setOption(3);
        return globalIdConfig;
    }
}
