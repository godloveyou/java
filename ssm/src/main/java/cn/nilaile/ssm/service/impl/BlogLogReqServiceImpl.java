package cn.nilaile.ssm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nilaile.ssm.dao.BlogArticleDao;
import cn.nilaile.ssm.dao.BlogArticleTagDao;
import cn.nilaile.ssm.dao.BlogLogReqDao;
import cn.nilaile.ssm.entity.BlogArticle;
import cn.nilaile.ssm.entity.BlogArticleTag;
import cn.nilaile.ssm.entity.BlogLogReq;
import cn.nilaile.ssm.service.IBlogArticleService;
import cn.nilaile.ssm.service.IBlogArticleTagService;
import cn.nilaile.ssm.service.IBlogLogReqService;

@Service
public class BlogLogReqServiceImpl implements IBlogLogReqService{

	@Autowired
	BlogLogReqDao blogLogReqDao;
	
	@Override
	public int insert(BlogLogReq record) {
		return blogLogReqDao.insert(record);
	}


}
