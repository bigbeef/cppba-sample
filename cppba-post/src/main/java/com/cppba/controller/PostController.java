package com.cppba.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class PostController {


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseBody
    //public String post(@RequestBody String people) { // post json
    //public String post(String people) { // post form
    public String post(String people,HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        System.out.println(12345);
        return "success";
    }
}
