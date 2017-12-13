package cn.nilaile.ssm.service;

import java.util.List;

import cn.nilaile.ssm.entity.BlogArticle;

public interface IBlogArticleService {

	public List<BlogArticle> findByPage();
	
}
