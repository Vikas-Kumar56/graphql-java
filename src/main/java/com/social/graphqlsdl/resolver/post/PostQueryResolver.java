package com.social.graphqlsdl.resolver.post;

import com.social.graphqlsdl.Service.PostService;
import com.social.graphqlsdl.dto.PostDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PostQueryResolver implements GraphQLQueryResolver {

    private final PostService postService;

    public PostQueryResolver(PostService postService) {
        this.postService = postService;
    }

    public List<PostDto> recentPosts(int count, int offset, DataFetchingEnvironment environment){
        log.info("{} request started", environment.getExecutionId());
        List<PostDto> recentPosts = postService.getRecentPosts(count, offset);
        log.info("{} request completed", environment.getExecutionId());
        return recentPosts;
    }
}
