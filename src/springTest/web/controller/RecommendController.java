package springTest.web.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springTest.web.auth.AuthPassport;
import springTest.web.service.PageMessageService;
import springTest.web.service.RecommendService;

@Controller
public class RecommendController {

	@Autowired
	private RecommendService rs;

	@AuthPassport
	@RequestMapping(value="/addrecommend")
	public ModelAndView addRecommend(String id) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		rs.addRecommend(new Integer(id).intValue());
		mv.setViewName("redirect:console?tabid=recommends&" + PageMessageService.sucessMessage("推荐文章成功。"));
		return mv;
	}

	@AuthPassport
	@RequestMapping(value="/delrecommend")
	public ModelAndView delRecommend(String id) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		rs.delRecommendByBlogID(new Integer(id).intValue());
		mv.setViewName("redirect:console?tabid=recommends&" + PageMessageService.sucessMessage("取消推荐成功。"));
		return mv;
	}
}
