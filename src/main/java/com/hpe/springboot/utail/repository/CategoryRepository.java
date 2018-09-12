package com.hpe.springboot.utail.repository;

import org.springframework.data.repository.CrudRepository;

import com.hpe.springboot.utail.entity.Category;

/**
 * 分类(Category)数据访问层接口
 * @author: admin
 * @date: 2018-09-06
 */
public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
