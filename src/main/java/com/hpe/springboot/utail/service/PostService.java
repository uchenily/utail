package com.hpe.springboot.utail.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hpe.springboot.utail.entity.Post;
import com.hpe.springboot.utail.repository.PostRepository;
import com.hpe.springboot.utail.util.TimeUtil;

/**
 * 文章(Post)业务逻辑层
 * @author: admin
 * @date: 2018-09-03
 */
@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	private static final String PREFIX = "src/main/resources/static/post/";
	
	/**
	 * 通过ID获取文章(Post)
	 * @param pid
	 * @return
	 */
	public Post getPostById(int pid) {
		return postRepository.findOne(pid);
	}

	/**
	 * 获取文章内容, 为html格式的字符串
	 * @param contentPath 文章路径, 文章存放在 [项目/PREFIX] 下
	 * @return
	 */
	public String getFormatedContent(String contentPath) {
		File file = null;
		FileReader fr = null;
		try {
			file = new File(PREFIX + contentPath);
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		char[] buf = new char[1024];
		int len = 0;
		try {
			while((len = fr.read(buf)) != -1) {
				sb.append(buf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String markdownContent = sb.toString();
		
		PegDownProcessor pdp = new PegDownProcessor();
		String htmlContent = pdp.markdownToHtml(markdownContent);
		return htmlContent;
	}

	/**
	 * 获取所有文章(Post)信息
	 * @return
	 */
	public Iterable<Post> getAll() {
		Iterable<Post> posts = postRepository.findAll();
		return posts;
	}
	
	/**
	 * 获取所有文章信息(分页方式)
	 * @param pageable
	 * @return
	 */
	public Page<Post> getAll(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	/**
	 * 添加或者更新文章到数据库
	 * 如果成功返回true, 否则返回false
	 * @param post
	 * @return
	 */
	public boolean addOrUpdatePost(Post post) {
		Post findOne = postRepository.findOne(post.getPid());
		if(findOne != null) { // 更新
			post.setPid(findOne.getPid());
		} // else{} 新增
		Post newPost = postRepository.save(post);
		if(newPost == null) {
			return false;
		}
		return true;
	}

	/**
	 * 保存文章信息到本地磁盘, 路径在  [项目/PREFIX] 下
	 * @param content
	 * @param contentPath
	 */
	public void saveContent(String content, String contentPath) {
		File file = null;
		FileWriter fw = null;
		try {
			file = new File(PREFIX + contentPath);
			fw = new FileWriter(file);
			fw.write(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 通过ID删除文章(Post)信息, 删除成功返回true, 否则返回 false
	 * @param id
	 * @return
	 */
	public boolean deletePostById(int id) {
		postRepository.delete(id);
		return !postRepository.exists(id);
	}

	/**
	 * 获取文章内容(原始格式)
	 * @param contentPath 文章路径, 文章存放在 [项目/PREFIX] 下
	 * @return
	 */
	public String getContent(String contentPath) {
		File file = null;
		FileReader fr = null;
		try {
			file = new File(PREFIX + contentPath);
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		char[] buf = new char[1024];
		int len = 0;
		try {
			while((len = fr.read(buf)) != -1) {
				sb.append(buf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 在 [项目/PREFIX] 路径下生成和文件, 文件名格式是  {"title" + "2018-09-10" + ".md"}
	 * @param title
	 * @return
	 */
	public String generateContentPath(String title, Date date) {
		return title.trim() + TimeUtil.getFormatedDate(date) + ".md";
	}
	
}
