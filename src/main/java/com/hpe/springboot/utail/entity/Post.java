package com.hpe.springboot.utail.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * 文章实体类
 * @author: admin
 * @date: 2018-09-03
 */
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pid;
	private String title;
	private String author;
	private String contentPath; 			// 文章存放的路径
	private String imagePath;				// 图片路径
	private String description; 			// 文章简述
	/*
	 * cascade all: 所有情况下均进行级联操作
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name = "posts_tags", 
			joinColumns = @JoinColumn(name = "pid"), 
			inverseJoinColumns = @JoinColumn(name = "tid"))
	private Set<Tag> tags = new HashSet<>();// 文章标签, 文章可以有多个标签
	@ManyToOne(targetEntity = Category.class)
	private Category category;				// 文章分类, 每一篇文章只对应一种分类
	private Date createdTime;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContentPath() {
		return contentPath;
	}
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Post(int pid, String title, String author, String contentPath, String imagePath, String description,
			Category category, Date createdTime) {
		super();
		this.pid = pid;
		this.title = title;
		this.author = author;
		this.contentPath = contentPath;
		this.imagePath = imagePath;
		this.description = description;
		// this.tags = tags;
		this.category = category;
		this.createdTime = createdTime;
	}
	@Override
	public String toString() {
		return "Post [pid=" + pid + ", title=" + title + ", author=" + author + ", contentPath=" + contentPath
				+ ", imagePath=" + imagePath + ", description=" + description + ", tags=" + tags + ", category="
				+ category + ", createdTime=" + createdTime + "]";
	}
	
	
//  有时间再添加评论和浏览量以及是否可见
//	private List<Comment> comments;	// 文章评论
//	private int visitedCount;		// 文章浏览量
// 	private boolean canAccess;		// 文章可访问
	
	
}
