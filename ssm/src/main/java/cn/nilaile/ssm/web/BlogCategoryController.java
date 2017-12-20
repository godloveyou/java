package cn.nilaile.ssm.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import cn.nilaile.ssm.entity.BlogCategory;
import cn.nilaile.ssm.service.IBlogCategoryService;

@Controller
@RequestMapping("/boss/articleCategory")
public class BlogCategoryController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IBlogCategoryService blogCategoryService;
	
	@RequestMapping(value="/delete/{ids}") 
	public String delete(@PathVariable("ids") String ids, final RedirectAttributes redirectAttributes){
		blogCategoryService.delete(ids);
		redirectAttributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/boss/articleCategory/list";
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
		ModelAndView mv = new ModelAndView("/articleCategory/list");
		PageHelper.startPage(curr, 30);
		List<BlogCategory> list = blogCategoryService.findAll();
		//用PageInfo对结果进行包装
		PageInfo<BlogCategory> page = new PageInfo<BlogCategory>(list);
		mv.addObject("list", list);
		mv.addObject("pageNum",page.getPageNum()); //当前页
		mv.addObject("total",page.getTotal()); //总条数
		mv.addObject("totalPage",page.getPages()); //总页数
		mv.addObject("curr",page.getPageNum());
		return mv;
	}

	
	@RequestMapping(value="/edit/{id}",method = RequestMethod.GET) 
	public ModelAndView edit(@PathVariable("id") Integer id){
		ModelAndView mv = new ModelAndView("/articleCategory/edit");
		try {
			BlogCategory tag = blogCategoryService.findById(id);
			mv.addObject("articleCategory",tag);
		} catch (Exception e) {
			LOG.error("exception-->"+e);
		}
		return mv;
	}
	
	
	@RequestMapping(value = "/doEdit",method = RequestMethod.POST)
	public String doEdit(HttpServletRequest req,final RedirectAttributes redirectAttributes){
		String id = req.getParameter("cid");
		String cname = req.getParameter("cname");
		BlogCategory category = blogCategoryService.findById(Integer.parseInt(id));
		if(!category.getCname().equals(cname)){
			category.setCname(cname);
			blogCategoryService.update(category);
		}
	
		redirectAttributes.addFlashAttribute("msg", "编辑成功");
		return "redirect:/boss/articleCategory/list"; 
	}
	
	
	@RequestMapping("/add") 
	public ModelAndView add(){
		ModelAndView mv = new ModelAndView("/articleCategory/add");
		return mv;
	}
	
	@RequestMapping("/doAdd")
	public String doAdd(String cname,final RedirectAttributes redirectAttributes){
		//判断是否存在
		BlogCategory t = blogCategoryService.findByName(cname);
		if(null!=t){
			redirectAttributes.addFlashAttribute("msg", "添加失败【已存在】");
			return "redirect:/boss/articleCategory/list"; 
		}
		
		t= new BlogCategory();
		t.setCname(cname);
		blogCategoryService.save(t);
		redirectAttributes.addFlashAttribute("msg", "添加成功");
		return "redirect:/boss/articleCategory/list"; 
	}
	

}
