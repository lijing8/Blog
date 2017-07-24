package springTest.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CkEditorController {

	@RequestMapping(value="/ckupload")
	public ModelAndView upload(@RequestParam(value="upload",required=true) CommonsMultipartFile file,HttpServletRequest req  ) throws IOException{
		ModelAndView mv = new ModelAndView("uploadresult");
		String fileType = file.getContentType();
		StringBuffer url = new StringBuffer(req.getContextPath());
		StringBuffer message = new StringBuffer();
		StringBuffer script = new StringBuffer();

		if(fileType.startsWith("image/")){
			if(!file.isEmpty()){
				InputStream is = file.getInputStream();
				String filename = UUID.randomUUID().toString()+file.getOriginalFilename();
				File of = new File(req.getServletContext().getRealPath("/img/upload/"), filename);
				OutputStream os = new FileOutputStream(of);
				byte[] buffer = new byte[1024];  
				int length = 0;  
				while ((length = is.read(buffer)) > 0) {  
					os.write(buffer, 0, length);  
				}
				is.close();
				os.close();


				message.append("上传成功！");

				script.append("<script type=\"text/javascript\">")
				.append("window.parent.CKEDITOR.tools.callFunction(")
				.append(req.getParameter("CKEditorFuncNum"))
				.append(",'")
//				.append(url.substring(0, url.lastIndexOf("/")))
				.append(url)
				.append("/img/upload/")
				.append(filename)
				.append("','')")
				.append("</script>");
				mv.addObject("script",script.toString());

			}
		} else {
			message.append("上传失败！");

			script.append("<script type=\"text/javascript\">")
			.append("window.parent.CKEDITOR.tools.callFunction(")
			.append(req.getParameter("CKEditorFuncNum"))
			.append(",'','');")
			.append("alert('文件类型错误！请选择图片文件。');")
			.append("</script>");
			mv.addObject("script",script.toString());
		}

		mv.addObject("message", message.toString());
		mv.addObject("script",script.toString());		
		return mv;
	}





}
