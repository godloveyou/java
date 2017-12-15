package cn.nilaile.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nilaile.ssm.dao.BlogTagDao;
import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.entity.BlogTag;
import cn.nilaile.ssm.service.IBlogTagService;

@Service
public class BlogCategoryServiceImpl implements IBlogTagService{

	@Autowired
	BlogTagDao blogTagDao;
	
	public List<BlogTag> findAll(){
		return blogTagDao.findAll();
	}

}
