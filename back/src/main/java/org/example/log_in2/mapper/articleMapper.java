package org.example.log_in2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.log_in2.podo.article;

import java.util.List;

@Mapper
public interface articleMapper {
    List<article> getarticleByusername(@Param("username")String username);
    int insertarticle(@Param("article")article Article,@Param("username")String username);
    int updatearticle(@Param("article") article Article, @Param("username")String username);
    int deletearticle(@Param("username")String username,@Param("article")article Article);
}
