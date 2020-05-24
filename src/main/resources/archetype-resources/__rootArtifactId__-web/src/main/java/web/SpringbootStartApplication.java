package ${groupId}.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 请填写类的描述
 *
 * @author makai5
 * @date 2020-02-10 16:17
 */
@SpringBootApplication(scanBasePackages = "${groupId}",
        scanBasePackageClasses = SpringbootStartApplication.class, exclude = {DataSourceAutoConfiguration.class})
public class SpringbootStartApplication extends SpringBootServletInitializer {


    /**
     * 兼容tomcat部署模式
     *
     * @param application spring环境
     * @return SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootStartApplication.class);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringbootStartApplication.class,args);
        System.out.println("/**********************APP-STARTED****************************/");

    }
}