package ${groupId}.web.configuration.ump;

import com.jd.ump.annotation.JAnnotation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * UmpConfig
 *
 * @author tanwei3
 * @date 2019-07-01 13:48
 */
@Configuration
@EnableConfigurationProperties(UmpProperties.class)
public class UmpConfig {
    /**
     * umpProperties
     */
    private final UmpProperties umpProperties;

    /***/
    public UmpConfig(UmpProperties umpProperties) {
        this.umpProperties = umpProperties;
    }

    @Bean
    @ConditionalOnMissingBean(JAnnotation.class)
    public JAnnotation jAnnotation() {
        if (!umpProperties.checkAppName()) {
            throw new IllegalStateException("appName can not be empty. config by jd.ump.appName");
        }
        JAnnotation annotation = new JAnnotation();
        annotation.setAppName(umpProperties.getAppName());
        annotation.setJvmKey(umpProperties.getJvmKey());
        annotation.setSystemKey(umpProperties.getSystemKey());
        return annotation;
    }

}
