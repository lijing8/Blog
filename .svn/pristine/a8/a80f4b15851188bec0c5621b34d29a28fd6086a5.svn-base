package springTest.web.util;

import java.util.Comparator;

import springTest.web.entity.BlogEntity;

public class BlogCommentNumComparator implements Comparator<BlogEntity> {

	@Override
	public int compare(BlogEntity o1, BlogEntity o2) {
		return o1.getComments().size()-o2.getComments().size();
	}

}
