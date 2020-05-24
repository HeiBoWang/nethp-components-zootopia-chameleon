package ${groupId}.web.configuration.jsf;

import com.jd.jsf.gd.config.ConsumerConfig;
import com.jd.jsf.gd.config.RegistryConfig;
import com.jd.jsf.gd.util.Constants;
import com.jd.medicine.base.common.util.StringUtil;
import ${groupId}.common.constants.AppConstant;
import ${groupId}.common.enums.JsfAliasAndTokenEnum;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;

/**
 * JsfConsumerConfig
 *
 * @author tanwei34
 * @date 2019-07-23 17:55
 */
@Configuration
public class JsfConsumerConfig implements EnvironmentAware {
    /**
     * propertyResolver
     */
    private PropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = environment;
    }

    /**
     * 示例
     * @param jsfRegistry
     * @return
    @Bean
    public RxManExportService rxManExportService(RegistryConfig jsfRegistry) {
        ConsumerConfig<RxManExportService> rxManExportService = buildFixedConsumerConfig(jsfRegistry, RxManExportService.class, JsfAliasAndTokenEnum.RX_MAN_EXPORT_SERVICE);
        return rxManExportService.refer();
    }
     */

    /**
     * buildConsumerConfig
     */
    private <T> ConsumerConfig<T> buildFixedConsumerConfig(RegistryConfig jsfRegistry, Class<T> cls, JsfAliasAndTokenEnum jsfAliasAndTokenEnum) {
        ConsumerConfig<T> consumerConfig = new ConsumerConfig<>();
        consumerConfig.setRegistry(jsfRegistry);
        consumerConfig.setInterfaceId(cls.getName());
        consumerConfig.setProtocol("jsf");
        consumerConfig.setTimeout(5000);
        if(StringUtil.isBlank(getProperty(jsfAliasAndTokenEnum.getSuffix()))){
            consumerConfig.setAlias(getProperty(jsfAliasAndTokenEnum.getAlias()));
        } else {
            consumerConfig.setAlias(getProperty(jsfAliasAndTokenEnum.getAlias()) + getProperty(jsfAliasAndTokenEnum.getSuffix()));
        }
        consumerConfig.setParameter(Constants.HIDDEN_KEY_TOKEN, getProperty(jsfAliasAndTokenEnum.getToken()));
        return consumerConfig;
    }

    /**
     * buildConsumerConfig
     */
    private <T> ConsumerConfig<T> buildConsumerConfig(RegistryConfig jsfRegistry, Class<T> cls) {
        ConsumerConfig<T> consumerConfig = new ConsumerConfig<>();
        consumerConfig.setRegistry(jsfRegistry);
        consumerConfig.setInterfaceId(cls.getName());
        consumerConfig.setProtocol("jsf");
        consumerConfig.setTimeout(5000);

        consumerConfig.setAlias(getAliasPropertyValue(cls));
        consumerConfig.setParameter(Constants.HIDDEN_KEY_TOKEN, getTokenPropertyValue(cls));

        return consumerConfig;
    }

    /**
     * 藏经阁格式的consumer
     * buildConsumerConfigForCjg
     */
    private <T> ConsumerConfig<T> buildConsumerConfigForCjg(RegistryConfig jsfRegistry, Class<T> cls) {
        ConsumerConfig<T> consumerConfig = new ConsumerConfig<>();
        consumerConfig.setRegistry(jsfRegistry);
        consumerConfig.setProtocol("jsf");
        consumerConfig.setInterfaceId(cls.getName());
        consumerConfig.setTimeout(3000);
        consumerConfig.setAlias(getAliasPropertyValue(cls));
        consumerConfig.setParameter(".clientName", AppConstant.APP_NAME);
        consumerConfig.setParameter(".authToken", getTokenPropertyValue(cls));

        return consumerConfig;
    }

    /**
     * getProperty
     */
    private String getProperty(String key) {
        return this.propertyResolver.getProperty(key);
    }

    /**
     * getAliasPropertyValue
     */
    private String getAliasPropertyValue(Class cls) {
        return this.propertyResolver.getProperty(cls.getName() + ".alias");
    }

    /**
     * getTokenPropertyValue
     */
    private String getTokenPropertyValue(Class cls) {
        return this.propertyResolver.getProperty(cls.getName() + ".token");
    }

}
