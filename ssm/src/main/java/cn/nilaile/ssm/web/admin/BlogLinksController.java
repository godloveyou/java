package cn.nilaile.ssm.web.admin;

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
import cn.nilaile.ssm.entity.BlogLinks;
import cn.nilaile.ssm.service.IBlogLinksService;

@Controller
@RequestMapping("/boss/links")
public class BlogLinksController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IBlogLinksService   iBlogLinksService;
	
	@RequestMapping(value="/delete/{ids}") 
	public String delete(@PathVariable("ids") String ids, final RedirectAttributes redirectAttributes){
		iBlogLinksService.remove(ids);
		redirectAttributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/boss/links/list";
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
		ModelAndView mv = new ModelAndView("/boss/links/list");
		PageHelper.startPage(curr, 30);
		List<BlogLinks> list = iBlogLinksService.list();
		//用PageInfo对结果进行包装
		PageInfo<BlogLinks> page = new PageInfo<BlogLinks>(list);
		mv.addObject("list", list);
		mv.addObject("pageNum",page.getPageNum()); //当前页
		mv.addObject("total",page.getTotal()); //总条数
		mv.addObject("totalPage",page.getPages()); //总页数
		mv.addObject("curr",page.getPageNum());
		return mv;
	}

	
	@RequestMapping(value="/edit/{id}",method = RequestMethod.GET) 
	public ModelAndView edit(@PathVariable("id") Integer id){
		ModelAndView mv = new ModelAndView("/boss/links/edit");
		try {
			BlogLinks tag = iBlogLinksService.getById(id);
			mv.addObject("links",tag);
		} catch (Exception e) {
			LOG.error("exception-->"+e);
		}
		return mv;
	}
	
	
	@RequestMapping(value = "/doEdit",method = RequestMethod.POST)
	public String doEdit(HttpServletRequest req,final RedirectAttributes redirectAttributes){
		String id = req.getParameter("id");
		BlogLinks links = iBlogLinksService.getById(Integer.parseInt(id));
		links.setCallbackUrl(req.getParameter("callbackUrl"));
		links.setDescription(req.getParameter("description"));
		links.setPublishDate(new java.util.Date());
		links.setTitle(req.getParameter("title"));
		
		iBlogLinksService.update(links);
		redirectAttributes.addFlashAttribute("msg", "编辑成功");
		return "redirect:/boss/links/list"; 
	}
	
	
	@RequestMapping("/add") 
	public ModelAndView add(){
		ModelAndView mv = new ModelAndView("/boss/links/add");
		return mv;
	}
	
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest req,final RedirectAttributes redirectAttributes){
		//判断是否存在
		BlogLinks t = iBlogLinksService.getByName(req.getParameter("title"));
		
		if(null!=t){
			redirectAttributes.addFlashAttribute("msg", "添加失败【已存在】");
			return "redirect:/boss/links/list"; 
		}
		
		t= new BlogLinks();
		t.setCallbackUrl(req.getParameter("callbackUrl"));
		t.setDescription(req.getParameter("description"));
		t.setPublishDate(new java.util.Date());
		t.setTitle(req.getParameter("title"));
		iBlogLinksService.save(t);
		redirectAttributes.addFlashAttribute("msg", "添加成功");
		return "redirect:/boss/links/list"; 
	}
	

}
