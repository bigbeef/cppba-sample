package com.cppba.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    private Long id;
    private String userName;
    private String password;
    private Integer gender;//0-未知，1-男，2-女
    private Long cityId;
}