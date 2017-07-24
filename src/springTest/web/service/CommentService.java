package springTest.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springTest.web.dao.BlogDAO;
import springTest.web.dao.CommentDAO;
import springTest.web.entity.BlogEntity;
import springTest.web.entity.CommentEntity;

@Service
public class CommentService {
	
	@Autowired
	private BlogDAO bd;
	
	@Autowired
	private CommentDAO dao;
	
	public void addComment(String blogid, String name, String email, String comment){
		CommentEntity entity = new CommentEntity();
		BlogEntity be = bd.getBolg(new Integer(blogid).intValue());
		entity.setPosterName(name);
		entity.setPosterEMail(email);
		entity.setComment(comment);
		entity.setPostDate(new Date());
		be.getComments().add(entity);
		
		bd.updateBlog(be);
		//dao.saveComment(entity);
	}
	
	
	public void delComment(int blogid, String id){
		BlogEntity be = bd.getBolg(blogid);
		Set<CommentEntity> comms = be.getComments();
		for( Iterator<CommentEntity> it = comms.iterator(); it.hasNext();){
			CommentEntity ce = it.next();
			if(ce.getId().equals(id)){
				comms.remove(ce);
				break;
			}
		}
		bd.updateBlog(be);		
	}
	
	public HashMap<String,ArrayList<String>> getNewest5Comments(){
		HashMap<String, ArrayList<String>> rst = new HashMap<String, ArrayList<String>>();
		ArrayList<Object[]> records = dao.getNewest5Comments();
		ArrayList<String> comms = new ArrayList<String>();
		ArrayList<String> blogid = new ArrayList<String>();
		for(int i=0 ; i<records.size() ; i++){
			comms.add(records.get(i)[0].toString());
			blogid.add(records.get(i)[1].toString());
		}
		
		rst.put("comms", comms);
		rst.put("blogid", blogid);
		
		return rst;
	}
	
}
