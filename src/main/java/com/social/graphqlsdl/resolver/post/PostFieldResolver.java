package com.social.graphqlsdl.resolver.post;

import com.social.graphqlsdl.Service.AuthorService;
import com.social.graphqlsdl.Service.CommentService;
import com.social.graphqlsdl.dto.AuthorDto;
import com.social.graphqlsdl.dto.CommentDto;
import com.social.graphqlsdl.dto.PostDto;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class PostFieldResolver implements GraphQLResolver<PostDto> {

    private final AuthorService authorService;
    private final CommentService commentService;

    private final ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public PostFieldResolver(AuthorService authorService, CommentService commentService) {
        this.authorService = authorService;
        this.commentService = commentService;
    }

    public CompletableFuture<AuthorDto> getAuthor(PostDto postDto) {
        log.info("author main thread  id : {}", Thread.currentThread().getName());
        return CompletableFuture.supplyAsync(() -> {
            log.info("author thread id : {}", Thread.currentThread().getName());
            log.info("author request start id: {}", postDto.getId());
            AuthorDto authorById = authorService.getAuthorById(postDto.getAuthorId());
            log.info("author request completed id: {}",postDto.getId());
            return authorById;
        }, executorService);
    }

    public CompletableFuture<List<CommentDto>> getComments(PostDto postDto, Integer first) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("comment request start id: {}", postDto.getId());
            List<CommentDto> firstFewCommentsByPostId = commentService.getFirstFewCommentsByPostId(postDto.getId(), first);
            log.info("comment request completed id: {}", postDto.getId());
            return firstFewCommentsByPostId;
        }, executorService);
    }
}
