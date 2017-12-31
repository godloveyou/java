package cn.nilaile.ssm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.nilaile.ssm.entity.BlogArticle;

public interface BlogArticleDao {
    int deleteByPrimaryKey(String id);

    int insert(BlogArticle record);


    BlogArticle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BlogArticle record);

    int updateByPrimaryKeyWithBLOBs(BlogArticle record);

    int updateByPrimaryKey(BlogArticle record);
    
    List<BlogArticle> findByPage(BlogArticle blogArticle);
    
    BlogArticle findDetailById(String id);

	void updateById(BlogArticle a);
	
	void updateClick(BlogArticle a);

}