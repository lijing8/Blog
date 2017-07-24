package springTest.web.entity;



import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_blog")
public class BlogEntity{//T_BLOG
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "identity")
	@GeneratedValue(generator = "idGenerator")
	private long id;
	
	@Column(length=80,nullable=false)
	private String blogTitle;
	
	@Column(length=10000)
	private String blogBody;
	
	@Column
	private Date blogDate;
	
	@Column(length=300)
	private String blogAbstract;
	
	@Column(length=2038)
	private String firstImgUrl;
	
	@Column
	private int views;
	
	@ManyToOne
	private ClassifyEntity classify;
	
	@OneToMany(cascade={CascadeType.ALL,CascadeType.REMOVE}, fetch=FetchType.EAGER)
	private Set<CommentEntity> comments;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogBody() {
		return blogBody;
	}

	public void setBlogBody(String blogBody) {
		this.blogBody = blogBody;
	}

	public Date getBlogDate() {
		return blogDate;
	}

	public void setBlogDate(Date blogDate) {
		this.blogDate = blogDate;
	}

	public String getBlogAbstract() {
		return blogAbstract;
	}

	public void setBlogAbstract(String blogAbstract) {
		this.blogAbstract = blogAbstract;
	}

	public String getFirstImgUrl() {
		return firstImgUrl;
	}

	public void setFirstImgUrl(String firstImgUrl) {
		this.firstImgUrl = firstImgUrl;
	}

	public ClassifyEntity getClassify() {
		return classify;
	}

	public void setClassify(ClassifyEntity classify) {
		this.classify = classify;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Set<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(Set<CommentEntity> comments) {
		this.comments = comments;
	}

	
}