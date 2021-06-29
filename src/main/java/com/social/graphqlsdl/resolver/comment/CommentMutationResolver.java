package com.social.graphqlsdl.resolver.comment;

import com.social.graphqlsdl.Service.CommentService;
import com.social.graphqlsdl.dto.CommentDto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentMutationResolver implements GraphQLMutationResolver {

    private final CommentService commentService;
    private final CommentPublisher commentPublisher;

    public CommentMutationResolver(CommentService commentService, CommentPublisher commentPublisher) {
        this.commentService = commentService;
        this.commentPublisher = commentPublisher;
    }

    public UUID createComment(CommentDto commentDto) {
        UUID id = commentService.createComment(commentDto);
        commentDto.setId(id);
        commentPublisher.publish(commentDto);
        return id;
    }
}
