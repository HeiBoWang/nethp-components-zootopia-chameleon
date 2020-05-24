package ${groupId}.web.configuration.jimdb;

import com.jd.jim.cli.Cluster;
import com.jd.jim.cli.ReloadableJimClientFactory;
import com.jd.medicine.base.common.spring.service.JimRedisService;
import com.jd.medicine.base.common.spring.service.impl.JimRedisServiceImpl;
import com.jd.medicine.base.common.spring.util.RedisLock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;

/**
 * JimdbConfig
 *
 * @author tanwei3
 * @date 2019-07-01 14:35
 */
@Configuration
public class JimdbConfig implements EnvironmentAware {
    /**
     * propertyResolver
     */
    private PropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = environment;
    }

    @Bean(name = "jimConfigClient")
    public Cluster jimConfigClient() {
        ReloadableJimClientFactory factory = new ReloadableJimClientFactory();
        factory.setJimUrl(this.propertyResolver.getProperty("jd.jimdb.lua.jimurl"));
        factory.setConfigId("0");
        return factory.getClient();
    }

    @Bean
    public JimRedisService jimGwConfigRedisService(@Qualifier("jimConfigClient") Cluster cluster) {
        JimRedisServiceImpl jimRedisService = new JimRedisServiceImpl();
        jimRedisService.setJimClient(cluster);
        return jimRedisService;
    }

    @Bean
    public RedisLock redisLock(@Qualifier("jimConfigClient") Cluster cluster) {
        RedisLock redisLock = new RedisLock();
        redisLock.setJimClient(cluster);
        return redisLock;
    }
}
