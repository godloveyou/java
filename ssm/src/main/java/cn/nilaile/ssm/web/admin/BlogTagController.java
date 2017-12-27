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

import cn.nilaile.ssm.entity.BlogTag;
import cn.nilaile.ssm.service.IBlogTagService;

@Controller
@RequestMapping("/boss/articleTag")
public class BlogTagController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IBlogTagService iBlogTagService;
	
	@RequestMapping(value="/delete/{tagIds}") 
	public String delete(@PathVariable("tagIds") String tagIds, final RedirectAttributes redirectAttributes){
		iBlogTagService.delete(tagIds);
		redirectAttributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/boss/articleTag/list";
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
		ModelAndView mv = new ModelAndView("/boss/articleTag/list");
		PageHelper.startPage(curr, 30);
		List<BlogTag> list = iBlogTagService.findAll();
		//用PageInfo对结果进行包装
		PageInfo<BlogTag> page = new PageInfo<BlogTag>(list);
		mv.addObject("list", list);
		mv.addObject("pageNum",page.getPageNum()); //当前页
		mv.addObject("total",page.getTotal()); //总条数
		mv.addObject("totalPage",page.getPages()); //总页数
		mv.addObject("curr",page.getPageNum());
		return mv;
	}

	
	@RequestMapping(value="/edit/{id}",method = RequestMethod.GET) 
	public ModelAndView edit(@PathVariable("id") Integer id){
		ModelAndView mv = new ModelAndView("/boss/articleTag/edit");
		try {
			BlogTag tag = iBlogTagService.findById(id);
			mv.addObject("articleTag",tag);
		} catch (Exception e) {
			LOG.error("exception-->"+e);
		}
		return mv;
	}
	
	
	@RequestMapping(value = "/doEdit",method = RequestMethod.POST)
	public String doEdit(HttpServletRequest req,final RedirectAttributes redirectAttributes){
		String id = req.getParameter("id");
		String tagname = req.getParameter("tagName");
		BlogTag articleTag = iBlogTagService.findById(Integer.parseInt(id));
		if(!articleTag.getTagName().equals(tagname)){
			articleTag.setTagName(tagname);
			iBlogTagService.update(articleTag);
		}
	
		redirectAttributes.addFlashAttribute("msg", "编辑成功");
		return "redirect:/boss/articleTag/list"; 
	}
	
	
	@RequestMapping("/add") 
	public ModelAndView add(){
		ModelAndView mv = new ModelAndView("/boss/articleTag/add");
		return mv;
	}
	
	@RequestMapping("/doAdd")
	public String doAdd(String tagName,final RedirectAttributes redirectAttributes){
		//判断是否存在
		BlogTag t = iBlogTagService.findByName(tagName);
		if(null!=t){
			redirectAttributes.addFlashAttribute("msg", "添加失败【标签已存在】");
			return "redirect:/boss/articleTag/list"; 
		}
		
		t= new BlogTag();
		t.setTagName(tagName);
		iBlogTagService.save(t);
		redirectAttributes.addFlashAttribute("msg", "添加成功");
		return "redirect:/boss/articleTag/list"; 
	}
	

}
