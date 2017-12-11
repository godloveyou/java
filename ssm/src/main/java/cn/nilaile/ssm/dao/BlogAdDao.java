package cn.nilaile.ssm.dao;

import cn.nilaile.ssm.entity.BlogAd;

public interface BlogAdDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogAd record);

    int insertSelective(BlogAd record);

    BlogAd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogAd record);

    int updateByPrimaryKey(BlogAd record);
}