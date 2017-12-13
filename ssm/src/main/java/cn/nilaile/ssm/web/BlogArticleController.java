package cn.nilaile.ssm.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.nilaile.ssm.entity.BlogArticle;
import cn.nilaile.ssm.service.IBlogArticleService;

@Controller
@RequestMapping("/boss/article")
public class BlogArticleController {
	
	@Autowired
	IBlogArticleService articleService;
	
	@RequestMapping("/list") 
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("/article/articleList");
		List<BlogArticle> list = articleService.findByPage();
		mv.addObject("articleList", list);
		return mv;
	}
	
	
	

}
