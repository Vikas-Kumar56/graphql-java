package com.social.graphqlsdl.resolver.comment;

import com.social.graphqlsdl.Service.PostService;
import com.social.graphqlsdl.dto.AuthorDto;
import com.social.graphqlsdl.dto.CommentDto;
import com.social.graphqlsdl.dto.PostDto;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class CommentFieldResolver implements GraphQLResolver<CommentDto> {

    private final PostService postService;

    public CommentFieldResolver(PostService postService) {
        this.postService = postService;
    }


    public PostDto getPost(CommentDto commentDto) {
        return postService.getPostById(commentDto.getPostId());
    }

    public AuthorDto getAuthor(CommentDto commentDto) {
        return postService.getAuthorById(commentDto.getAuthorId());
    }
}
