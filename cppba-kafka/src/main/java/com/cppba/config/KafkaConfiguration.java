package com.cppba.config;

/**
 * @author winfed
 * @create 2018-06-09 14:30
 */
public abstract class KafkaConfiguration {

    protected String bootstrapServers;

    protected String sslTruststoreLocation;

    private String javaSecurityAuthLoginConfig;

    protected String groupId;

    protected void configureSasl() {
        //如果用-D或者其它方式设置过，这里不再设置
        if (null == System.getProperty("java.security.auth.login.config")) {
            //请注意将XXX修改为自己的路径
            //这个路径必须是一个文件系统可读的路径，不能被打包到jar中
            System.setProperty("java.security.auth.login.config", javaSecurityAuthLoginConfig);
        }
    }
}
