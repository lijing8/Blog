package springTest.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springTest.web.auth.AuthPassport;
import springTest.web.service.BlogService;
import springTest.web.service.ClassifyService;
import springTest.web.service.CommentService;
import springTest.web.service.PageMessageService;
import springTest.web.service.RecommendService;

@Controller
//@RequestMapping(value="/blog")
public class BlogController {

	@Autowired
	private BlogService service;

	@Autowired
	private ClassifyService classifyService;

	@Autowired
	private CommentService cs;

	@Autowired
	private RecommendService rs;

	/**
	 * 添加文章连接地址
	 * @return view:blogeditor; model:{文章分类列表}
	 */
	@AuthPassport
	@RequestMapping(value="/addblog")
	public ModelAndView addBlog(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("blogeditor");
		mv.addObject("classify", classifyService.listClassify());	
		mv.addObject("isedit", "false");
		return mv;
	}

	/**
	 * 编辑文章链接地址
	 * @param id 编辑文章id
	 * @return id为空：console；id不为空：编辑界面
	 */
	@AuthPassport
	@RequestMapping(value="/editblog")
	public ModelAndView editBlog(String id){
		ModelAndView mv = new ModelAndView();
		if(StringUtils.isEmpty(id)){
			mv.setViewName("redirect:console");
			return mv;
		}
		mv.setViewName("blogeditor");
		mv.addObject("classify", classifyService.listClassify());	
		mv.addObject("oldBlog", service.getBlog(new Integer(id).intValue()));
		mv.addObject("isedit", "true");
		return mv;
	}

	/**
	 * 保存文章链接地址
	 * @param classifyId 文章分类id
	 * @param title 标题
	 * @param textbody 内容
	 * @param isedit 是否是编辑状态的标志，true为编辑状态，需要update，false 为新增状态
	 * @param request request
	 * @return  view: sucess; model:{跳转地址:首页}
	 * @throws UnsupportedEncodingException 
	 */
	@AuthPassport
	@RequestMapping(value="/saveblog")
	public ModelAndView saveBlog(String classifyId , String title,String textbody, String isedit, String blogid) throws UnsupportedEncodingException{
		boolean bisedit = "true".equals(isedit);
		int editId=0;
		if(bisedit){
			editId = new Integer(blogid).intValue();
		}
		ModelAndView mv = new ModelAndView();

		if(!StringUtils.isEmpty(title)&&!StringUtils.isEmpty(classifyId)&&!StringUtils.isEmpty(textbody)){
			if(textbody.length()>10000){
				mv.setViewName("redirect:console?" + PageMessageService.dangerMessage("超出文章的字数限制。"));
			} else {
				service.saveBlog(new Integer(classifyId).intValue(), title, textbody, editId, bisedit);
				mv.setViewName("redirect:console?tabid=blogs&" + PageMessageService.sucessMessage("文章发布成功。"));
			}
		} else {
			mv.setViewName("redirect:console?tabid=blogs");
		}
		return mv;
	}

	/**
	 * 主界面
	 * @param tarpage 目标页数
	 * @param classifyid 显示分类
	 * @return
	 */
	@RequestMapping(value={"/index.html","/"})
	public ModelAndView listBlog(String tarpage, String classifyid){
		ModelAndView mv = new ModelAndView("bloglister");
		if(StringUtils.isEmpty(tarpage)){
			tarpage="1";
		}
		if(StringUtils.isEmpty(classifyid)){
			classifyid="0";
		}
		if("0".equals(classifyid) && "1".equals(tarpage)){
			mv.addObject("recommeds", rs.listRecommendBlogs());
		}
		mv.addObject("result", service.getBloglist(classifyid,tarpage, "10"));
		mv.addObject("top5",service.getTop5Blog());
		mv.addObject("maxpage", service.getMaxPage(classifyid,"10"));
		mv.addObject("tarpage",tarpage);
		mv.addObject("classifyid",new Integer(classifyid).intValue());
		mv.addObject("classify", classifyService.listClassify());
		HashMap<String, ArrayList<String>> newcomments = cs.getNewest5Comments();
		mv.addObject("newcomments", newcomments.get("comms"));
		mv.addObject("newcommentsid", newcomments.get("blogid"));
		HashMap<String, ArrayList<String>>Comm5Blogs = service.getComm5Blogs();
		mv.addObject("Comm5BlogsNum", Comm5Blogs.get("comms"));
		mv.addObject("Comm5Blogsid", Comm5Blogs.get("blogid"));
		mv.addObject("Comm5BlogsTitle", Comm5Blogs.get("blogtitle"));

		return mv;
	}

	/**
	 * 显示博文内容及评论
	 * @param id 博文id
	 * @param error 评论错误信息
	 * @return
	 */
	@RequestMapping(value="/viewblog")
	public ModelAndView viewblog(String id, String message, String messagetype ){
		service.addviews(new Integer(id).intValue());
		ModelAndView mv = new ModelAndView("blogviewer");
		mv.addObject("blog", service.getBlog(new Integer(id).intValue()));
		mv.addObject("classify", classifyService.listClassify());
		if(!StringUtils.isEmpty(message)){
			if(StringUtils.isEmpty(messagetype)){
				messagetype = PageMessageService.WARNING;
			}
			mv=PageMessageService.addPageMessage(mv, messagetype, message);
		}
		return mv;
	}

	/**
	 * 删除博文
	 * @param id 删除博文id
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@AuthPassport
	@RequestMapping(value="/delblog")
	public ModelAndView delBlog(String id) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		service.delBlog(new Integer(id).intValue());
		mv.setViewName("redirect:console?tabid=blogs&" + PageMessageService.sucessMessage("文章删除成功。"));
		return mv;
	}
}
