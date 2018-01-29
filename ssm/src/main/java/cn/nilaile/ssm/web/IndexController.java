package cn.nilaile.ssm.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.nilaile.ssm.entity.BlogArticle;
import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.entity.BlogLogReq;
import cn.nilaile.ssm.entity.BlogTag;
import cn.nilaile.ssm.exception.BizException;
import cn.nilaile.ssm.service.IBlogArticleService;
import cn.nilaile.ssm.service.IBlogCategoryService;
import cn.nilaile.ssm.service.IBlogLogReqService;
import cn.nilaile.ssm.service.IBlogTagService;
import cn.nilaile.ssm.util.IpUtil;
import cn.nilaile.ssm.web.admin.BlogArticleController;

/**
 * 首页控制
 * @author david
 *
 */
@Controller
public class IndexController {
	private static Log log = LogFactory.getLog(IndexController.class);
	
	@Autowired
	IBlogCategoryService categorService;
	
	@Autowired
	IBlogArticleService  articleService;
	
	@Autowired
	IBlogLogReqService blogLogReqService;
	
	@Autowired
	IBlogTagService iBlogTagService;
	
	@RequestMapping(value={"/","/index"})
	public ModelAndView index(HttpServletRequest req){
		ModelAndView mv = new ModelAndView("/front/index");
		
		mv.addObject("categoryName", "最新文章");
		List<BlogCategory> categorys = categorService.list();
		mv.addObject("listCategory", categorys);
		
		List<BlogArticle> listArticle = articleService.list(null);
		mv.addObject("listArticle", listArticle);
		
		BlogLogReq r = new BlogLogReq();
		r.setReqhead("");
		r.setReqip(IpUtil.getClientIp(req));
		r.setReqtime(new java.util.Date());
		blogLogReqService.save(r);
		return mv;
	}
	
	@RequestMapping("/article/{id}")
	public ModelAndView articleDetail(@PathVariable("id") String id){
		
		ModelAndView mv = new ModelAndView("/front/article");
		BlogArticle a = articleService.getDetailById(id);
		List<BlogCategory> categorys = categorService.list();
		mv.addObject("listCategory", categorys);
		mv.addObject("article", a);
		int count = (a.getClickCount()==null)?0:a.getClickCount();
		a.setClickCount(++count);
		articleService.updateClick(a);
		return mv;
	}
	
	@RequestMapping(value="/category/{cname}",method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ModelAndView findByCategory(@PathVariable("cname") String cname){
		BlogCategory c = null;
		try {
			c = categorService.getByName(new String(cname.getBytes("ISO-8859-1"), "utf8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView("/front/index");
		List<BlogCategory> categorys = categorService.list();
		mv.addObject("listCategory", categorys);
		mv.addObject("categoryName", c.getCname());
		
		BlogArticle a = new BlogArticle();
		a.setCategoryId(c.getCid());
		
		List<BlogArticle> listArticle = articleService.list(a);
		mv.addObject("listArticle", listArticle);
		return mv;
	}
	
	@RequestMapping("/tag/{tagname}")
	public ModelAndView findByTagName(@PathVariable("tagname") String tagname){
		BlogTag c = iBlogTagService.getByName(tagname);
		
		ModelAndView mv = new ModelAndView("/front/index");
		List<BlogCategory> categorys = categorService.list();
		mv.addObject("listCategory", categorys);
		mv.addObject("categoryName", c.getTagName());
		
		BlogArticle a = new BlogArticle();
		a.setTagId(c.getId());
		
		List<BlogArticle> listArticle = articleService.list(a);
		mv.addObject("listArticle", listArticle);
		return mv;
	}
	


}
