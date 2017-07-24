package springTest.web.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springTest.web.entity.AdminEntity;

@Repository
@Transactional(rollbackOn = Exception.class)
public class AdminDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public AdminEntity getAdmin(String name, String password){
		String HQL = "from AdminEntity where password=? and id=?";
		Query q = sessionFactory.getCurrentSession().createQuery(HQL);
		q.setString(0, password);
		q.setString(1, name);
		return (AdminEntity) q.uniqueResult();
	}
	
	public boolean setpw(String pw){
		String HQL = "update AdminEntity ae set ae.password=?";
		Query q = sessionFactory.getCurrentSession().createQuery(HQL);
		q.setString(0, pw);
		return q.executeUpdate()>=0;
	}
}
