package cn.nilaile.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nilaile.ssm.dao.BlogCategoryDao;
import cn.nilaile.ssm.dao.BlogTagDao;
import cn.nilaile.ssm.entity.BlogArticle;
import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.entity.BlogTag;
import cn.nilaile.ssm.service.IBlogCategoryService;
import cn.nilaile.ssm.service.IBlogTagService;

@Service
public class BlogCategoryServiceImpl implements IBlogCategoryService{

	@Autowired
	BlogCategoryDao blogCategoryDao;

	@Override
	public List<BlogCategory> findAll() {
		return blogCategoryDao.findAll();
	}

	@Transactional
	@Override
	public void delete(String ids) {
		 String[] idArray = ids.split(",");
		 for(int i=0;i<idArray.length;i++){
			 blogCategoryDao.deleteByPrimaryKey(Integer.parseInt(idArray[i]));
		 }
	}

	@Override
	public BlogCategory findById(Integer id) {
		return blogCategoryDao.selectByPrimaryKey(id);
	}

	@Override
	public void update(BlogCategory category) {
		blogCategoryDao.updateByPrimaryKey(category);
	}

	@Override
	public BlogCategory findByName(String cname) {
		
		return blogCategoryDao.findByName(cname);
	}

	@Override
	public void save(BlogCategory t) {
		blogCategoryDao.insert(t);
	}
}
