package ${groupId}.web.configuration.ump;

import com.jd.medicine.base.common.util.StringUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * UmpProperties
 *
 * @author tanwei3
 * @date 2019-07-01 12:33
 */
@ConfigurationProperties(prefix = "jd.ump")
public class UmpProperties {

    /***/
    private final static String SYSTEM_KEY_SUFFIX = ".systemKey";
    /***/
    private final static String JVM_KEY_SUFFIX = ".jvmKey";

    /***/
    private static String DEPLOY_APP_NAME;

    /***/
    private String appName = "test-ump-name";
    /***/
    private String systemKey;
    /***/
    private String jvmKey;

    static {
        DEPLOY_APP_NAME = System.getProperty("deploy.app.name");
    }

    /***/
    public boolean checkAppName() {
        return getAppName() != null;

    }

    /***/
    public String getAppName() {
        //强制使用 JONE 提供的 appName
        if (StringUtil.isNotEmpty(DEPLOY_APP_NAME)) {
            return DEPLOY_APP_NAME;
        }
        return appName;
    }

    /***/
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /***/
    public String getSystemKey() {
        if (systemKey == null || systemKey.isEmpty()) {
            systemKey = getAppName() + SYSTEM_KEY_SUFFIX;
        }
        return systemKey;
    }

    /***/
    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    /***/
    public String getJvmKey() {
        if (jvmKey == null || jvmKey.isEmpty()) {
            jvmKey = getAppName() + JVM_KEY_SUFFIX;
        }
        return jvmKey;
    }

    /***/
    public void setJvmKey(String jvmKey) {
        this.jvmKey = jvmKey;
    }

}
