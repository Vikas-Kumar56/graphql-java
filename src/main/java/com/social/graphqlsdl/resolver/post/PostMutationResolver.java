package com.social.graphqlsdl.resolver.post;

import com.social.graphqlsdl.Service.PostService;
import com.social.graphqlsdl.dto.PostDto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostMutationResolver implements GraphQLMutationResolver {

    private final PostService postService;
    private final PostPublisher postPublisher;

    public PostMutationResolver(PostService postService, PostPublisher postPublisher) {
        this.postService = postService;
        this.postPublisher = postPublisher;
    }

    public UUID createPost(PostDto postDto) {
        UUID uuid = postService.cratePost(postDto);
        postDto.setId(uuid);
        postPublisher.publish(postDto);
        return uuid;
    }
}
