package ${groupId}.web.configuration.jsf;

import com.jd.jsf.gd.config.ProviderConfig;
import com.jd.jsf.gd.config.RegistryConfig;
import com.jd.jsf.gd.config.ServerConfig;
import com.jd.jsf.gd.registry.RegistryFactory;
import com.jd.medicine.base.common.util.StringUtil;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;

/**
 * JsfProviderConfig
 *
 * @author liudehui4
 * @date 2019-09-29 17:55
 */
@Configuration
public class JsfProviderConfig implements EnvironmentAware {
    /**
     * 通用jsf配置的key
     */
    private static final String BASE_ALIAS_KEY = "jsf.provider.alias";
    /**
     * 通用jsf配置的key
     */
    private static final String BASE_TOKEN_KEY = "jsf.provider.token";
    /**
     * propertyResolver
     */
    private PropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = environment;
    }


    /**
     * 构造jsf提供者配置
     * --全部是默认配置。特殊配置 自定义
     *
     * @param tClass         接口
     * @param refImpl        实现类
     * @param registryConfig 注册配置
     */
    private <T> ProviderConfig<T> buildProviderConfig(Class<T> tClass, T refImpl, RegistryConfig registryConfig) {
        return buildProviderConfig(tClass, refImpl, registryConfig, getAliasPropertyValue(), getTokenPropertyValue());
    }

    /**
     * 构造jsf提供者配置
     * --alias /token 可直接传入。特殊配置 自定义
     *
     * @param tClass         接口
     * @param refImpl        实现类
     * @param registryConfig 注册配置
     */
    private <T> ProviderConfig<T> buildProviderConfig(Class<T> tClass, T refImpl, RegistryConfig registryConfig, String alias, String token) {
        // 服务端配置（必须）
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setProtocol("jsf");
        // 服务提供者属性
        ProviderConfig<T> providerConfig = new ProviderConfig<>();
        providerConfig.setInterfaceId(tClass.getName());
        providerConfig.setAlias(alias);
        if (StringUtil.isNotEmpty(token)) {
            providerConfig.setParameter(com.jd.jsf.gd.util.Constants.HIDDEN_KEY_TOKEN, token);
        }

        providerConfig.setRef(refImpl);
        // 多个server用list
        providerConfig.setServer(serverConfig);
        // 如果外面已经有xml配的注册中心，使用providerConfig.setRegistry(RegistryFactory.getRegistryConfigs());
        // (多个registry用list)
        providerConfig.setRegistry(RegistryFactory.getRegistryConfigs());
        // providerConfig.setRegister(false);//打开注释表示不走注册中心
        return providerConfig;
    }

    /**
     * getAliasPropertyValue
     */
    private String getAliasPropertyValue() {
        return this.propertyResolver.getProperty(BASE_ALIAS_KEY);
    }

    /**
     * getTokenPropertyValue
     */
    private String getTokenPropertyValue() {
        return this.propertyResolver.getProperty(BASE_TOKEN_KEY);
    }

    /**
     * 示例
    @Bean
    public RxDispachMsgExportService jsfRxDispachMsgExportServiceProvider(RxDispachMsgExportService rxDispachMsgExportService, RegistryConfig registryConfig) {
        ProviderConfig<RxDispachMsgExportService> providerConfig = buildProviderConfig(RxDispachMsgExportService.class, rxDispachMsgExportService, registryConfig);
        // 暴露及注册服务
        providerConfig.export();
        return rxDispachMsgExportService;
    }
     */
}
