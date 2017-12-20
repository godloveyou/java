package cn.nilaile.ssm.service;

import java.util.List;

import cn.nilaile.ssm.entity.BlogCategory;

public interface IBlogCategoryService {
	public List<BlogCategory> findAll();

	public void delete(String ids);

	public BlogCategory findById(Integer id);

	public void update(BlogCategory category);

	public BlogCategory findByName(String cname);

	public void save(BlogCategory t);

}
