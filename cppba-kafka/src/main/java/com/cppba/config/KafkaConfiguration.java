package com.cppba.config;

import com.cppba.KafkaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author winfed
 * @create 2018-06-09 14:30
 */
@Configuration
public class KafkaConfiguration {

    @Autowired
    protected KafkaProperties kafkaProperties;

    protected void configureSasl() {
        //如果用-D或者其它方式设置过，这里不再设置
        if (null == System.getProperty("java.security.auth.login.config")) {
            //请注意将XXX修改为自己的路径
            //这个路径必须是一个文件系统可读的路径，不能被打包到jar中
            System.setProperty("java.security.auth.login.config", kafkaProperties.getJavaSecurityAuthLoginConfig());
        }
    }
}
