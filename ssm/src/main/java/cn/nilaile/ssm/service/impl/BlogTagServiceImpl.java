package cn.nilaile.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nilaile.ssm.dao.BlogCategoryDao;
import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.service.IBlogCategoryService;

@Service
public class BlogTagServiceImpl implements IBlogCategoryService{

	@Autowired
	BlogCategoryDao categoryDao;
	
	public List<BlogCategory> findAll(){
		return categoryDao.findAll();
	}

}
