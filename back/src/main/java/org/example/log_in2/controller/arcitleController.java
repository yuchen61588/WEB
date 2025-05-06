package org.example.log_in2.controller;


import org.example.log_in2.podo.article;
import org.example.log_in2.service.impl.arcitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@CrossOrigin(origins = "http://localhost:8081")
public class arcitleController {
    @Autowired
    private arcitleService arcitleservice;
    @PostMapping("/load")
    public List<article> loadController(@RequestParam("username") String username){
        if (arcitleservice.loadarcitle(username).isSuccess()){
            return arcitleservice.loadarcitle(username).getArticles();
        }
        else{
            return null;
        }
    }
    @PostMapping("/add")
    public boolean adddata(@RequestBody article article,@RequestParam("username") String username){
        if(arcitleservice.addData(article,username)){
            return true;
        }
        return false;
    }
    @PostMapping("/update")
    public boolean updatedata(@RequestBody article article,@RequestParam("username") String username){
        if(arcitleservice.updateData(article,username)){
            return true;
        }
        return false;
    }
    @PostMapping("/delete")
    public boolean deletedata(@RequestBody article article,@RequestParam("username") String username){
        if(arcitleservice.deleteData(username,article))
        {
            return true;
        }
        return false;
    }
}
