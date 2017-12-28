package cn.nilaile.ssm.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.nilaile.ssm.entity.BlogArticle;
import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.service.IBlogArticleService;
import cn.nilaile.ssm.service.IBlogCategoryService;

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
	
	@RequestMapping(value={"/","/index"})
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("/front/index");
		
		List<BlogCategory> categorys = categorService.findAll();
		mv.addObject("listCategory", categorys);
		
		List<BlogArticle> listArticle = articleService.findByPage(null);
		mv.addObject("listArticle", listArticle);
		return mv;
	}
	
	@RequestMapping("/article/{id}")
	public ModelAndView articleDetail(@PathVariable("id") Integer id){
		
		ModelAndView mv = new ModelAndView("/front/article");
		BlogArticle a = articleService.findDetailById(id);
		List<BlogCategory> categorys = categorService.findAll();
		mv.addObject("listCategory", categorys);
		mv.addObject("article", a);
		return mv;
	}
	


}
