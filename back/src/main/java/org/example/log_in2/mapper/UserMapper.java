package org.example.log_in2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.log_in2.podo.user;



@Mapper
public interface UserMapper {
    user findUserByUsername(String username);

    boolean findPassword(String username, String password);

    void addUser(user user);

    boolean updatePassword(String username, String newPassword);

}
