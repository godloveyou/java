package cn.nilaile.ssm.service;

import java.util.List;

import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.entity.BlogTag;

public interface IBlogTagService {
	public List<BlogTag> findAll();

}
