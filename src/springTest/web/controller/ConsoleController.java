package springTest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springTest.web.auth.AuthPassport;
import springTest.web.service.BlogService;
import springTest.web.service.ClassifyService;
import springTest.web.service.PageMessageService;
import springTest.web.service.RecommendService;

@Controller
public class ConsoleController {

	@Autowired
	private BlogService bs;

	@Autowired
	private ClassifyService cs;

	@Autowired
	private RecommendService rs;

	@AuthPassport
	@RequestMapping(value="/console")
	public ModelAndView consolepage(String blogpage, String message,String messagetype, String tabid){
		ModelAndView mv = new ModelAndView();
		if(!StringUtils.isEmpty(message)){
			if(StringUtils.isEmpty(messagetype)){
				messagetype = PageMessageService.WARNING;
			}
			mv=PageMessageService.addPageMessage(mv,messagetype , message);
		}

		mv.setViewName("console");
		//׼�����¹�������
		if(StringUtils.isEmpty(blogpage)){
			blogpage="1";
		}
		mv.addObject("bloglist", bs.getBloglist("0", blogpage,"20"));
		mv.addObject("blogpage", blogpage);
		mv.addObject("blogmaxpage", bs.getMaxPage("0", "20"));
		mv.addObject("tabid", tabid);

		//׼�����·�������
		mv.addObject("classifylist", cs.listClassify());

		//׼����������
		//������������������

		//׼���Ƽ���������
		mv.addObject("recommend", rs.listRecommendBlogs());
		return mv;
	}

}
