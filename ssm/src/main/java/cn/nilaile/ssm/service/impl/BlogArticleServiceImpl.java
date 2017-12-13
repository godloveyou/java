package cn.nilaile.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nilaile.ssm.dao.BlogArticleDao;
import cn.nilaile.ssm.dao.UserDao;
import cn.nilaile.ssm.entity.BlogArticle;
import cn.nilaile.ssm.entity.User;
import cn.nilaile.ssm.service.IBlogArticleService;
import cn.nilaile.ssm.service.IUserService;
import cn.nilaile.ssm.util.AccountValidatorUtil;

@Service
public class BlogArticleServiceImpl implements IBlogArticleService{

	@Autowired
	BlogArticleDao blogArticleDao;
	

	@Override
	public List<BlogArticle> findByPage() {
		return blogArticleDao.findByPage();
	}

}
