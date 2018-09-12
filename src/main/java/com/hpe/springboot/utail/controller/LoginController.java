package com.hpe.springboot.utail.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hpe.springboot.utail.service.UserService;

/**
 * 登录控制层
 * @author: admin
 * @date: 2018-09-03
 */
@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("/login");
		return modelAndView;
	}
	
	@PostMapping("/validate")
	public ModelAndView validate(
			@RequestParam String nickname, 
			@RequestParam String password,
			HttpSession session) {
		ModelAndView modelAndView = null;
		// 用户名密码验证
		if(userService.isValidated(nickname, password)) {
			session.setAttribute("nickname", nickname);
			modelAndView = new ModelAndView("redirect:/index");
		} else {
			String info = "nickname or password was wrong.";
			modelAndView = new ModelAndView("/login");
			modelAndView.addObject("info", info);
		}
		return modelAndView;
	}
	
	@GetMapping("/logout/{nickname}")
	public ModelAndView logout(@PathVariable String nickname, HttpSession session) {
		session.removeAttribute("nickname");
		ModelAndView modelAndView = new ModelAndView("redirect:/index");
		return modelAndView;
	}
}
