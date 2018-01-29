package cn.nilaile.ssm.dao;

import cn.nilaile.ssm.entity.BlogAd;

public interface BlogAdDao {
    int removeByPrimaryKey(Integer id);

    int save(BlogAd record);

    int saveSelective(BlogAd record);

    BlogAd getById(Integer id);

    int updateByPrimaryKeySelective(BlogAd record);

    int updateByPrimaryKey(BlogAd record);
}