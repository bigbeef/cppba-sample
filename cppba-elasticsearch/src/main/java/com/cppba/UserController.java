package com.cppba;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 说明：用户
 *
 * @author winfed
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @RequestMapping("/create")
    public void create() throws IOException {
        User user = new User();
        user.setId(UUID.randomUUID().toString().replace("-", ""));
        user.setUserName("jack");
        user.setCreateDate(new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        IndexRequest indexRequest = new IndexRequest("user").source(objectMapper.writeValueAsString(user), XContentType.JSON);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

}
