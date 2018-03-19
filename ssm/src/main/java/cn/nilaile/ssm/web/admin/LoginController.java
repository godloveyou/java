package cn.nilaile.ssm.web.admin;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
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

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.nilaile.ssm.dto.BaseResult;
import cn.nilaile.ssm.entity.User;
import cn.nilaile.ssm.enums.ResultEnum;
import cn.nilaile.ssm.service.IUserService;
import cn.nilaile.ssm.util.Md5SaltUtil;
import cn.nilaile.ssm.util.jiyan.GeetestConfig;
import cn.nilaile.ssm.util.jiyan.GeetestLib;

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
	public BaseResult<Object> login(HttpServletRequest request,@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, HttpSession session) {
		
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(), 
				GeetestConfig.isnewfailback());
			
		String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
		String validate = request.getParameter(GeetestLib.fn_geetest_validate);
		String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);
		System.out.println(challenge+"--"+validate+""+seccode);
		
//		if(StringUtils.isBlank(vcode)){
//			return new BaseResult<Object>(false, "验证码不能为空");
//		}
//		if(!vcode.equalsIgnoreCase(session.getAttribute(Constants.KAPTCHA_SESSION_KEY).toString())){
//			return new BaseResult<Object>(false, "验证码错误");
//		}
		
		//从session中获取gt-server状态
		int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
		
		//从session中获取userid
		String userid = (String)request.getSession().getAttribute("userid");
		
		//自定义参数,可选择添加
		HashMap<String, String> param = new HashMap<String, String>(); 
		param.put("user_id", userid); //网站用户id
		param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
		param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP
		
		int gtResult = 0;

		if (gt_server_status_code == 1) {
			//gt-server正常，向gt-server进行二次验证
			gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
			log.info("gt_server_status_code===1 验证"+gtResult);
		} else {
			// gt-server非正常情况下，进行failback模式验证
			log.info("failback:use your own server captcha validate");
			gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
			log.info("fail back 验证"+gtResult);
		}

		if (gtResult != 1) {
			return new BaseResult<Object>(false, "验证码验证失败");
		}
		
		
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return new BaseResult<Object>(false, "用户名或密码为空");
		}

		User u = userService.getByUserNameOrMobile(username);
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
		
		User u = userService.getByUserNameOrMobile(username);
		
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
