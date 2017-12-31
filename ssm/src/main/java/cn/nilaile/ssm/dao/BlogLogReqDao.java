package cn.nilaile.ssm.dao;

import cn.nilaile.ssm.entity.BlogLogReq;

public interface BlogLogReqDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogLogReq record);

    int insertSelective(BlogLogReq record);

    BlogLogReq selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogLogReq record);

    int updateByPrimaryKey(BlogLogReq record);
}