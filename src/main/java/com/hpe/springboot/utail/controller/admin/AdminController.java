package com.hpe.springboot.utail.controller.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hpe.springboot.utail.entity.Category;
import com.hpe.springboot.utail.entity.Post;
import com.hpe.springboot.utail.entity.Tag;
import com.hpe.springboot.utail.entity.User;
import com.hpe.springboot.utail.service.CategoryService;
import com.hpe.springboot.utail.service.PostService;
import com.hpe.springboot.utail.service.TagService;
import com.hpe.springboot.utail.service.UserService;

@RestController
public class AdminController {

	@Autowired
	private PostService postService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TagService tagService;
	@Autowired
	private UserService userService;
	
	/*************** index ***************/
	@GetMapping("/admin")
	public ModelAndView admin() {
		ModelAndView modelAndView = new ModelAndView("/admin/index");
		return modelAndView;
	}
	
	/*************** post ***************/
	@GetMapping("/admin/post")
	public ModelAndView adminPost() {
		Iterable<Post> posts = postService.getAll();
		ModelAndView modelAndView = new ModelAndView("/admin/post_all");
		modelAndView.addObject("posts", posts);
		return modelAndView;
	}
	
	@PostMapping("/admin/post")
	public ModelAndView createOrUpdatePost(
			@RequestParam(defaultValue="0", required=false) int pid,
			@RequestParam String title,
			@RequestParam String author,
			@RequestParam String imagePath,
			@RequestParam String description,
			@RequestParam String tags,
			@RequestParam int categoryId,
			@RequestParam String content) {
		ModelAndView modelAndView = null;
		Category category = new Category(categoryId);
		Date createdTime = new Date();
		String contentPath = postService.generateContentPath(title, createdTime);
		Post post = new Post(pid, title, author, contentPath, imagePath, description, category, createdTime);
		String[] sTags = tags.split(",");
		for(String tagName : sTags) {
			Tag tag = tagService.getTagByName(tagName);
			post.getTags().add(tag);
		}
		boolean success = postService.addOrUpdatePost(post);
		if(success) {
			postService.saveContent(content, contentPath);
			modelAndView = new ModelAndView("redirect:/admin/post");
		} else {
			modelAndView = new ModelAndView("/admin/error");
		}
		
		return modelAndView;
	}
	
	@GetMapping("/admin/newpost")
	public ModelAndView newPost() {
		ModelAndView modelAndView = new ModelAndView("/admin/post_new");
		Iterable<Category> categorys = categoryService.getAll();
		modelAndView.addObject("categorys", categorys);
		return modelAndView;
	}
	
	@GetMapping("/admin/updatepost/{id}")
	public ModelAndView updatePost(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView("/admin/post_update");
		Post post = postService.getPostById(id);
		modelAndView.addObject("post", post);
		Iterable<Category> categorys = categoryService.getAll();
		modelAndView.addObject("categorys", categorys);
		String content = postService.getContent(post.getContentPath());
		modelAndView.addObject("post_content", content);
		System.out.println(content);
		return modelAndView;
	}
	
	@GetMapping("/admin/deletepost/{id}")
	public ModelAndView deletePost(@PathVariable int id) {
		ModelAndView modelAndView = null;
		boolean success = postService.deletePostById(id);
		if(success) {
			modelAndView = new ModelAndView("redirect:/admin/post");
		} else {
			modelAndView = new ModelAndView("/admin/error");
		}
		return modelAndView;
	}
	
	/*************** user ***************/
	@GetMapping("/admin/user")
	public ModelAndView adminUser() {
		Iterable<User> users = userService.getAll();
		ModelAndView modelAndView = new ModelAndView("/admin/user_all");
		modelAndView.addObject("users", users);
		return modelAndView;
	}
	
	@PostMapping("/admin/user")
	public ModelAndView createOrUpdateUser(
			@RequestParam(defaultValue="0", required=false) int id,
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String nickname,
			@RequestParam String email) {
		ModelAndView modelAndView = null;
		Date createdTime = new Date();
		User user = new User(id, nickname, username, password, email, createdTime);
		boolean success = userService.addOrUpdateUser(user);
		if(success) {
			modelAndView = new ModelAndView("redirect:/admin/user");
		} else {
			modelAndView = new ModelAndView("main/error");
		}
		return modelAndView;
	}
	
	@GetMapping("/admin/newuser")
	public ModelAndView newUser() {
		ModelAndView modelAndView = new ModelAndView("/admin/user_new");
		return modelAndView;
	}
	
	@GetMapping("/admin/updateuser/{id}")
	public ModelAndView updateUser(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView("/admin/user_update");
		User user = userService.getUserById(id);
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	@GetMapping("/admin/deleteuser/{id}")
	public ModelAndView deleteUser(@PathVariable int id) {
		ModelAndView modelAndView = null;
		boolean success = userService.deleteUserById(id);
		if(success) {
			modelAndView = new ModelAndView("redirect:/admin/user");
		} else {
			modelAndView = new ModelAndView("/admin/error");
		}
		return modelAndView;
	}
	
	/*************** category ***************/
	@GetMapping("/admin/category")
	public ModelAndView adminCategory() {
		Iterable<Category> categorys = categoryService.getAll();
		ModelAndView modelAndView = new ModelAndView("/admin/category_all");
		modelAndView.addObject("categorys", categorys);
		return modelAndView;
	}
	
	@PostMapping("/admin/category")
	public ModelAndView createOrUpdateCategory(
			@RequestParam(defaultValue="0", required=false) int cid,
			@RequestParam String name) {
		ModelAndView modelAndView = null;
		Category category = new Category(cid, name);
		categoryService.addOrUpdateCagetory(category);
		modelAndView = new ModelAndView("redirect:/admin/category");
		return modelAndView;
	}
	
	@GetMapping("/admin/updatecategory/{id}")
	public ModelAndView updateCatetory(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView("/admin/category_all");
		Category category = categoryService.getCategoryById(id);
		modelAndView.addObject("category", category);
		Iterable<Category> categorys = categoryService.getAll();
		modelAndView.addObject("categorys", categorys);
		return modelAndView;
	}
	
	@GetMapping("/admin/deletecategory/{id}")
	public ModelAndView deleteCategory(@PathVariable int id) {
		ModelAndView modelAndView = null;
		boolean success = categoryService.deleteCategoryById(id);
		if(success) {
			modelAndView = new ModelAndView("redirect:/admin/category");
		} else {
			modelAndView = new ModelAndView("/admin/error");
		}
		return modelAndView;
	}
}
