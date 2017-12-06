package com.cppba.vo;

import com.cppba.entity.City;
import com.cppba.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

@Getter
@Setter
@ToString
public class UserVo {
    private Long id;
    private String UserName;
    private String cityName;
    private String gender;


    public static String[] genders={"男","女","未知"};

    /**
     * User转UserVo自定义映射规则
     */
    public static PropertyMap<User, UserVo> UserToUserVoMap = new PropertyMap<User, UserVo>() {
        protected void configure() {
            using(toGender).map(source.getGender(),destination.getGender());
        }
    };

    /**
     * 自定义转换规则,将int的genderId翻译为String类型的gender，如1-->"女"
     */
    public static Converter<Integer, String> toGender = new AbstractConverter<Integer, String>() {
        protected String convert(Integer genderId) {
            return genders[genderId];
        }
    };

    /**
     * City转UserVo自定义映射规则
     */
    public static PropertyMap<City, UserVo> CityToUserVoMap = new PropertyMap<City, UserVo>() {
        protected void configure() {
            map(source.getName(),destination.getCityName());
        }
    };
}