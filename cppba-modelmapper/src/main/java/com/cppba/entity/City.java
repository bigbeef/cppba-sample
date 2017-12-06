package com.cppba.entity;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class City {
    private Long id;
    private String name;
    private Long parentId;
}