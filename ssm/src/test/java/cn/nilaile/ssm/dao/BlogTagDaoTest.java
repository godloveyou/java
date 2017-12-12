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

import cn.nilaile.ssm.entity.BlogTag;
import cn.nilaile.ssm.entity.User;
import cn.nilaile.ssm.enums.CommonEnum;
import cn.nilaile.ssm.util.Md5SaltUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class BlogTagDaoTest {
	
	@Autowired
	private BlogTagDao blogTagDao;
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testInsert(){
		String[] tags = {"spring","struts2","hibernate"};
		for(int i=0;i<tags.length;i++){
			BlogTag t = new BlogTag();
			t.setTagName(tags[i]);
			blogTagDao.insert(t);
		}
		
		
	}

}
