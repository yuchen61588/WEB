package org.example.log_in2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.log_in2.podo.arc;
import org.example.log_in2.podo.article;

import java.util.List;
@Mapper
public interface arcMapper {
    List<arc> getArc();
    int insertArc(@Param("id") int id, @Param("username")String username, @Param("number")int number);
    int addnumber(@Param("username")String username);
    int delnumber(@Param("username")String username);
}
