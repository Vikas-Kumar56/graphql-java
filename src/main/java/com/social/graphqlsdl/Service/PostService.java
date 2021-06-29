package com.social.graphqlsdl.Service;

import com.social.graphqlsdl.dto.AuthorDto;
import com.social.graphqlsdl.dto.PostDto;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<PostDto> getAllPostByAuthorId(UUID authorId);

    List<PostDto> getRecentPosts(int count, int offset);

    UUID cratePost(PostDto postDto);

    Integer getPostCountByAuthorId(UUID id);

    PostDto getPostById(UUID postId);

    AuthorDto getAuthorById(UUID authorId);
}
