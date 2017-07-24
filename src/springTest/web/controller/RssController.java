package springTest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rometools.rome.io.FeedException;

import springTest.web.service.RssService;

@Controller
public class RssController {
	
	@Autowired
	private RssService rs;
	
	@RequestMapping(value="/rssfeed")
	public ModelAndView getRssFeed() throws IllegalArgumentException, FeedException {
		ModelAndView mv = new ModelAndView("rssfeed");
		mv.addObject("items",rs.getRssItems());
		return mv;
	}

}
