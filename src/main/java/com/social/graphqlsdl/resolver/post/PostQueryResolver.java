package com.social.graphqlsdl.resolver.post;

import com.social.graphqlsdl.Service.PostService;
import com.social.graphqlsdl.dto.PostDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostQueryResolver implements GraphQLQueryResolver {

    private final PostService postService;

    public PostQueryResolver(PostService postService) {
        this.postService = postService;
    }

    public List<PostDto> recentPosts(int count, int offset){
        return postService.getRecentPosts(count,offset);
    }
}
