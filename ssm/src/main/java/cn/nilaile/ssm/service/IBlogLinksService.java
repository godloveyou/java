package cn.nilaile.ssm.service;

import java.util.List;

import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.entity.BlogLinks;

public interface IBlogLinksService {
	public List<BlogLinks> findAll();

	public void delete(String ids);

	public BlogLinks findById(Integer id);

	public void update(BlogLinks category);

	public BlogLinks findByName(String cname);

	public void save(BlogLinks t);

}
