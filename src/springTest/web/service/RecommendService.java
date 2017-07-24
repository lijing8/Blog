package springTest.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springTest.web.dao.BlogDAO;
import springTest.web.dao.RecommendDAO;
import springTest.web.entity.BlogEntity;
import springTest.web.entity.RecommendEntity;

@Service
public class RecommendService {

	@Autowired
	private RecommendDAO dao ;

	@Autowired
	private BlogDAO bd;

	public void addRecommend(int blogid){
		if(dao.getRecommendByBlogID(blogid) == null){
			RecommendEntity entity = new RecommendEntity();
			entity.setBlogid(blogid);
			dao.addRecommend(entity);
		}
	}

	public RecommendEntity getRecommend(int id){
		return dao.getRecommend(id);
	}

	public void delRecommend(int id){
		RecommendEntity entity = getRecommend(id);
		dao.delRecommend(entity);
	}

	public void delRecommendByBlogID(int blogid){
		RecommendEntity entity = dao.getRecommendByBlogID(blogid);
		if(entity != null){
			dao.delRecommend(entity);
		}
	}




	public List<RecommendEntity> listRecommend(){
		return dao.listRecommend();
	}

	public List<BlogEntity> listRecommendBlogs(){
		List<BlogEntity> blogs = new ArrayList<BlogEntity>();
		List<RecommendEntity> res = listRecommend();
		for(int i=0 ; i<res.size() ; i++){
			blogs.add(bd.getBolg(res.get(i).getBlogid()));
		}		
		return blogs;
	}

}
