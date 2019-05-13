package com.cppba;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 说明：elasticsearch
 *
 * @author winfed
 **/
@Configuration
public class ElasticSearchConfig {

    @Value("${spring.data.elasticsearch.host}")
    private String  host;
    @Value("${spring.data.elasticsearch.port}")
    private Integer port;
    @Value("${spring.data.elasticsearch.user}")
    private String  user;
    @Value("${spring.data.elasticsearch.pass}")
    private String  pass;

    @Bean
    public RestClientBuilder restClientBuilder() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port));
        if (StringUtils.isNotBlank(user)) {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, pass));
            builder.setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        }
        return builder;
    }

    @Bean
    public RestHighLevelClient highLevelClient(@Autowired RestClientBuilder restClientBuilder) {
        return new RestHighLevelClient(restClientBuilder);
    }
}
