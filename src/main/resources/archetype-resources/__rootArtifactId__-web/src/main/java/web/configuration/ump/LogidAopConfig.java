package ${groupId}.web.configuration.ump;

import com.jd.medicine.base.common.constant.SystemConstant;
import com.jd.medicine.base.common.logging.rxlog.UniqRequestIdGen;
import com.jd.medicine.base.common.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * LogidAopConfig
 *
 * @author tanwei3
 * @date 2019-07-05 22:00
 */
@Component
@Aspect
public class LogidAopConfig {

    @Pointcut("execution (public * ${groupId}..*.*(..))")
    public void pointcut() {
    }

    /**
     * 记录调用入口方法标识，防止调用链中多个方法匹配到此切面时，logId发生变化
     */
    private final ThreadLocal<String> traceTag = new ThreadLocal<String>();

    @Around("pointcut()")
    public Object execPointcut(ProceedingJoinPoint jp) throws Throwable {
        Object result = null;
        try {
            //为防止切面多次切入,多次修改LogId,因而用ThreadLocal保存jp,只在首层设置logId
            if (traceTag.get() == null) {
                traceTag.set(jp.toString());
                //在本增强之前天使之眼也有可能会设置一次logId,因而如果上游系统已经设置了logId,则以上游系统设置为准,不再添加logId
                //MDC由日志框架保证本身就是线程绑定的
                if (StringUtil.isBlank(MDC.get(SystemConstant.RX_LOGID))) {
                    MDC.put(SystemConstant.RX_LOGID, UniqRequestIdGen.generateReqId() + "-");
                }
            }
            //业务处理
            result = jp.proceed();
        } finally {
            if (jp.toString().equals(traceTag.get())) {
                traceTag.remove();
                MDC.remove(SystemConstant.RX_LOGID);
            }
        }
        return result;
    }
}
