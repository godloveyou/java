package cn.nilaile.ssm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nilaile.ssm.dao.BlogArticleDao;
import cn.nilaile.ssm.dao.BlogArticleTagDao;
import cn.nilaile.ssm.entity.BlogArticle;
import cn.nilaile.ssm.entity.BlogArticleTag;
import cn.nilaile.ssm.service.IBlogArticleService;
import cn.nilaile.ssm.service.IBlogArticleTagService;

@Service
public class BlogArticleTagServiceImpl implements IBlogArticleTagService{

	@Autowired
	BlogArticleDao blogArticleDao;
	

	@Autowired
	BlogArticleTagDao blogArticleTagDao;
	
	@Override
	public List<BlogArticleTag> findByArticle(int aid) {
		return blogArticleTagDao.findByArticle(aid);
	}

	@Override
	public String findArticleTagIds(Integer articleId) {
		// TODO Auto-generated method stub
		return blogArticleTagDao.findArticleTagIds(articleId);
	}
	



}
