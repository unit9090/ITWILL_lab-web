package com.itwill.spring2.dto;

import java.sql.Timestamp;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
// @Getter
// @Setter -> Getter만 쓴 이유는 읽기전용으로 사용
// @ToString
@Data
public class PostListDto {
	
	// field
	private long id;
	private String title;
	private String author;
	private Timestamp created_time;
	// -> JSTL에서는 LocalDateTime 객체를 사용하지 못하기 때문에 Timestamp로 변환
	
	private long rcnt;	// 댓글 개수
	
	// Post 타입의 객체를 PostListDto 타입의 객체로 변환해서 리턴하는 메서드.
	public static PostListDto fromEntity(Post entity) {
		
		return PostListDto.builder()
						  .id(entity.getId())
						  .title(entity.getTitle())
						  .author(entity.getAuthor())
						  .created_time(Timestamp.valueOf(entity.getCreated_time()))
						  .build();
	}
	
}
