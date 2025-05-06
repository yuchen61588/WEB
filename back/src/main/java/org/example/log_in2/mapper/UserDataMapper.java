package org.example.log_in2.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.log_in2.podo.userData;

import java.util.List;

@Mapper
public interface UserDataMapper {
    List<userData> getUserDataByusername(@Param("username")String username);
    int insertUserData(@Param("userData")userData userData,@Param("username")String username);
    int updateUserData(@Param("userData")userData userData,@Param("username")String username);
    int deleteUserData(@Param("username")String username,@Param("userData")userData userData);
}
