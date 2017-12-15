package cn.nilaile.ssm.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.nilaile.ssm.entity.BlogArticle;
import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.entity.BlogTag;
import cn.nilaile.ssm.entity.User;
import cn.nilaile.ssm.service.IBlogArticleService;
import cn.nilaile.ssm.service.IBlogCategoryService;
import cn.nilaile.ssm.service.IBlogTagService;

@Controller
@RequestMapping("/boss/article")
public class BlogArticleController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IBlogArticleService articleService;
	@Autowired
	IBlogCategoryService categoryService;
	
	@Autowired
	IBlogTagService blogTagService;	
	
	@RequestMapping("/list") 
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("/article/articleList");
		List<BlogArticle> list = articleService.findByPage();
		mv.addObject("articleList", list);
		return mv;
	}
	
	@RequestMapping("/add") 
	public ModelAndView add(){
		ModelAndView mv = new ModelAndView("/article/articleAdd");
		List<BlogCategory> listCategory = categoryService.findAll();
		List<BlogTag> listTag = blogTagService.findAll();
		mv.addObject("listCategory", listCategory);
		mv.addObject("listTag", listTag);
		return mv;
	}
	
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest req,HttpSession session,final RedirectAttributes redirectAttributes){
		User u = (User)session.getAttribute("user");  
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String categoryId = req.getParameter("categoryId");
		String checkedTags = req.getParameter("checkedTags");
		String blogDes = req.getParameter("blogDes");
		
		BlogArticle a = new BlogArticle();
		a.setClickCount(0);
		a.setTitle(title);
		a.setCategoryId(Integer.parseInt(categoryId));
		a.setContent(content);
		a.setDesc(blogDes);
		a.setIsRecommend( req.getParameter("recommend"));
		a.setUserId(u.getUserId());
		a.setPublishDate(new java.util.Date());
		articleService.save(a,checkedTags);
		redirectAttributes.addAttribute("msg", "添加成功");
		return "redirect:/boss/article/list"; 
	}
	

}
