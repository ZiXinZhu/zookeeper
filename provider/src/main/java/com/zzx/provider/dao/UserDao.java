package com.zzx.provider.dao;

import com.zzx.provider.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    @Select("SELECT * from girl where id=#{id}")
    UserPO findById(@Param("id")int id);
}
