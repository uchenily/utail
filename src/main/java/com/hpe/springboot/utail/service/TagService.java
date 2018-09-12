package com.hpe.springboot.utail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpe.springboot.utail.entity.Tag;
import com.hpe.springboot.utail.repository.TagRepository;

/**
 * 标签管理业务逻辑层
 * @author: admin
 * @date: 2018-09-06
 */
@Service
public class TagService {

	@Autowired
	private TagRepository tagRepository;
	
	/**
	 * 通过标签名(tagName)获取标签对象
	 * 先在数据库中查找标签是否存在, 如果不存在则执行添加数据操作
	 * @param tagName
	 * @return
	 */
	public Tag getTagByName(String tagName) {
		Tag tag = tagRepository.findTagByName(tagName);
		if(tag == null) {
			tagRepository.save(new Tag(0, tagName));
		}
		return tagRepository.findTagByName(tagName);
	}

}
