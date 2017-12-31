package cn.nilaile.ssm.dao;

import java.util.List;

import cn.nilaile.ssm.entity.BlogArticleTag;

public interface BlogArticleTagDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogArticleTag record);

    int insertSelective(BlogArticleTag record);

    BlogArticleTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogArticleTag record);

    int updateByPrimaryKey(BlogArticleTag record);
    
    List<BlogArticleTag> findByArticle(String aid);

	String findArticleTagIds(String articleId);
	
	void deleteByIds(String[]  articleTagIds);

	int findByTagId(Integer tagId);
}