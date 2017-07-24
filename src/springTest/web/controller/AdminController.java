package springTest.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import springTest.web.entity.AdminEntity;
import springTest.web.service.AdminService;
import springTest.web.service.PageMessageService;

@Controller
@SessionAttributes(value="admin")
public class AdminController {

	@Autowired
	private AdminService service;

	@RequestMapping(value="/login")
	public ModelAndView login(String name, String password, String jumpto) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		if(StringUtils.isEmpty(name) &&StringUtils.isEmpty(password)){
			mv.setViewName("login");
			if(!StringUtils.isEmpty(jumpto)){
				mv.addObject("jumpto",URLEncoder.encode(jumpto,"utf-8"));
			} else {
				mv.addObject("jumpto","index.html");
			}
		} else{
			AdminEntity ae = service.checkLogin(name, password);
			if(null!=ae){
				mv.addObject("admin", ae);				
				if(!StringUtils.isEmpty(jumpto)){
					mv.setViewName("redirect:"+jumpto);
				} else {
					mv.setViewName("redirect:index.html");
				}
			} else {
				mv=PageMessageService.addPageMessage(mv, PageMessageService.DANGER, "��¼ʧ�ܣ��û��������������");
			}
		}
		return mv;
	}
	
	@RequestMapping(value="/changepassword")
	public ModelAndView changepw(String oldpw, String password1, String password2) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		ModelAndView mv= new ModelAndView();
		if(null == oldpw){
			oldpw = "";
		}
		if(null == service.checkLogin("admin", oldpw)) {
			mv.setViewName("redirect:console?tabid=password&" + PageMessageService.dangerMessage("ԭ�������"));
		} else{
			if(!StringUtils.isEmpty(password1) && !StringUtils.isEmpty(password2)	 && password1.equals(password2)){
				service.updatepw(password1);
				mv.setViewName("redirect:console?tabid=password&" + PageMessageService.sucessMessage("��������ɹ���"));
			}else{
				mv.setViewName("redirect:console?tabid=password&" + PageMessageService.dangerMessage("��������������벻ͬ�����������롣"));
			}
		}
		return mv;
	}
	
	
}
