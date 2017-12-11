package cn.nilaile.ssm.service;

import cn.nilaile.ssm.entity.User;

public interface IUserService {

	public User login(String username);
	public User findByUserName(String username);
	public User findByMobile(String mobile);
	
}
