package cn.nilaile.ssm.web.common;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import cn.nilaile.ssm.util.validcode.VerificationCodeTool;

@Controller
@RequestMapping("/validateCode")
public class ValidateCodeController {
	
	
	
    @RequestMapping("/doGet")  
    public ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        response.setDateHeader("Expires", 0);  
        // Set standard HTTP/1.1 no-cache headers.  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        // Set standard HTTP/1.0 no-cache header.  
        response.setHeader("Pragma", "no-cache");  
        // return a jpeg  
        response.setContentType("image/jpeg");  
        // create the text for the image  
        
        VerificationCodeTool vcTool = new VerificationCodeTool();  
        BufferedImage image = vcTool.drawVerificationCodeImage();  
        int vcResult = vcTool.getXyresult();  //计算结果
        String vcValue = vcTool.getRandomString();   //问题
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, vcResult);  
       
        ServletOutputStream out = response.getOutputStream();  
        // write the data out  
        ImageIO.write(image, "jpg", out);  
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }
        System.out.println("Captchca:"+request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY));
        return null;  
    }  

}
