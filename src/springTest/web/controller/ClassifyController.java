package springTest.web.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springTest.web.auth.AuthPassport;
import springTest.web.service.BlogService;
import springTest.web.service.ClassifyService;
import springTest.web.service.PageMessageService;

@Controller
public class ClassifyController {

	@Autowired
	private ClassifyService service;

	@Autowired
	private BlogService bs;

	@AuthPassport
	@RequestMapping(value="/saveclassify")
	public ModelAndView addClassify(String item) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();

		if(!StringUtils.isEmpty(item)){
			service.addClassify(item);
			mv.setViewName("redirect:console?tabid=classifies&" + PageMessageService.sucessMessage("添加分类成功。"));
		} else {
			mv.setViewName("redirect:console?tabid=classifies&" + PageMessageService.dangerMessage("分类内容不能为空。"));
		}
		return mv;
	}

	@AuthPassport
	@RequestMapping(value="/delclassify")
	public ModelAndView delClassify(String id) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();

		if(bs.getBloglist(new Integer(id).toString(), "1", "1").size()>0){
			mv.setViewName("redirect:console?tabid=classifies&" + PageMessageService.dangerMessage("由于该分类下至少有一篇文章存在，所以无法删除该分类。"));
		}else{
			if(!StringUtils.isEmpty(id)){
				service.delClassify(new Integer(id).intValue());
			}
			mv.setViewName("redirect:console?tabid=classifies&" + PageMessageService.sucessMessage("分类删除成功。"));
		}

		return mv;
	}
}
