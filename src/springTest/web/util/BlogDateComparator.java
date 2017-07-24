package springTest.web.util;

import java.util.Comparator;

import springTest.web.entity.BlogEntity;

public class BlogDateComparator implements Comparator<BlogEntity> {

	@Override
	public int compare(BlogEntity o1, BlogEntity o2) {
		if(o1.getBlogDate().before(o2.getBlogDate())){
			return 1;
		}
		if(o1.getBlogDate().after(o2.getBlogDate())){
			return -1;
		}
		return 0;
	}

}
