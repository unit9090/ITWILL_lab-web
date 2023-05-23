package com.itwill.spring2.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.dto.ReplyCreateDto;
import com.itwill.spring2.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyController {
	
	private final ReplyService replyService;
	
	@PostMapping
	public ResponseEntity<Integer> createReply(@RequestBody ReplyCreateDto dto) {
		log.info("createReply(dto = {})", dto);
		
		int result = replyService.create(dto);
		
		if(result != 1) {
			return ResponseEntity.status(500).build();
		}
		
		return ResponseEntity.ok(result);
	}
}
