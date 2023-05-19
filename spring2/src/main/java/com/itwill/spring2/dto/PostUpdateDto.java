package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateDto {
	
	private long id;
	private String title;
	private String content;
	
	public Post toEntity() {
		return Post.builder()
					.id(id)
					.title(title)
					.content(content)
					.build();
	}
}
