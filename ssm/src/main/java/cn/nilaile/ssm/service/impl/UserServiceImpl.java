package cn.nilaile.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nilaile.ssm.dao.UserDao;
import cn.nilaile.ssm.entity.User;
import cn.nilaile.ssm.service.IUserService;
import cn.nilaile.ssm.util.AccountValidatorUtil;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public User findByUserName(String username) {
		
		return userDao.findByUserName(username);
	}

	@Override
	public User findByMobile(String mobile) {
		return userDao.findByMobile(mobile);
	}

	@Override
	public User login(String username) {
		if(AccountValidatorUtil.isMobile(username)){
			return this.findByMobile(username);
		}
		
		return this.findByUserName(username);
	}

	

}
