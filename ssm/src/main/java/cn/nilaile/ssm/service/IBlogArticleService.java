package cn.nilaile.ssm.service;

import java.util.List;

import cn.nilaile.ssm.entity.BlogArticle;

public interface IBlogArticleService {

	public List<BlogArticle> findByPage(BlogArticle article);

	public void save(BlogArticle a, String checkedTags);

	public BlogArticle findById(int aid);

	public void update(BlogArticle a, String checkedTags);

	public void delete(String aids);

	public BlogArticle findDetailById(Integer id);
	
}
