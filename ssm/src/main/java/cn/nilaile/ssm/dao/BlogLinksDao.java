package cn.nilaile.ssm.dao;

import java.util.List;

import cn.nilaile.ssm.entity.BlogLinks;

public interface BlogLinksDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogLinks record);

    int insertSelective(BlogLinks record);

    BlogLinks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogLinks record);

    int updateByPrimaryKey(BlogLinks record);

	BlogLinks findByName(String cname);

	List<BlogLinks> findAll();
}