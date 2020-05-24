package ${groupId}.web.configuration.tomcat;

import com.jd.security.tomcat.JDJspServlet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.Jsp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * Tomcat安全加固-防御jsp木马（WEBAPP层部署方式）
 * https://cf.jd.com/pages/viewpage.action?pageId=107475162&focusedCommentId=114747061
 *
 * @author tanwei3
 * @date 2019-07-01 12:33
 */
@Configuration
public class JspConfiguration {

    /**
     * 使用外部tomcat启动的情况
     *
     * @return
     * @author lanyushi
     */
    @Bean
    @Conditional(OuterTomcatCondition.class)
    public ServletRegistrationBean jdJspServletRegistration() {
        JDJspServlet servlet = new JDJspServlet();
        servlet.setSpringBoot(true);
        ServletRegistrationBean registration = new ServletRegistrationBean();
        registration.setServlet(servlet);
        // jsp功能开关 开启jsp功能 请将false修改为true（配置为true安全防护将失效，不建议修改）
        registration.addInitParameter("enableJsp", "false");
        registration.addInitParameter("fork", "false");
        registration.addInitParameter("xpoweredBy", "false");
        registration.addInitParameter("springboot", "true");
        registration.setLoadOnStartup(3);
        registration.addUrlMappings("*.jsp");
        registration.addUrlMappings("*.jspx");
        registration.setName("jsp");
        return registration;
    }


    /**
     * 使用springboot自带的tomcat启动的情况
     *
     * @author lanyushi
     * @since 2018/8/13 15:28
     */
    @Conditional(InnerTomcatCondition.class)
    @Configuration
    public static class ResolveTomcatServletWebServerFactory implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof TomcatServletWebServerFactory) {
                Jsp jsp = ((TomcatServletWebServerFactory) bean).getJsp();
                jsp.setClassName(JDJspServlet.class.getName());
                Map<String, String> map = jsp.getInitParameters();
                map.put("enableJsp", "false");
                map.put("xpoweredBy", "false");
                map.put("springboot", "true");
            }
            return bean;
        }

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            return bean;
        }
    }

    public static class InnerTomcatCondition implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            return getClass().getClassLoader().equals(org.apache.catalina.startup.Tomcat.class.getClassLoader());
        }
    }

    public static class OuterTomcatCondition implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            return !getClass().getClassLoader().equals(org.apache.catalina.startup.Tomcat.class.getClassLoader());
        }
    }

}