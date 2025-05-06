package org.example.log_in2.service.impl;

import org.example.log_in2.mapper.UserMapper;
import org.example.log_in2.podo.LoginResponse;
import org.example.log_in2.podo.changePassword;
import org.example.log_in2.podo.registerResponse;
import org.example.log_in2.podo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoService {
    @Autowired
    private UserMapper userMapper;
    //用户注册
    public registerResponse register(user user) {
        if(userMapper.findUserByUsername(user.getUsername())!=null){
            registerResponse response = new registerResponse(false,"用户名已存在");
            return  response;
        }
        if(user.getPassword().equals(user.getConfirmPassword())){
            userMapper.addUser(user);
            registerResponse response = new registerResponse(true,"注册成功");
            return  response;
        }
        else{
            registerResponse response = new registerResponse(false,"密码和确认密码不一致");
            return  response;
        }
    }
    //用户登录
    public LoginResponse login(String username, String password) {
        user existUser =  userMapper.findUserByUsername(username);
        if(existUser!=null&&userMapper.findPassword(username,password)==true){
            LoginResponse response = new LoginResponse(true,"登陆成功" ,existUser.getEmail(),existUser.getUrl(),existUser.getBirthday());
            return response;
        }
        else{
            LoginResponse response = new LoginResponse(false,"账号或密码错误",null,null,null);
            return response;
        }
    }
    //修改密码
    public changePassword changePassword(String username, String oldPassword, String newPassword) {
        user existUser = userMapper.findUserByUsername(username);
        if (existUser != null && userMapper.findPassword(username, oldPassword)) {
            if (existUser.getPassword().equals(newPassword)) {
                return new changePassword(false, "旧密码与新密码相同");
            }
            if (userMapper.updatePassword(username, newPassword)) {
                return new changePassword(true, "密码修改成功");
            } else {
                return new changePassword(false, "密码修改失败");
            }
        }
        return new changePassword(false, "旧密码错误");
    }
    public LoginResponse load(String username){
        user existUser =  userMapper.findUserByUsername(username);

        LoginResponse response = new LoginResponse(true,"登陆成功" ,existUser.getEmail(),existUser.getUrl(),existUser.getBirthday());
        return response;


    }
}
