package blog.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue
	@Column(length = 11, nullable = false)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Topic topic;
	@Column(length = 16536, nullable = false)
	private String title;
	@Column(length = 16536, nullable = false)
	private String text;

	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public Topic getTopic(){
		return topic;
	}
	public void setTopic(Topic topicId){
		this.topic=topic;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;	
	}
	public String getText(){
		return text;
	}
	public void setText(String text){
		this.text = text;
	}

	public String toString(){
		return "{ \"id\": "+id+", \"title\":\"" + title+ "\", \"text\": \""+text+"\" }";
	}
}
