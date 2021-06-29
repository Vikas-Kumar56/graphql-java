package com.social.graphqlsdl.resolver.comment;

import com.social.graphqlsdl.dto.CommentDto;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentSubscription implements GraphQLSubscriptionResolver {

    private final CommentPublisher commentPublisher;

    public CommentSubscription(CommentPublisher commentPublisher) {
        this.commentPublisher = commentPublisher;
    }

    public Publisher<CommentDto> recentCommentByPost(UUID postId) {
        return commentPublisher.getRecentCommentFor(postId);
    }
}
