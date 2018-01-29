package cn.nilaile.ssm.service;

import java.util.List;

import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.entity.BlogLinks;

public interface IBlogLinksService {
	public List<BlogLinks> list();

	public void remove(String ids);

	public BlogLinks getById(Integer id);

	public void update(BlogLinks category);

	public BlogLinks getByName(String cname);

	public void save(BlogLinks t);

}
