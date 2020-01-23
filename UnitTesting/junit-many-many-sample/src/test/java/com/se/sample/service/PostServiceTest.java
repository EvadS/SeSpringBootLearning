package com.se.sample.service;

import com.se.sample.model.Post;
import com.se.sample.repository.PostRepository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PostServiceTest {

    @MockBean
    private PostRepository postRepository;

    private PostService postService;


    @Before
    public void init(){
        postService = new PostService(postRepository);
    }

    @org.junit.Test
    public void getPostById() {

        BDDMockito
                .given(this.postRepository.getOne(1l))
                .willReturn(new Post(1l,"title","description", "content" ));

        Post actual = postService.getPostById(1l);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(1l);

        assertThat(actual.getTitle()).isEqualTo("title");
    }
}