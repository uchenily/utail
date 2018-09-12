package com.hpe.springboot.utail.repository;

import org.springframework.data.repository.CrudRepository;

import com.hpe.springboot.utail.entity.User;

/**
 * 用户数据访问层接口, 继承自CrudRepository
 * @author: admin
 * @date: 2018-09-04
 */
public interface UserRepository extends CrudRepository<User, Integer> {
	
	/**
	 * 通过nickname获取User
	 * 因为设置了nickname唯一约束, 所以返回的只会是一个User或者返回空
	 * @param nickname
	 * @return
	 */
	public User findUserByNickname(String nickname);
}
