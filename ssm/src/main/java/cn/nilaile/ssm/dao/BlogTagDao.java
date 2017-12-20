package cn.nilaile.ssm.dao;

import cn.nilaile.ssm.entity.BlogTag;
import java.util.*;
public interface BlogTagDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogTag record);

    int insertSelective(BlogTag record);

    BlogTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogTag record);

    int updateByPrimaryKey(BlogTag record);
    
    List<BlogTag> findAll();

	BlogTag findByName(String tagname);
}