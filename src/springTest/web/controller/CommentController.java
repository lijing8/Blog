package springTest.web.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springTest.web.auth.AuthPassport;
import springTest.web.service.CommentService;
import springTest.web.service.PageMessageService;

@Controller
public class CommentController {

	@Autowired
	private CommentService service;

	/**
	 * 发表评论
	 * @param blogid 评论所属博文id
	 * @param name 评论者称呼
	 * @param email 评论者email
	 * @param comment 评论内容
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/savecomment")
	public ModelAndView addComment(String blogid, String name , String email, String comment) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		if(comment.length()>2000){
			mv.setViewName("redirect:viewblog?id="+blogid+"&" + PageMessageService.dangerMessage("超出评论限制字数。"));
		} else if(name.length()>20){
			mv.setViewName("redirect:viewblog?id="+blogid+"&" + PageMessageService.dangerMessage("超出称呼的字数限制。"));
		} else {		
			service.addComment(blogid, name, email, comment);
			mv.setViewName("redirect:viewblog?id="+blogid+"&" + PageMessageService.sucessMessage("评论发布成功。"));
		}
		return mv;
	}

	/**
	 * 删除评论
	 * @param id 评论id
	 * @param blogid 评论所属文章id
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@AuthPassport
	@RequestMapping("/delcomment")
	public ModelAndView delComment(String id , String blogid) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		service.delComment(new Integer(blogid).intValue(), id);
		mv.setViewName("redirect:console?tabid=comments&" + PageMessageService.sucessMessage("删除评论成功！"));
		return mv;
	}

}
