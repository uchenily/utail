package com.hpe.springboot.utail.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hpe.springboot.utail.entity.User;
import com.hpe.springboot.utail.service.UserService;

@RestController
public class RegisterController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView("/register");
		return modelAndView;
	}

	@PostMapping("/register")
	public ModelAndView registerCheck(@RequestParam String username, @RequestParam String nickname,
			@RequestParam String email, @RequestParam String password, HttpSession session) {
		ModelAndView modelAndView = null;
		if (userService.isExists(nickname)) { // 昵称冲突
			String info = "nickname has exists!";
			modelAndView = new ModelAndView("/register");
			modelAndView.addObject("info", info);
		} else { // 昵称没有冲突, 添加注册信息到数据库
			Date createdTime = new Date();
			User user = new User(0, nickname, username, password, email, createdTime);
			userService.addOrUpdateUser(user);
			session.setAttribute("nickname", user.getNickname());
			modelAndView = new ModelAndView("redirect:/index");
		}
		return modelAndView;
	}
}
