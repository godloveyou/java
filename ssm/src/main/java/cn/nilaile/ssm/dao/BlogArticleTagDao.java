package cn.nilaile.ssm.dao;

import cn.nilaile.ssm.entity.BlogArticleTag;

public interface BlogArticleTagDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogArticleTag record);

    int insertSelective(BlogArticleTag record);

    BlogArticleTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogArticleTag record);

    int updateByPrimaryKey(BlogArticleTag record);
}