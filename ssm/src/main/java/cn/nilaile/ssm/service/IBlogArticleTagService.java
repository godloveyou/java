package cn.nilaile.ssm.service;

import java.util.List;

import cn.nilaile.ssm.entity.BlogArticle;
import cn.nilaile.ssm.entity.BlogArticleTag;
import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.entity.BlogTag;

public interface IBlogArticleTagService {
	List<BlogArticleTag> getByAid(String aid);

	String getByArticleTagIds(String id);


}
