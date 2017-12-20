package cn.nilaile.ssm.web;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;

import cn.nilaile.ssm.dto.BaseResult;
import cn.nilaile.ssm.entity.User;
import cn.nilaile.ssm.enums.ResultEnum;
import cn.nilaile.ssm.service.IUserService;
import cn.nilaile.ssm.util.Md5SaltUtil;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	IUserService userService;
	
	@RequestMapping(path = "/logout")
	public String logout(HttpSession session){
		session.setAttribute("user", null);
		return "login/login";
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
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			 return new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
		}

		 return new BaseResult<Object>(true,"登录成功");
	}

	
	
	
	
	
	
	
	// URL template
	// @RequestMapping(path="/owners/{ownerId}", method=RequestMethod.GET)
	// public String findOwner(@PathVariable String ownerId, Model model) {
	// Owner owner = ownerService.findOwner(ownerId);
	// model.addAttribute("owner", owner);
	// return "displayOwner";
	// }

}
