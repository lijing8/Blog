package springTest.web.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class PageMessageService {
	public static String SUCCESS = "alert-success";
	public static String INFO = "alert-info";
	public static String WARNING = "alert-warning";
	public static String DANGER = "alert-danger";
	
	private String alertcolor;
	private String message;
	
	public static ModelAndView addPageMessage(ModelAndView mv, String alertcolor, String message){
		PageMessageService pms = new PageMessageService();
		pms.setAlertcolor(alertcolor);
		pms.setMessage(message);
		mv.addObject("pagemessage", pms);
		
		return mv;
	}

	public String getAlertcolor() {
		return alertcolor;
	}

	public void setAlertcolor(String alertcolor) {
		this.alertcolor = alertcolor;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static String sucessMessage(String message) throws UnsupportedEncodingException{
		return "messagetype=" + SUCCESS + "&message=" +  URLEncoder.encode(message,"utf-8");
	}
	
	public static String dangerMessage(String message) throws UnsupportedEncodingException{
		return "messagetype=" + DANGER + "&message=" +  URLEncoder.encode(message,"utf-8");
	}
	
	public static String warningMessage(String message) throws UnsupportedEncodingException{
		return "messagetype=" + WARNING + "&message=" + URLEncoder.encode(message,"utf-8");
	}
	
	public static String infoMessage(String message) throws UnsupportedEncodingException{
		return "messagetype=" + INFO + "&message=" +  URLEncoder.encode(message,"utf-8");
	}
	
}
