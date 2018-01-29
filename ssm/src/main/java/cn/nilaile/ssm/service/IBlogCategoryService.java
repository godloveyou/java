package cn.nilaile.ssm.service;

import java.util.List;

import cn.nilaile.ssm.entity.BlogCategory;

public interface IBlogCategoryService {
	public List<BlogCategory> list();

	public void remove(String ids);

	public BlogCategory getById(Integer id);

	public void update(BlogCategory category);

	public BlogCategory getByName(String cname);

	public void save(BlogCategory t);

}
