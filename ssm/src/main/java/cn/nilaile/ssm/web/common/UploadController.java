package cn.nilaile.ssm.web.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	@RequestMapping(value="/uploadImg",method=RequestMethod.POST)
	public void uploadImg(HttpServletRequest req,HttpServletResponse resp,@RequestParam(value = "editormd-image-file", required = false) MultipartFile attach){
		try{
		 req.setCharacterEncoding( "utf-8" );
         resp.setHeader( "Content-Type" , "text/html" );
         String rootPath = req.getSession().getServletContext().getRealPath("/resource/upload/");

         /**
          * 文件路径不存在则需要创建文件路径
          */
         File filePath=new File(rootPath);
         if(!filePath.exists()){
             filePath.mkdirs();
         }

         //最终文件名
         File realFile=new File(rootPath+File.separator+attach.getOriginalFilename());
         FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);

         //下面response返回的json格式是editor.md所限制的，规范输出就OK
         resp.getWriter().write( "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"/resource/upload/" + attach.getOriginalFilename() + "\"}" );
     } catch (Exception e) {
         try {
             resp.getWriter().write( "{\"success\":0}" );
         } catch (IOException e1) {
             e1.printStackTrace();
         }
     }
	}
}
