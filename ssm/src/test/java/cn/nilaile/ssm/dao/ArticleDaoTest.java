package cn.nilaile.ssm.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.nilaile.ssm.entity.BlogArticle;
import cn.nilaile.ssm.entity.BlogTag;
import cn.nilaile.ssm.entity.User;
import cn.nilaile.ssm.enums.CommonEnum;
import cn.nilaile.ssm.util.Md5SaltUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ArticleDaoTest {
	
	@Autowired
	private BlogArticleDao blogArticleDao;
	
	@Autowired
	private BlogArticleTagDao blogArticleTagDao;
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Test
	@Ignore
	public void testInsert(){
		try {
			BlogArticle b = new BlogArticle();
			b.setCategoryId(1);
			b.setTitle("TestTest");
			b.setContentHtml("This is first blog content");
			b.setClickCount(0);
			b.setBlogDes("blog desc");
			b.setIsRecommend("1");
			b.setPublishDate(new java.util.Date());
			b.setUserId("b63f7040da4311e79c0d7824afc13d01");
			int a = blogArticleDao.insert(b);
			LOG.info("success: "+a);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindByPage(){
		//PageHelper.startPage(1, 1);
		List<BlogArticle> list = blogArticleDao.findByPage(null);
		for(int i=0;i<list.size();i++){
			BlogArticle b = list.get(i);
			LOG.info("COMMENTS: "+b.getListBlogComment().size());
			LOG.info("tagNumber: "+b.getListBlogTags().size());
		}
	}
	
	@Test
	@Ignore
	public void testFindByArticle(){
		BlogArticle a = blogArticleDao.selectByPrimaryKey("1");
		LOG.info("success: "+a.getContentHtml());
	}
	
	@Test
	@Ignore
	public void testDelteByIds(){
		String[] tagIds = {""};
		blogArticleTagDao.deleteByIds(tagIds);
	}
	

}
