package cn.nilaile.ssm.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/boss")
public class AdminIndexController {
	
	@RequestMapping("/index") 
	public String index(){
		return "boss/index";
	}
	
	@RequestMapping("/main") 
	public String main(){
		return "boss/main";
	}

}
