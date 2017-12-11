package cn.nilaile.ssm.dao;

import org.apache.ibatis.annotations.Param;

import cn.nilaile.ssm.entity.User;

public interface UserDao {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User findByUserId(@Param("userId") String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User findByUserName(String username);
    
    User findByMobile(String mobile);
}