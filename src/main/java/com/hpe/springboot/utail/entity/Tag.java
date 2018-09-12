package com.hpe.springboot.utail.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 文章标签(Tag)实体类
 * @author: admin
 * @date: 2018-09-03
 */
@Entity
// 给标签名添加唯一约束
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="name")})
public class Tag {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int tid;
	private String name;
	@ManyToMany
	@JoinTable(
			name = "posts_tags",
			joinColumns = @JoinColumn(name = "tid"),
			inverseJoinColumns = @JoinColumn(name = "pid"))
	private Set<Post> posts = new HashSet<>();
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tag(int tid, String name) {
		super();
		this.tid = tid;
		this.name = name;
	}
	
	// 注意, 这里只是返回标签名, thymeleaf模板中使用
	// #strings.setJoin(post.tags,',')}, 将Set<Tag>转化为以','分割的字符串 
	@Override
	public String toString() {
		return name;
	}
	
	
}
