package cn.nilaile.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nilaile.ssm.dao.BlogArticleTagDao;
import cn.nilaile.ssm.dao.BlogCategoryDao;
import cn.nilaile.ssm.dao.BlogTagDao;
import cn.nilaile.ssm.entity.BlogArticleTag;
import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.entity.BlogTag;
import cn.nilaile.ssm.exception.BizException;
import cn.nilaile.ssm.service.IBlogCategoryService;
import cn.nilaile.ssm.service.IBlogTagService;

@Service
public class BlogTagServiceImpl implements IBlogTagService{

	@Autowired
	BlogTagDao blogTagDao;
	
	@Autowired
	BlogArticleTagDao blogArticleTagDao;
	
	public List<BlogTag> findAll(){
		return blogTagDao.findAll();
	}
	
	@Override
	public BlogTag findById(Integer id) {
		return blogTagDao.selectByPrimaryKey(id);
	}

	@Override
	public void update(BlogTag articleTag) {
		blogTagDao.updateByPrimaryKey(articleTag);
	}

	@Transactional
	@Override
	public void delete(String tagIds) {
		String[] tagIdArray = tagIds.split(",");
		for(int i=0;i<tagIdArray.length;i++){
			String tagId = tagIdArray[i];
			int tagCount =  blogArticleTagDao.findByTagId(Integer.parseInt(tagId));
			if(tagCount>0){
				throw new BizException("要删除的标签仍然有文章在使用");
			}
			blogTagDao.deleteByPrimaryKey(Integer.parseInt(tagIdArray[i]));
		}
	}

	@Override
	public BlogTag findByName(String tagname) {
		return blogTagDao.findByName(tagname);
	}

	@Override
	public int save(BlogTag t) {
		int i = blogTagDao.insert(t);
		return i;
	}


}
