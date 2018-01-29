package cn.nilaile.ssm.service;

import java.util.List;

import cn.nilaile.ssm.entity.BlogArticle;
import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.entity.BlogTag;

public interface IBlogTagService {
	public List<BlogTag> list();

	public BlogTag getById(Integer id);

	public void update(BlogTag articleTag);

	public void remove(String tagIds);

	public BlogTag getByName(String tagname);

	public int save(BlogTag t);



}
