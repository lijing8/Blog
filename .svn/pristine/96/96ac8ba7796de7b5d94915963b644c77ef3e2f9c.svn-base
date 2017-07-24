package springTest.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springTest.web.dao.ClassifyDAO;
import springTest.web.entity.ClassifyEntity;

@Service
public class ClassifyService {

	@Autowired
	private ClassifyDAO dao;
	
	public ClassifyEntity viewClassify(int id){
		return dao.viewClassify(id);
	}
	
	public List<ClassifyEntity> listClassify(){
		return dao.listClassify();
	}
	
	public void addClassify(String item){
		ClassifyEntity entity = new ClassifyEntity();
		entity.setItem(item);
		dao.addClassify(entity);
	}
	
	public void delClassify(int id){
		ClassifyEntity entity = dao.getClassify(id);
		dao.delClassify(entity);
	}
}
