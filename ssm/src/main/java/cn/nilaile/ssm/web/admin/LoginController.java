package cn.nilaile.ssm.web.admin;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;

import cn.nilaile.ssm.anno.Token;
import cn.nilaile.ssm.dto.BaseResult;
import cn.nilaile.ssm.entity.User;
import cn.nilaile.ssm.enums.ResultEnum;
import cn.nilaile.ssm.interceptor.BossLoginInterceptor;
import cn.nilaile.ssm.service.IUserService;
import cn.nilaile.ssm.util.Md5SaltUtil;

@Controller
@RequestMapping("/boss")
public class LoginController {

	 private final Logger log = LoggerFactory.getLogger(LoginController.class);  
	 
	@Autowired
	IUserService userService;
	
	@RequestMapping(path = "/logout")
	public String logout(HttpSession session){
		session.setAttribute("user", null);
		return "boss/login/login";
	}
	
	@RequestMapping(path = "/login")
	public String login(HttpSession session){
		if(session.getAttribute("user")!=null){
			return "boss/index";
		}
		
		return "boss/login/login";
	}

	@RequestMapping(path = "/doLogin", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public BaseResult<Object> login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,@RequestParam(value="vcode") String vcode, HttpSession session) {
		if(StringUtils.isBlank(vcode)){
			return new BaseResult<Object>(false, "验证码不能为空");
		}
		if(!vcode.equalsIgnoreCase(session.getAttribute(Constants.KAPTCHA_SESSION_KEY).toString())){
			return new BaseResult<Object>(false, "验证码错误");
		}
		
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return new BaseResult<Object>(false, "用户名或密码为空");
		}

		User u = userService.login(username);
		if (null == u) {
			return new BaseResult<Object>(false, "用户名或密码错误");
		}

		try {
			if(!Md5SaltUtil.validPassword(password, u.getPassword())){
				return new BaseResult<Object>(false, ResultEnum.INVALID_USER.getMsg());
			}
			session.setAttribute("user", u);
			User u2 = new User();
			u2.setLastLogin(new java.util.Date());
			u2.setUserId(u.getUserId());
			userService.updateByProper(u2);
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			 return new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
		}

		 return new BaseResult<Object>(true,"登录成功");
	}

	
	@RequestMapping("/toChangePwd")
	public ModelAndView toChangePwd(ModelAndView v,HttpSession session){
		v.setViewName("/boss/user/changePwd");
		User u = (User) session.getAttribute("user");
		u.setPassword("");
		v.addObject("user", u);
		return v;
	}
	
	
	@RequestMapping(path="/changePwd",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Object> changePwd(@RequestParam(value = "oldPwd") String oldPwd,@RequestParam(value = "pwd") String pwd,@RequestParam(value = "username") String username){
		
		User u = userService.login(username);
		
		try {
			if(!Md5SaltUtil.validPassword(oldPwd, u.getPassword())){
				return new BaseResult<Object>(false, "原密码错误");
			}
			User u2 = new User();
			u2.setUserId(u.getUserId());
			u2.setPassword(Md5SaltUtil.getEncryptedPwd(pwd));
			userService.updateByProper(u2);
			
		} catch (Exception e) {
			e.printStackTrace();
			 return new BaseResult<Object>(false,"修改失败[系统繁忙]");
		}
		
		 return new BaseResult<Object>(true,"修改成功");
	}
	
	
	
	// URL template
	// @RequestMapping(path="/owners/{ownerId}", method=RequestMethod.GET)
	// public String findOwner(@PathVariable String ownerId, Model model) {
	// Owner owner = ownerService.findOwner(ownerId);
	// model.addAttribute("owner", owner);
	// return "displayOwner";
	// }

}
