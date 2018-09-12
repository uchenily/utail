package com.hpe.springboot.utail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpe.springboot.utail.entity.Post;
import com.hpe.springboot.utail.entity.User;
import com.hpe.springboot.utail.repository.UserRepository;

/**
 * 用户业务逻辑层
 * @author: admin
 * @date: 2018-09-04
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 根据ID判断用户是否存在
	 * @param id
	 * @return
	 */
	public boolean isExists(int id) {
		return userRepository.exists(id);
	}
	
	/**
	 * 根据用户昵称判断用户是否存在
	 * @param nickname
	 * @return
	 */
	public boolean isExists(String nickname) {
		User user = userRepository.findUserByNickname(nickname);
		if(user == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 判断昵称和密码是否合法
	 * @param nickname
	 * @param password
	 * @return
	 */
	public boolean isValidated(String nickname, String password) {
		User user = userRepository.findUserByNickname(nickname);
		if(user != null) {
			if(password.equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 添加或者更新用户, 如果添加成功返回true, 否则返回false(nickname出现冲突时)
	 * @param user
	 * @return
	 */
	public boolean addOrUpdateUser(User user) {
		User findOne = userRepository.findOne(user.getId());
		if(findOne != null) { // 更新
			user.setId(findOne.getId());
		} // else {} 新增
		User newUser = userRepository.save(user);
		if(newUser == null) {
			return false;
		}
		return true;
	}

	/**
	 * 返回所有用户
	 * @return
	 */
	public Iterable<User> getAll() {
		return userRepository.findAll();
	}

	/**
	 * 通过id获取User对象
	 * @param id
	 * @return
	 */
	public User getUserById(int id) {
		return userRepository.findOne(id);
	}

	/**
	 * 通过ID删除用户(User)对象, 删除成功返回true, 否则返回false
	 * @param id
	 * @return
	 */
	public boolean deleteUserById(int id) {
		userRepository.delete(id);
		return !userRepository.exists(id);
	}
}
