package com.social.graphqlsdl.resolver.post;

import com.social.graphqlsdl.Service.PostService;
import com.social.graphqlsdl.context.CustomGraphQLContext;
import com.social.graphqlsdl.dto.PostDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PostQueryResolver implements GraphQLQueryResolver {

    private final PostService postService;

    public PostQueryResolver(PostService postService) {
        this.postService = postService;
    }

    public List<PostDto> recentPosts(int count, int offset, DataFetchingEnvironment environment){
        CustomGraphQLContext context = environment.getContext();

        log.info("user id {}", context.getUserId());

        Set<String> fields = environment.getSelectionSet().getFields()
                .stream()
                .map(SelectedField::getName)
                .collect(Collectors.toSet());

        log.info("{} request started, request fields {}", environment.getExecutionId(), fields);

       // select description, id, title from post where ???

        List<PostDto> recentPosts = postService.getRecentPosts(count, offset);
        log.info("{} request completed", environment.getExecutionId());
        return recentPosts;
    }
}
