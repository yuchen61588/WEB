package org.example.log_in2.controller;

import org.example.log_in2.podo.arc;
import org.example.log_in2.service.impl.arcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/arc")
public class arcController {
    @Autowired
    private arcService arcservice;
    @PostMapping("/getarc")
    public List<arc> loadarc(){
        if(arcservice.loadarcservice().isSuccess()){
            return arcservice.loadarcservice().getArcList();
        }
        else{
            return null;
        }
    }
    @PostMapping("/add")
    public boolean addarc(@RequestParam("id") int id, @RequestParam("username") String username,@RequestParam("number") int number){
        if(arcservice.addarcservice(id,username,number)){
            return true;
        }
        else{
            return false;
        }
    }

}
