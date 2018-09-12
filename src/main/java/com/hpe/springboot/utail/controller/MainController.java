package com.hpe.springboot.utail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hpe.springboot.utail.entity.Post;
import com.hpe.springboot.utail.service.PostService;

/**
 * 文章(Post)控制层
 * @author: admin
 * @date: 2018-09-03
 */
@RestController
public class MainController {
	@Autowired
	private PostService postService;

	@GetMapping("/")
	public ModelAndView main() {
		ModelAndView modelAndView = new ModelAndView("redirect:/index");
		return modelAndView;
	}

	// 参数的格式是 ?page=2&size=2
	@GetMapping("/index")
	public ModelAndView index(
			@PageableDefault(
					value = 9, 
					page = 0,
					sort = { "pid" }, 
					direction = Sort.Direction.DESC) 
			Pageable pageable,
			@RequestParam(
					defaultValue = "0", 
					required = false) 
			int page) {
		ModelAndView modelAndView = new ModelAndView("/index");
		Page<Post> postPage = postService.getAll(pageable);
		List<Post> posts = postPage.getContent();
		int totalPages = postPage.getTotalPages();
		modelAndView.addObject("posts", posts);
		modelAndView.addObject("totalPages", totalPages);
		modelAndView.addObject("currentPage", page);
		return modelAndView;
	}
	
	@GetMapping("/about")
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView("/about");
		return modelAndView;
	}

	@GetMapping("/post/{pid}")
	public ModelAndView getPost(@PathVariable int pid) {
		Post post = postService.getPostById(pid);
		ModelAndView modelAndView = null;
		if (post == null) {
			modelAndView = new ModelAndView("/404");
		} else {
			String content = postService.getFormatedContent(post.getContentPath());
			modelAndView = new ModelAndView("/post-detail");
			modelAndView.addObject("post_content", content);
			modelAndView.addObject("post", post);
		}
		return modelAndView;
	}
}
