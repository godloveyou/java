package cn.nilaile.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.nilaile.ssm.dao.BlogLogReqDao;
import cn.nilaile.ssm.entity.BlogLogReq;
import cn.nilaile.ssm.service.IBlogLogReqService;

@Service
public class BlogLogReqServiceImpl implements IBlogLogReqService{

	@Autowired
	BlogLogReqDao blogLogReqDao;
	
	@Override
	public int save(BlogLogReq record) {
		return blogLogReqDao.insert(record);
	}


}
