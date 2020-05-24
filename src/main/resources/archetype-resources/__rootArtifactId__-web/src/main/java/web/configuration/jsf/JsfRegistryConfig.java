package ${groupId}.web.configuration.jsf;

import com.jd.jsf.gd.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JsfRegistryConfig
 *
 * @author tanwei3
 * @date 2019-07-24 12:25
 */
@Configuration
public class JsfRegistryConfig {

    @Bean
    public RegistryConfig jsfRegistry(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("jsfRegistry");
        registryConfig.setIndex("i.jsf.jd.com");
        return registryConfig;
    }

}
