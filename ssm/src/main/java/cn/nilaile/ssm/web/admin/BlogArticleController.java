package cn.nilaile.ssm.web.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

import cn.nilaile.ssm.entity.BlogArticle;
import cn.nilaile.ssm.entity.BlogArticleTag;
import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.entity.BlogTag;
import cn.nilaile.ssm.entity.User;
import cn.nilaile.ssm.service.IBlogArticleService;
import cn.nilaile.ssm.service.IBlogArticleTagService;
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
	
	@Autowired
	IBlogArticleTagService IBlogArticleTagService;
	
	@RequestMapping(value="/delete/{aids}") 
	public String delete(@PathVariable("aids") String aids, final RedirectAttributes redirectAttributes){
		articleService.delete(aids);
		redirectAttributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/boss/article/list";
	}
	
	/**
	 * spring的编码过滤器之针对post表单请求可以过滤处理，get无效
	 * @param curr
	 * @param req
	 * @return
	 */
	@RequestMapping("/list") 
	public ModelAndView list(String title,Integer curr){
		
		curr = curr==null?1:curr;
		ModelAndView mv = new ModelAndView("/boss/article/articleList");
		BlogArticle a = new BlogArticle();
		if(StringUtils.isNoneBlank(title)){
			a.setTitle(title);
		}
		PageHelper.startPage(curr, 20);
		List<BlogArticle> list = articleService.findByPage(a);
		//用PageInfo对结果进行包装
		PageInfo<BlogArticle> page = new PageInfo<BlogArticle>(list);
	
		mv.addObject("articleList", list);
		mv.addObject("pageNum",page.getPageNum()); //当前页
		mv.addObject("total",page.getTotal()); //总条数
		mv.addObject("totalPage",page.getPages()); //总页数
		mv.addObject("curr",page.getPageNum());
		
		
		return mv;
	}

	
	public ModelAndView loadCommonData(ModelAndView mv){
		List<BlogCategory> listCategory = categoryService.findAll();
		List<BlogTag> listTag = blogTagService.findAll();
		mv.addObject("listCategory", listCategory);
		mv.addObject("listTag", listTag);
		return mv;
	}
	
	@RequestMapping(value="/edit/{id}",method = RequestMethod.GET) 
	public ModelAndView edit(@PathVariable("id") String id){
		ModelAndView mv = new ModelAndView("/boss/article/articleEdit");
		try {
			BlogArticle article = this.articleService.findById(Integer.parseInt(id));
			mv.addObject("article",article);
			String articleTagIds = IBlogArticleTagService.findArticleTagIds(article.getId());
			mv.addObject("articleTagIds", articleTagIds);
		    loadCommonData(mv);
		} catch (Exception e) {
			LOG.error("exception-->"+e);
		}
		return mv;
	}
	
	
	@RequestMapping(value = "/doEdit",method = RequestMethod.POST)
	public String doEdit(HttpServletRequest req,final RedirectAttributes redirectAttributes){
		String aid = req.getParameter("id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String categoryId = req.getParameter("categoryId");
		String checkedTags = req.getParameter("checkedTags");
		String blogDes = req.getParameter("blogDes");
		System.out.println("content======"+content);
		BlogArticle a = new BlogArticle();
		a.setId(Integer.parseInt(aid));
		a.setTitle(title);
		a.setCategoryId(Integer.parseInt(categoryId));
		a.setContent(content);
		a.setBlogDes(blogDes);
		a.setIsRecommend( req.getParameter("recommend"));
		
		articleService.update(a,checkedTags);
		redirectAttributes.addFlashAttribute("msg", "编辑成功");
		return "redirect:/boss/article/list"; 
	}
	
	
	@RequestMapping("/add") 
	public ModelAndView add(){
		ModelAndView mv = new ModelAndView("/boss/article/articleAdd");
		loadCommonData(mv);
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
		a.setBlogDes(blogDes);
		a.setIsRecommend( req.getParameter("recommend"));
		a.setUserId(u.getUserId());
		a.setPublishDate(new java.util.Date());
		articleService.save(a,checkedTags);
		redirectAttributes.addFlashAttribute("msg", "添加成功");
		return "redirect:/boss/article/list"; 
	}
	

}
