package ${groupId}.web.configuration;

import com.jd.medicine.base.common.console.ConsoleReqHandler;
import com.jd.medicine.base.common.console.ConsoleSQL;
import com.jd.medicine.base.common.logging.loglevel.ChangeLogLevelReqHandler;
import com.jd.medicine.base.common.util.http.server.ReqHandler;
import com.jd.medicine.base.common.util.http.server.SimpleHttpServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * HttpAgentConfig
 *
 * @author tanwei3
 * @date 2019-07-01 12:33
 */
@Configuration
public class HttpAgentConfig {

    /**
     * consoleSQL
     */
    @Resource
    protected ConsoleSQL consoleSQL;

    @Bean
    public ConsoleReqHandler consoleReqHandler(){
        //控制台sql执行请求处理器 只能执行update，且sql必须是 update xxx set xxx where xxx limt 1
        ConsoleReqHandler consoleReqHandler =  new ConsoleReqHandler();
        consoleReqHandler.setConsoleSQL(this.consoleSQL);
        return consoleReqHandler;
    }

    @Bean
    public ChangeLogLevelReqHandler changeLogLevelReqHandler(){
        //动态日志级别请求处理器
        return new ChangeLogLevelReqHandler();
    }

    @Bean(initMethod = "start")
    public SimpleHttpServer simpleHttpServer(){
        SimpleHttpServer simpleHttpServer = new SimpleHttpServer();
        simpleHttpServer.setPort(37654);

        List<ReqHandler> reqHandlerList = new ArrayList<>(2);

        reqHandlerList.add(changeLogLevelReqHandler());


        simpleHttpServer.setReqHandlerList(reqHandlerList);

        return simpleHttpServer;

    }

}
