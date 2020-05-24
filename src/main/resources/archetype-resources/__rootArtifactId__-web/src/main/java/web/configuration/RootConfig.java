package ${groupId}.web.configuration;

import com.jd.medicine.base.common.spring.SpringContextHolder;
import com.jd.security.configsec.spring.config.JDSecurityPropertyCleanService;
import com.jd.security.configsec.spring.config.JDSecurityPropertySourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * RootConfig
 *
 * @author tanwei3
 * @date 2019-07-03 21:24
 */
@PropertySources(value = {
    @PropertySource(value = {"classpath:important.properties"}, encoding = "utf-8", factory = JDSecurityPropertySourceFactory.class, ignoreResourceNotFound = true),
    @PropertySource(value = {"classpath:suffix.properties"}, encoding = "utf-8", ignoreResourceNotFound = true)
})
@Import(JDSecurityPropertyCleanService.class)
@Configuration
@EnableAspectJAutoProxy( proxyTargetClass = true)
@EnableTransactionManagement
public class RootConfig {

    @Bean
    public SpringContextHolder springContextHolder(){
        return new SpringContextHolder();
    }
}
