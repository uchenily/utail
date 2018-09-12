package com.hpe.springboot.utail.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 用户实体类
 * @author: admin
 * @date: 2018-09-03
 */
@Entity
// 给nickname字段添加唯一约束(当然id也是唯一的, 在这里不需要设置)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="nickname")})
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nickname;
	private String username;
	private String password;
	private String email;
	private Date createdTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String nickname, String username, String password, String email, Date createdTime) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.createdTime = createdTime;
	}
	
	public User(String nickname, String username, String password, String email, Date createdTime) {
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.createdTime = createdTime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", createdTime=" + createdTime + "]";
	}
	
}
