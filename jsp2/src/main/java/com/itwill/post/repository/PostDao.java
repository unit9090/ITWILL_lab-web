package com.itwill.post.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.post.datasource.HikariDataSourceUtil;
import com.itwill.post.model.Post;
import com.zaxxer.hikari.HikariDataSource;

// Repository(Persistence) Layer(저장소/영속성 계층)
// DB CRUD(Create, Read, Update, Delete) 작업을 수행하는 계층
public class PostDao {
	// Slf4j 로깅 기능 사용:
	private static final Logger log = LoggerFactory.getLogger(PostDao.class);
	
	// singleton
	private static PostDao instance = null;
	
	private HikariDataSource ds;
	
	private PostDao() {
		ds = HikariDataSourceUtil.getInstance().getDataSource();
	}
	
	public static PostDao getInstance() {
		if(instance == null) {
			instance = new PostDao();
		}
		
		return instance;
	}
	
	// POSTS 테이블에서 전체 레코드를 id 내림차순으로 정렬(최근 작성 포스트 먼저)해서 검색.
	private static final String SQL_SELECT_ALL =
			"SELECT * FROM POSTS ORDER BY ID DESC";
	
	public List<Post> select() {
		List<Post> list = new ArrayList<>();
		
		log.info(SQL_SELECT_ALL);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// pool에서 Connection 객체를 빌려옴.
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				// 테이블 컬럼 내용을 Post 타입 객체로 변환하고 리스트에 추가:
				Post post = recodeToPost(rs);
				list.add(post);
			}
			
			log.info("# of rows = {}", list.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();	// 물리적인 접속 해제가 아니라, pool에 반환!
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	private Post recodeToPost(ResultSet rs) throws SQLException {
		Long id = rs.getLong("ID");
		String title = rs.getString("TITLE");
		String content = rs.getString("CONTENT");
		String author = rs.getString("AUTHOR");
		LocalDateTime create = rs.getTimestamp("CREATED_TIME").toLocalDateTime();
		LocalDateTime modify = rs.getTimestamp("MODIFIED_TIME").toLocalDateTime();
		
		Post post = new Post(id, title, content, author, create, modify);
		
		return post;
	}

	// 새 포스트 작성
	private static final String SQL_INSERT = 
			"INSERT INTO POSTS (TITLE, CONTENT, AUTHOR) VALUES (?, ?, ?)";
			
	public int insert(Post post) {
		log.info("insert({})", post);
		log.info(SQL_INSERT);
		
		int result = 0;	// executeUpdate() 결과(insert 결과)를 저장할 변수
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, post.getTitle());
			stmt.setString(2, post.getContent());
			stmt.setString(3, post.getAuthor());
			
			
			result = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				stmt.close();
				conn.close();				
			} catch (Exception e) {
				e.printStackTrace();				
			}			
		}
		
		return result;
	}

	// 포스트 번호로 검색
	private static final String SQL_SELECT_BY_ID =
			"SELECT * FROM POSTS WHERE ID = ?";
	
	public Post select(long id) {
		log.info("select(id = {})", id);
		log.info(SQL_SELECT_BY_ID);
		
		Post post = null;		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setLong(1, id);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				post = recodeToPost(rs);
			}
			
//			log.info("post = {}", post.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return post;
	}

	// 해당 아이디의 포스트의 제목과 내용, 수정 시간을 업데이트
	private static final String SQL_UPDATE =
			"UPDATE POSTS SET "
			+ "TITLE = ?, "
			+ "CONTENT = ?, "
			+ "MODIFIED_TIME = SYSDATE "
			+ "WHERE ID = ?";
	
	public int update(Post post) {
		log.info("update({})", post);
		log.info(SQL_UPDATE);
		
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, post.getTitle());
			stmt.setString(2, post.getContent());
			stmt.setLong(3, post.getId());
			
			result = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				stmt.close();
				conn.close();				
			} catch (Exception e) {
				e.printStackTrace();				
			}			
		}
		
		return result;
	}

	
	// 포스트 아이디(PK)로 삭제하기:
	private static final String SQL_DELETE_ID =
			"DELETE FROM POSTS WHERE ID = ?";
	
	public int delete(long id) {
		log.info("delete(id = {})", id);
		log.info(SQL_DELETE_ID);
		
		int result = 0;	// SQL 실행 결과를 저장할 변수
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(SQL_DELETE_ID);
			stmt.setLong(1, id);
			
			result = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	private static final String SQL_SEARCH_KEYWORD =
			"SELECT * FROM POSTS WHERE "
			+ "LOWER(TITLE) LIKE LOWER(?) OR "
			+ "LOWER(CONTENT) LIKE LOWER(?) OR "
			+ "LOWER(AUTHOR) LIKE LOWER(?) "			
			+ "ORDER BY ID DESC";
	
	public List<Post> read(String category, String keyword) {
		List<Post> list = new ArrayList<>();		
		log.info(SQL_SEARCH_KEYWORD);
		log.info("category = {}, keyword = {}", category, keyword);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SEARCH_KEYWORD);
			
			if(category.equals("t")) {
				stmt.setString(1, "%" + keyword + "%");
				stmt.setString(2, "");
				stmt.setString(3, "");
			} else if(category.equals("c")) {
				stmt.setString(1, "");
				stmt.setString(2, "%" + keyword + "%");
				stmt.setString(3, "");
			} else if(category.equals("tc")) {
				stmt.setString(1, "%" + keyword + "%");
				stmt.setString(2, "%" + keyword + "%");
				stmt.setString(3, "");
			} else if(category.equals("a")) {
				stmt.setString(1, "");
				stmt.setString(2, "");
				stmt.setString(3, "%" + keyword + "%");
			}
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				// 테이블 컬럼 내용을 Post 타입 객체로 변환하고 리스트에 추가:
				Post post = recodeToPost(rs);
				list.add(post);
			}
			
			log.info("# of rows = {}", list.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();	// 물리적인 접속 해제가 아니라, pool에 반환!
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
}
