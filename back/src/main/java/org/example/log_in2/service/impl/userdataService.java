package org.example.log_in2.service.impl;

import org.example.log_in2.mapper.UserDataMapper;
import org.example.log_in2.podo.loaddata;
import org.example.log_in2.podo.userData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userdataService {
    @Autowired
    private UserDataMapper userDataMapper;
    public loaddata loadData(String username){
         List map = userDataMapper.getUserDataByusername(username);
         if(map == null){
             return new loaddata(map,false);
         }
         else{
             return new loaddata(map,true);
         }
    }
    public boolean addData(userData userData,String username){
       if(userDataMapper.insertUserData(userData,username)!=0){
           return true;
       }
       else{
           return false;
       }
    }
    public boolean updateData(userData userData,String username){
        if(userDataMapper.updateUserData(userData,username)!=0){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean deleteData(String username,userData userData){
        if(userDataMapper.deleteUserData(username,userData)!=0){
            return true;
        }
        else{
            return false;
        }
    }

}
