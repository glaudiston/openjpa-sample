package blog.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

@Entity
@Table(name = "topic")
public class Topic {
	@Id
	@GeneratedValue
	@Column(length = 11, nullable = false)
	private int id;
	@Column(length = 255, nullable = false)
	private String name;

	@OneToMany( mappedBy = "topic", fetch = FetchType.LAZY )
	private List<Post> posts;

	public Topic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Topic(){
		super();
	}

	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public List<Post> getPosts(){
		return posts;
	}
	public void setPosts(List<Post> posts){
		this.posts = posts;
	}

	public String toString(){
		return "{ \"id\": \"" + id  + "\", \"name\": \""+name+"\", \"posts\": " + posts + " }";
	}
}
