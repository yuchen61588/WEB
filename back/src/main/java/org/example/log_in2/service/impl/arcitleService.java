package org.example.log_in2.service.impl;


import org.example.log_in2.mapper.arcMapper;
import org.example.log_in2.mapper.articleMapper;
import org.example.log_in2.podo.arc;
import org.example.log_in2.podo.article;
import org.example.log_in2.podo.articledata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class arcitleService {
    @Autowired
    private articleMapper articlemapper;
    @Autowired
    private arcMapper arcmapper;
    public articledata loadarcitle(String username){
        List map = articlemapper.getarticleByusername(username);
        if(map == null){
            return new articledata(map,false);
        }
        else{
            return new articledata(map,true);
        }
    }
    public boolean addData(article article ,String username){
        if(articlemapper.insertarticle (article,username)!=0){
           if( arcmapper.addnumber(username)!=0)
           {    return true;   }
        }
        return false;
    }
    public boolean updateData(article article,String username){
        if(articlemapper.updatearticle(article,username)!=0){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean deleteData(String username,article article){
        if(articlemapper.deletearticle(username,article)!=0){
            List<arc> map =arcmapper.getArc();//注明类型
            for(arc arcs:map){
                if(arcs.getUsername().equals(username)){
                    if(arcs.getNumber()>0){
                        if( arcmapper.delnumber(username)!=0)
                        {
                            return true;
                        }
                    }
                }
            }


        }
        return false;
    }
}
