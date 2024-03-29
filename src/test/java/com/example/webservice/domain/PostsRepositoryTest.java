package com.example.webservice.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		/*
		 * 이후 테스트 코드에 영향을 미치지 않기 위해
		 * 테스트 메소드가 끝날때 마다 repository 전체 비우는 코드
		 */
		postsRepository.deleteAll();
	}
	@Test
	public void BaseTimeEntity_regist() {
		//given
		LocalDateTime now=LocalDateTime.now();
		postsRepository.save(Posts.builder()
				.title("테스트 게시글")
				.content("테스트 본문")
				.author("olikuj933@gmail.com")
				.build());
		//when
		List<Posts> postsList=postsRepository.findAll();
		
		//then
		Posts posts=postsList.get(0);
		
		assertTrue(posts.getCreatedDate().isAfter(now));
		assertTrue(posts.getModifiedDate().isAfter(now));
	}
	@Test
	public void getPosts() {
		postsRepository.save(Posts.builder()
				.title("테스트 게시글")
				.content("테스트 본문")
				.author("olikuj933@gmail.com")
				.build());
		//when
        List<Posts> postsList = postsRepository.findAll();
		//Optional<Posts> p=postsRepository.findById((long)2);
        //then
        Posts posts = postsList.get(0);
		//Posts posts=p.get();
        assertThat(posts.getTitle(), is("테스트 게시글"));
        assertThat(posts.getContent(), is("테스트 본문"));
	}
}
