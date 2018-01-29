package cn.nilaile.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nilaile.ssm.dao.BlogArticleDao;
import cn.nilaile.ssm.dao.BlogArticleTagDao;
import cn.nilaile.ssm.entity.BlogArticleTag;
import cn.nilaile.ssm.service.IBlogArticleTagService;

@Service
public class BlogArticleTagServiceImpl implements IBlogArticleTagService{

	@Autowired
	BlogArticleDao blogArticleDao;
	

	@Autowired
	BlogArticleTagDao blogArticleTagDao;
	
	@Override
	public List<BlogArticleTag> getByAid(String aid) {
		return blogArticleTagDao.findByArticle(aid);
	}

	@Override
	public String getByArticleTagIds(String articleId) {
		return blogArticleTagDao.findArticleTagIds(articleId);
	}
	



}
