package cn.nilaile.ssm.service;

import java.util.List;

import cn.nilaile.ssm.entity.BlogArticle;
import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.entity.BlogTag;

public interface IBlogTagService {
	public List<BlogTag> findAll();

	public BlogTag findById(Integer id);

	public void update(BlogTag articleTag);

	public void delete(String tagIds);

	public BlogTag findByName(String tagname);

	public int save(BlogTag t);



}
