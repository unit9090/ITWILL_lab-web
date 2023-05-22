package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Reply;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
	locations = { "file:src/main/webapp/WEB-INF/application-context.xml" }
)
public class ReplyRepositoryTest {
	
	// Repository 객체를 주입받음(의존성 주입, DI)
	@Autowired
	private ReplyRepository replyRepository;
	
	// @Test
	public void test() {
		Assertions.assertNotNull(replyRepository);
		log.info(replyRepository.toString());
		
		List<Reply> list = replyRepository.selectByPostId(23);
		for(Reply reply : list) {
			log.info(reply.toString());
		}
	}
	
	// @Test
	public void testInsert() {
		Reply entity = Reply.builder()
							.post_id(41)
							.reply_text("JUnit Text")
							.writer("test")
							.build();
		
		int result = replyRepository.insert(entity);
		log.info("result = {}", result);
		Assertions.assertEquals(result, 1);
		
	}
	
	// @Test
	public void testUpdate() {
		Reply entity = Reply.builder()
							.id(2)
							.reply_text("JUnit Test - update 테스트")
							.build();
		int result = replyRepository.update(entity);
		log.info("result = {}", result);
		Assertions.assertEquals(result, 1);
	}
	
	// @Test
	public void testDelete() {
		int result = replyRepository.delete(2);
		log.info("result = {}", result);
		Assertions.assertEquals(result, 1);
	}
	
	// @Test
	public void testCount() {
		long result = replyRepository.selectReplyCountWithPostId(23);
		log.info("result = {}", result);
	}
	
}
