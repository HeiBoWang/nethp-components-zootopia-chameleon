package ${groupId}.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 * DuccConfig简介
 *
 * @author anyongcong
 * @date 2020-03-30 14:40
 */
@Configuration
public class DuccConfig {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DuccConfig.class);

    /**
     * test 示例
    @Value("${test}")
    private String test;

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }
     */


}