package org.example.log_in2.service.impl;

import org.example.log_in2.mapper.arcMapper;
import org.example.log_in2.mapper.articleMapper;
import org.example.log_in2.podo.arc;
import org.example.log_in2.podo.loadarc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class arcService {
    @Autowired
    private arcMapper arcmapper;


    public loadarc loadarcservice(){
        List<arc> map = arcmapper.getArc();
        if(map==null){
            return new loadarc(null,false);
        }
        else{
            return new loadarc(map,true);
        }
    }
    public boolean addarcservice(int id,String username,int number){
        if(arcmapper.insertArc(id,username,number)!=0){
            return true;
        }
        return false;
    }


}
