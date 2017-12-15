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

@Service
public class BlogArticleServiceImpl implements IBlogArticleService{

	@Autowired
	BlogArticleDao blogArticleDao;
	
	@Autowired
	BlogArticleTagDao blogArticleTagDao;
	

	@Override
	public List<BlogArticle> findByPage() {
		return blogArticleDao.findByPage();
	}

	@Transactional
	@Override
	public void save(BlogArticle a,String checkedTags) {
		//1.保存文章
		blogArticleDao.insertSelective(a);
		
		//保存文章分类,文章标签
		List<String> listTagIds = java.util.Arrays.asList(checkedTags.split(","));
		for(int i=0;i<listTagIds.size();i++){
			BlogArticleTag record = new BlogArticleTag();
			record.setArticleId(a.getId());
			record.setTagId(Integer.parseInt(listTagIds.get(i)));
			blogArticleTagDao.insert(record);
		}
	
	}

}
