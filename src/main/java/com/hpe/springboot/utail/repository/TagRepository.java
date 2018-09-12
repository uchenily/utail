package com.hpe.springboot.utail.repository;

import org.springframework.data.repository.CrudRepository;

import com.hpe.springboot.utail.entity.Tag;

/**
 * 标签(Tag)数据访问接口, 继承自CrudRepository
 * @author admin
 *
 */
public interface TagRepository extends CrudRepository<Tag, Integer> {

	public Tag findTagByName(String name);
}
