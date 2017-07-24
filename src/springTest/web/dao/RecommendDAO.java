package springTest.web.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springTest.web.entity.RecommendEntity;

@Repository
@Transactional(rollbackOn = Exception.class)
public class RecommendDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void addRecommend(RecommendEntity entity){
		sessionFactory.getCurrentSession().save(entity);
	}
	
	public void delRecommend(RecommendEntity entity){
		sessionFactory.getCurrentSession().delete(entity);
	}
	
	
	public RecommendEntity getRecommendByBlogID(int blogid){
		String HQL = "from RecommendEntity r where r.blogid=?";
		Query q = sessionFactory.getCurrentSession().createQuery(HQL);
		q.setInteger(0, blogid);
		return (RecommendEntity) q.uniqueResult();
	}
	
	public RecommendEntity getRecommend(int id){
		String HQL = "from RecommendEntity r where r.id=?";
		Query q = sessionFactory.getCurrentSession().createQuery(HQL);
		q.setInteger(0, id);
		return (RecommendEntity) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<RecommendEntity> listRecommend(){
		String HQL = "from RecommendEntity";
		Query q = sessionFactory.getCurrentSession().createQuery(HQL);
		return q.list();
	}
	
}
