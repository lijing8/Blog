package springTest.web.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springTest.web.dao.BlogDAO;
import springTest.web.dao.ClassifyDAO;
import springTest.web.entity.BlogEntity;
import springTest.web.entity.CommentEntity;
import springTest.web.util.BlogDateComparator;
import springTest.web.util.BlogViewsComparator;

@Service
public class BlogService {

	@Autowired
	private BlogDAO dao;

	@Autowired
	private ClassifyDAO classifyDao;
	
	@Autowired
	private RecommendService rs;
	
	/**
	 * ���沩��
	* @param classifyid ���·�����
	 * @param title ���±���
	 * @param body ��������
	 * @param blogId �޸ĵĲ���id
	 * @param isedit �Ƿ����޸�״̬��true�޸ģ�false����
	 */
	public void saveBlog(int classifyid, String title,String body,int blogId,boolean isedit){
		BlogEntity blog = new BlogEntity();
		
		if(isedit){//�༭״̬
			blog = dao.getBolg(blogId);
		}

		blog.setBlogTitle(title);

		blog.setBlogBody(body);

		blog.setBlogDate(new Date());

		blog.setClassify(classifyDao.viewClassify(classifyid));
		

		String abs = new String(body);
		abs.replace("\r", "");
		abs.replace("\n", "");
		abs=abs.replace("</p>", "\r\n");
		abs=abs.replaceAll("<(.[^>]*)>","");
		abs=abs.replace("&nbsp;", "");
		abs=abs.replace(" ","");
		abs=abs.replace("��", "");
		if(abs.length()>280){
			abs = abs.substring(0,280);
			abs=abs+"����";
		}
		blog.setBlogAbstract(abs);

		String fiu = new String(body);
		if(fiu.indexOf("img")!=-1){
			fiu = fiu.substring(fiu.indexOf("img"));
			fiu = fiu.substring(fiu.indexOf("src"));
			fiu = fiu.substring(fiu.indexOf("\"")+1);
			fiu = fiu.substring(0,fiu.indexOf("\""));
		} else {
			fiu=null;
		}

		blog.setFirstImgUrl(fiu);
		
		if("true".equals(isedit)){
			dao.updateBlog(blog);
		}else{
			blog.setViews(0);
			dao.addBlog(blog);
		}
	}

	/**
	 * �г������б���ÿҳ������Ϊ10����̬��Ա����ά��
	 * @param classifyid ���·��࣬0����ȫ������
	 * @param tarpage Ŀ��ҳ��
	 * @return �����б�
	 */
	public List<BlogEntity> getBloglist(String classifyid , String tarpage, String pagesize){
		int cid=0;
		if(null!=classifyid && classifyid.trim().length()!=0){
			cid=new Integer(classifyid).intValue();
		}		
		ArrayList<BlogEntity> list =  new ArrayList<BlogEntity>(dao.listBlog(new Integer(tarpage).intValue(),new Integer(pagesize).intValue(), cid));
		Comparator<BlogEntity> c = new BlogDateComparator();
		Collections.sort(list, c);
		return list;
	}

	/**
	 * ��ȡ���ҳ��
	 * @param classifyid ���·��࣬0����ȫ������
	 * @return ���ҳ��
	 */
	public Integer getMaxPage(String classifyid, String pageSize){
		int cid=0;
		int pagesize= new Integer(pageSize).intValue();
		if(null!=classifyid && classifyid.trim().length()!=0){
			cid=new Integer(classifyid).intValue();
		}
		int maxpage = (dao.getBolgNum(cid)+pagesize-1)/pagesize;
		return new Integer(maxpage);
	}

	/**
	 * ��ȡĿ����������
	 * @param id ����id
	 * @return ��������
	 */
	public BlogEntity getBlog(int id){
		BlogEntity be = dao.getBolg(id);
		TreeSet<CommentEntity> comms = new TreeSet<>(be.getComments());
		be.setComments(comms);		
		return be;
	}

	/**
	 * ��ȡ������5ƪ�����б�
	 * @return 5ƪ�����б�
	 */
	public List<BlogEntity> getTop5Blog(){
		ArrayList<BlogEntity> list = new ArrayList<BlogEntity>(dao.listTop5Blog());
		Comparator<BlogEntity> c = new BlogViewsComparator();
		Collections.sort(list, c);
		return list;
	}
	
	public void delBlog(int id){
		rs.delRecommendByBlogID(id);
		dao.delBlog(id);
	}
	public void addviews(int id){
		dao.addViews(id);
	}
	
	public HashMap<String,ArrayList<String>> getComm5Blogs(){
		HashMap<String, ArrayList<String>> rst = new HashMap<String, ArrayList<String>>();
		ArrayList<Object[]> records = dao.listComm5Blogs();
		ArrayList<String> comms = new ArrayList<String>();
		ArrayList<String> blogid = new ArrayList<String>();
		ArrayList<String> blogtitle = new ArrayList<String>();
		for(int i=0 ; i<records.size() ; i++){			
			blogid.add(records.get(i)[0].toString());
			blogtitle.add(records.get(i)[1].toString());
			comms.add(records.get(i)[2].toString());
		}
		
		rst.put("comms", comms);
		rst.put("blogid", blogid);
		rst.put("blogtitle", blogtitle);
		
		return rst;
	}
}