package springTest.web.dao;

import java.util.ArrayList;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class CommentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public ArrayList<Object[]> getNewest5Comments(){
		String endl = "\r\n";
		StringBuffer SQL = new StringBuffer();
		SQL.append("select c.comment,bc.T_BLOG_id").append(endl)
		.append("FROM t_comment c").append(endl)
		.append("inner join t_blog_t_comment bc").append(endl)
		.append("on c.id = bc.comments_id").append(endl)
		.append("order by c.postDate desc").append(endl)
		.append("limit 5");
		
		Query q = sessionFactory.getCurrentSession().createSQLQuery(SQL.toString());
		
		return ((ArrayList<Object[]>) q.list());
		
	}

}
