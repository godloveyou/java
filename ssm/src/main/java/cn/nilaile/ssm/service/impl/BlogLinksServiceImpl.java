package cn.nilaile.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nilaile.ssm.dao.BlogLinksDao;
import cn.nilaile.ssm.entity.BlogLinks;
import cn.nilaile.ssm.service.IBlogLinksService;

@Service
public class BlogLinksServiceImpl implements IBlogLinksService{

	@Autowired
	BlogLinksDao bloglinksDao;

	@Override
	public List<BlogLinks> list() {
		return bloglinksDao.findAll();
	}

	@Transactional
	@Override
	public void remove(String ids) {
		 String[] idArray = ids.split(",");
		 for(int i=0;i<idArray.length;i++){
			 bloglinksDao.deleteByPrimaryKey(Integer.parseInt(idArray[i]));
		 }
	}

	@Override
	public BlogLinks getById(Integer id) {
		return bloglinksDao.selectByPrimaryKey(id);
	}

	@Override
	public void update(BlogLinks category) {
		bloglinksDao.updateByPrimaryKey(category);
	}

	@Override
	public BlogLinks getByName(String cname) {
		
		return bloglinksDao.findByName(cname);
	}

	@Override
	public void save(BlogLinks t) {
		bloglinksDao.insert(t);
	}
}
