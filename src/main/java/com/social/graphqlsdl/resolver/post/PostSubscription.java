package com.social.graphqlsdl.resolver.post;

import com.social.graphqlsdl.dto.PostDto;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostSubscription implements GraphQLSubscriptionResolver {

    private final PostPublisher postPublisher;

    public PostSubscription(PostPublisher postPublisher) {
        this.postPublisher = postPublisher;
    }

    public Publisher<PostDto> recentPost() {
        return postPublisher.getRecentPost();
    }

    public Publisher<PostDto> recentPostByAuthor(UUID authorId) {
        return postPublisher.getRecentPostByAuthor(authorId);
    }
}
