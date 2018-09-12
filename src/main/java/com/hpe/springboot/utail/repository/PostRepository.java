package com.hpe.springboot.utail.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.hpe.springboot.utail.entity.Post;

/**
 * 文章(Post)数据访问层接口, 继承自CurdRepository
 * @author: admin
 * @date: 2018-09-03
 */
public interface PostRepository extends CrudRepository<Post, Integer> {
	
	/**
	 * 分页实现
	 * @param pageable
	 * @return
	 */
	public Page<Post> findAll(Pageable pageable);
}