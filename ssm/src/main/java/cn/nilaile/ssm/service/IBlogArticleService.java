package cn.nilaile.ssm.service;

import java.util.List;

import cn.nilaile.ssm.entity.BlogArticle;

public interface IBlogArticleService {

	public List<BlogArticle> list(BlogArticle article);

	public void save(BlogArticle a, String checkedTags);

	public BlogArticle getById(String id);

	public void update(BlogArticle a, String checkedTags);

	public void remove(String aids);

	public BlogArticle getDetailById(String id);

	public void updateById(BlogArticle a);
	
	 void updateClick(BlogArticle a);
}
