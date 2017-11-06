package com.cppba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author winfed
 * @create 2017-11-06 14:56
 */
@Configuration
public class ThreadPoolConfiguration {
    // 配置线程池
    @Bean
    @Scope("prototype")
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 线程池维护线程的最少数量
        threadPoolTaskExecutor.setCorePoolSize(10);
        // 线程池维护线程的最大数量
        threadPoolTaskExecutor.setMaxPoolSize(20);
        // 线程池维护线程所允许的空闲时间
        threadPoolTaskExecutor.setKeepAliveSeconds(30000);
        // 线程池所使用的缓冲队列
        threadPoolTaskExecutor.setQueueCapacity(1000);
        return threadPoolTaskExecutor;
    }
}
