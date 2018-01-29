package cn.nilaile.ssm.web.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nilaile.ssm.dto.BaseResult;

@Controller
@RequestMapping("/api")
public class Index {
	
	@RequestMapping("/index") 
	@ResponseBody
	public BaseResult<String> index(){
		return new BaseResult<>(true, "Api调用成功");
	}
}
