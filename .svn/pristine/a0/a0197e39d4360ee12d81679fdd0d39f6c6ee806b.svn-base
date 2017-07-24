package springTest.web.service;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.io.FeedException;
import springTest.web.dao.BlogDAO;
import springTest.web.entity.BlogEntity;
import springTest.web.util.BlogDateComparator;

@Service
public class RssService {
	
	@Autowired
	private BlogDAO dao;
	
	public ArrayList<Item> getRssItems() throws IllegalArgumentException, FeedException{
		ArrayList<BlogEntity> bl= new ArrayList<BlogEntity>(dao.listBlog(1, 20, 0));
		Collections.sort(bl, new BlogDateComparator());		
		
		 ArrayList<Item> items = new ArrayList<Item>();
		 for(int i=0 ; i<bl.size() ; i++){
			 Item item = new Item();
			 item.setAuthor("×÷Õß");
			 item.setLink("ÍøÕ¾ÍøÖ·" + "Blog/viewblog?id=" + bl.get(i).getId());
			 item.setPubDate(bl.get(i).getBlogDate());
			 item.setTitle(bl.get(i).getBlogTitle());
			 Description des = new Description();
			 des.setValue( bl.get(i).getBlogAbstract());
			 item.setDescription(des);
			 items.add(item);
		 }

		return items;
	}
	

}
