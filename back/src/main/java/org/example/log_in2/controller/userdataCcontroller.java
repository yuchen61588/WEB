package org.example.log_in2.controller;

import org.example.log_in2.podo.userData;
import org.example.log_in2.service.impl.userdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
@CrossOrigin(origins = "http://localhost:8081")
public class userdataCcontroller {
    @Autowired
    private userdataService dataservice;
    @PostMapping("/load")
    public List<userData> loadController(@RequestParam("username") String username){
       if (dataservice.loadData(username).isSuccess()){
           return dataservice.loadData(username).getUserData();
       }
       else{
           return null;
       }
    }
    @PostMapping("/add")
    public boolean adddata(@RequestBody userData userdata,@RequestParam("username") String username){
          if(dataservice.addData(userdata,username)){
              return true;
          }
          return false;
    }
    @PostMapping("/update")
    public boolean updatedata(@RequestBody userData userdata,@RequestParam("username") String username){
        if(dataservice.updateData(userdata,username)){
            return true;
        }
        return false;
    }
    @PostMapping("/delete")
    public boolean deletedata(@RequestBody userData userdata,@RequestParam("username") String username){
        if(dataservice.deleteData(username,userdata))
        {
            return true;
        }
        return false;
    }
}
