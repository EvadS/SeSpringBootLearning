package com.se.sample.mapper;

import com.se.sample.dto.PostDto;
import com.se.sample.entity.Post;
import com.se.sample.entity.Tag;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class PostDtoUnitTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertPostEntityToPostDto_thenCorrect() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("title");
        post.setDescription("Description");

        // before java 8
        //Set<Tag> tags = new HashSet<>(Arrays.asList(new Tag("tag1"),new Tag("tag2")));
        //post.setTags(tags);

        // JAVA 8
        Set<Tag> strSet2 = Stream.of(new Tag("tag1"),new Tag("tag2"))
                .collect(Collectors.toCollection(HashSet::new));

        PostDto postDto = modelMapper.map(post, PostDto.class);
        assertEquals(post.getContent(), postDto.getContent());
        assertEquals(post.getTitle(), postDto.getTitle());

        String postTagName = post.getTags().stream().findFirst().get().getName();
        String postDtoTagName = post.getTags().stream().findFirst().get().getName();

        assertEquals(postTagName, postDtoTagName );
    }
}
