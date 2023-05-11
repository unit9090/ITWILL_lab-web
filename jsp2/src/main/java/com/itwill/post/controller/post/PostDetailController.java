package com.itwill.post.controller.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.post.model.Post;
import com.itwill.post.service.PostService;

/**
 * Servlet implementation class PostDetailController
 */
@WebServlet(name="postDetailController", urlPatterns = { "/post/detail" })
public class PostDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(PostDetailController.class);
	
	private final PostService postService = PostService.getInstance();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("doGet()");
		
		String id = request.getParameter("id");
		Post post = postService.detail(id);
		
		request.setAttribute("post", post);
		
		request.getRequestDispatcher("/WEB-INF/post/detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("doPost()");
		
		Long id = Long.parseLong(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String author = request.getParameter("author");
		
		Post existingPost = postService.detail(request.getParameter("id"));
		Post post = new Post(id, title, content, author, existingPost.getCreatedTime(), null);
		
		int result = postService.update(post);
		log.info("update result = {}", result);
		
		response.sendRedirect("/post/post");
	}

}
