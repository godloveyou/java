package cn.nilaile.ssm.dao;

import java.util.List;

import cn.nilaile.ssm.entity.BlogCategory;

public interface BlogCategoryDao {
    int deleteByPrimaryKey(Integer cid);

    int insert(BlogCategory record);

    int insertSelective(BlogCategory record);

    BlogCategory selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(BlogCategory record);

    int updateByPrimaryKey(BlogCategory record);
    
    List<BlogCategory> findAll();

	BlogCategory findByName(String cname);
}