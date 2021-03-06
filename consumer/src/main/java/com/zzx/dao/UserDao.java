package com.zzx.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    @Update("Update girl set age=#{age} where id=#{id}")
    int update(@Param("id")int id,@Param("age")int age);

    @Select("SELECT age from girl where id=#{id}")
    int findAge(@Param("id") int id);
}
