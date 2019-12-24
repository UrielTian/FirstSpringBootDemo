package com.example.firstspringbootdemo.bean.mapper;


import com.example.firstspringbootdemo.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user where name = #{name}")
    public User queryUserByName(@Param("name") String name);

    @Insert("insert into user(name,password) values(#{name},#{password})")
    public int addUser(@Param("name")String name,@Param("password")String password);
}

