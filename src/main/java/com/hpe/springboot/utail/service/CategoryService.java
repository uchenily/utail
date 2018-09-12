package com.hpe.springboot.utail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpe.springboot.utail.entity.Category;
import com.hpe.springboot.utail.repository.CategoryRepository;

/**
 * 分类管理(Category)业务逻辑层
 * @author: admin
 * @date: 2018-09-06
 */
@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	/**
	 * 获取所有分类对象
	 * @return
	 */
	public Iterable<Category> getAll() {
		return categoryRepository.findAll();
	}

	/**
	 * 添加或者更新分类(Category)信息
	 * @param category
	 * @return
	 */
	public void addOrUpdateCagetory(Category category) {
		Category findOne = categoryRepository.findOne(category.getCid());
		if(findOne != null) {
			category.setCid(findOne.getCid());
		}
		categoryRepository.save(category);
	}

	/**
	 * 通过ID获取分类(Category)信息
	 * @param id
	 * @return
	 */
	public Category getCategoryById(int id) {
		return categoryRepository.findOne(id);
	}

	/**
	 * 通过ID删除分类(Category)对象, 删除成功返回true, 否则返回false
	 * @param id
	 * @return
	 */
	public boolean deleteCategoryById(int id) {
		categoryRepository.delete(id);
		return !categoryRepository.exists(id);
	}
}
