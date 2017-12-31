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

import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.entity.User;
import cn.nilaile.ssm.enums.CommonEnum;
import cn.nilaile.ssm.util.Md5SaltUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class BlogCategoryDaoTest {
	
	@Autowired
	private BlogCategoryDao blogCategoryDao;
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testInsert(){
//		String[] list = {"java","php","python","delphi"};
//		for(int i=0;i<list.length;i++){
//			BlogCategory c = new BlogCategory();
//			c.setCname(list[i]);
//			c.setCsort(i);
//			blogCategoryDao.insert(c);
//		}
		
	}

}
