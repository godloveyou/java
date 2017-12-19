package cn.nilaile.ssm.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.nilaile.ssm.dao.BlogArticleDao;
import cn.nilaile.ssm.dao.BlogArticleTagDao;
import cn.nilaile.ssm.entity.BlogArticle;
import cn.nilaile.ssm.entity.BlogArticleTag;
import cn.nilaile.ssm.service.IBlogArticleService;
import cn.nilaile.ssm.service.IBlogArticleTagService;

@Service
public class BlogArticleServiceImpl implements IBlogArticleService{

	@Autowired
	BlogArticleDao blogArticleDao;
	
	@Autowired
	BlogArticleTagDao blogArticleTagDao;
	
	@Autowired
	IBlogArticleTagService iBlogArticleTagService;
	
	@Override
	public List<BlogArticle> findByPage(BlogArticle article) {
		return blogArticleDao.findByPage(article);
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

	@Override
	public BlogArticle findById(int aid) {
		return blogArticleDao.selectByPrimaryKey(aid);
	}

	
	@Transactional
	@Override
	public void update(BlogArticle a, String checkedTags) {
		//更新文章
		blogArticleDao.updateByPrimaryKeySelective(a);
		
		//更新标签(删除原始标签+更新为新的标签)
		List<BlogArticleTag> listBlogArticle = iBlogArticleTagService.findByArticle(a.getId());
		for (BlogArticleTag blogArticleTag : listBlogArticle) {
			blogArticleTagDao.deleteByPrimaryKey(blogArticleTag.getId());
		}
		
		List<String> listTagIds = java.util.Arrays.asList(checkedTags.split(","));
		for(int i=0;i<listTagIds.size();i++){
			BlogArticleTag record = new BlogArticleTag();
			record.setArticleId(a.getId());
			record.setTagId(Integer.parseInt(listTagIds.get(i)));
			blogArticleTagDao.insert(record);
		}
		
		
		
	}

	@Transactional
	@Override
	public void delete(String aids) {
		String[] listAids = aids.split(",");
		for(int i=0;i<listAids.length;i++){
			String aid = listAids[i];
			//查询文章所有标签
			List<BlogArticleTag> tags = blogArticleTagDao.findByArticle(Integer.parseInt(aid));
			
			//删除标签
			for(int j=0;j<tags.size();j++){
				BlogArticleTag t = blogArticleTagDao.selectByPrimaryKey(tags.get(j).getId());
				blogArticleTagDao.deleteByPrimaryKey(t.getId());
			}
			
			//删除文章
			blogArticleDao.deleteByPrimaryKey(Integer.parseInt(aid));
		}
		
	}

}
