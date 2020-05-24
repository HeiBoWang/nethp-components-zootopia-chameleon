package ${groupId}.web.configuration.servlet;

import com.jd.fly.angel.eye.servlet.AngelEyeServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 天使之眼注册
 *
 * @author mengfei6
 * @date 2019-10-13 20:40
 */
@Configuration
public class AngelEyeConfig {
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new AngelEyeServlet(), "/angeleye/*");
    }
}
