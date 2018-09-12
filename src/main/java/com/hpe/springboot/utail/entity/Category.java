package com.hpe.springboot.utail.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 文章分类实体类
 * @author: admin
 * @date: 2018-09-03
 */
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cid;
	private String name;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category(int cid) {
		super();
		this.cid = cid;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(int cid, String name) {
		this.cid = cid;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", name=" + name + "]";
	}
	
}
