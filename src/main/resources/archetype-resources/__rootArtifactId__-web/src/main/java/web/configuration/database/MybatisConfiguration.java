package ${groupId}.web.configuration.database;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * DruidDataSourceConfig
 *
 * @author mengfei
 * @date 2020-03-06 12:33
 */
@Configuration
@MapperScan(basePackages = "${groupId}")
public class MybatisConfiguration {

}
