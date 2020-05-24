package ${groupId}.common.enums;

/**
 * 读取配置文件jsf配置
 *
 * @author zhaobaolin1
 * @date 2019/11/21 14:44
 */
public enum JsfAliasAndTokenEnum {

    /**
     * 权限系统jsf配置
     */
    USER_AUTH("medicine.authorization.center.export.alias", "medicine.authorization.center.export.token", "medicine.authorization.center.export.suffix"),

    ;

    /**
     * alias
     */
    private String alias;
    /**
     * token
     */
    private String token;

    /**
     * suffix
     */
    private String suffix;

    /**
     * 构造方法
     *
     */
    JsfAliasAndTokenEnum(String alias, String token, String suffix) {
        this.alias = alias;
        this.token = token;
        this.suffix = suffix;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
