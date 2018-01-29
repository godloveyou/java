package cn.nilaile.ssm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.nilaile.ssm.entity.BlogArticle;

public interface BlogArticleDao {
    int removeById(String id);

    int save(BlogArticle record);


    BlogArticle getById(String id);

    int updateByPrimaryKeySelective(BlogArticle record);

    int updateByPrimaryKeyWithBLOBs(BlogArticle record);

    int updateByPrimaryKey(BlogArticle record);
    
    List<BlogArticle> list(BlogArticle blogArticle);
    
    BlogArticle getDetailById(String id);

	void updateById(BlogArticle a);
	
	void updateClick(BlogArticle a);

}