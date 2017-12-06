package com.cppba;


import com.cppba.entity.City;
import com.cppba.entity.User;
import com.cppba.vo.UserVo;
import org.modelmapper.ModelMapper;

public class Main {

    //public static String[] genders={"男","女","未知"};

    public static void main(String[] args) {
        //模拟数据库查询出来的数据
        User user = new User(1L,"jack","123456",1,2L);
        City city = new City(2L,"重庆",0L);

        /*UserVo userVo = new UserVo();
        userVo.setUserName(user.getUserName());
        userVo.setId(user.getId());
        userVo.setCityName(city.getName());
        userVo.setGender(genders[user.getGender()]);*/

        System.out.println(user.toString());
        System.out.println(city.toString());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(UserVo.UserToUserVoMap);
        modelMapper.addMappings(UserVo.CityToUserVoMap);

        UserVo userVo = new UserVo();
        modelMapper.map(city,userVo);
        modelMapper.map(user,userVo);
        System.out.println(userVo.toString());

    }


}