package com.social.graphqlsdl.resolver.post;

import com.social.graphqlsdl.Service.AuthorService;
import com.social.graphqlsdl.Service.CommentService;
import com.social.graphqlsdl.dto.AuthorDto;
import com.social.graphqlsdl.dto.CommentDto;
import com.social.graphqlsdl.dto.PostDto;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostFieldResolver implements GraphQLResolver<PostDto> {

    private final AuthorService authorService;
    private final CommentService commentService;

    public PostFieldResolver(AuthorService authorService, CommentService commentService) {
        this.authorService = authorService;
        this.commentService = commentService;
    }

    public AuthorDto getAuthor(PostDto postDto) {
        return authorService.getAuthorById(postDto.getAuthorId());
    }

    public List<CommentDto> getComments(PostDto postDto, Integer first) {
        return commentService.getFirstFewCommentsByPostId(postDto.getId(),first);
    }
}
