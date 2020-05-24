package ${groupId}.web.configuration.threadpool;

import com.jd.nethp.components.zootopia.joy.core.concurrent.JoyThreadPoolTaskExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 请填写类的描述
 *
 * @author makai5
 * @date 2020-04-04 18:29
 */
@Configuration
public class JoyThreadPoolTaskExecutorConfig {

    /**
     * 线程名称格式化
     */
    private final static String scan_thread_format= "DISPATCH-KEY-SCAN-THREAD-%d";
    /**
     * <bean id="coreTaskExecutor" class="com.jd.nethp.components.zootopia.joy.core.concurrent.JoyThreadPoolTaskExecutor">
     *         <!-- 线程池维护线程的最少数量 -->
     *         <property name="corePoolSize" value="5" />
     *         <!-- 允许的空闲时间 -->
     *         <property name="keepAliveSeconds" value="200" />
     *         <!-- 线程池维护线程的最大数量 -->
     *         <property name="maxPoolSize" value="50" />
     *         <!-- 缓存队列 -->
     *         <property name="queueCapacity" value="1024" />
     *         <!-- 对拒绝task的处理策略 -->
     *         <property name="rejectedExecutionHandler">
     *             <bean class="java.util.concurrent.ThreadPoolExecutor$CallerRunsPolicy" />
     *         </property>
     *     </bean>
     * @return
     */
    @Bean
    public JoyThreadPoolTaskExecutor joyThreadPoolTaskExecutor() {
        JoyThreadPoolTaskExecutor joyThreadPoolTaskExecutor = new JoyThreadPoolTaskExecutor();
        joyThreadPoolTaskExecutor.setCorePoolSize(2);
        joyThreadPoolTaskExecutor.setKeepAliveSeconds(200);
        joyThreadPoolTaskExecutor.setMaxPoolSize(50);
        joyThreadPoolTaskExecutor.setQueueCapacity(400);
        joyThreadPoolTaskExecutor.setThreadFactoryNameFormat(scan_thread_format);
        joyThreadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return joyThreadPoolTaskExecutor;
    }
}
