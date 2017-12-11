package cn.nilaile.ssm.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.nilaile.ssm.entity.User;
import cn.nilaile.ssm.enums.CommonEnum;
import cn.nilaile.ssm.util.Md5SaltUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testInsert(){
		User u = new User();
		u.setUserName("admin");
		try {
			u.setPassword(Md5SaltUtil.getEncryptedPwd("123456"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		u.setCreateTime(new java.util.Date());
		u.setEmail("823939100@gmail.com");
		u.setMobile("15537954009");
		u.setLastLogin(new java.util.Date());
		u.setIsActive(CommonEnum.ACTIVE_YES.getValue());
		u.setIsStaff(CommonEnum.STAFF_YES.getValue());
		int m = userDao.insert(u);
		LOG.info("记录数:"+m);
	}

}
