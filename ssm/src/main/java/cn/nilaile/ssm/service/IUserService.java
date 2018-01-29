package cn.nilaile.ssm.service;

import cn.nilaile.ssm.entity.User;

public interface IUserService {

	public User getByUserNameOrMobile(String username);
	public User getByUserName(String username);
	public User getByMobile(String mobile);
	
	public void updateByProper(User u);
	
}
