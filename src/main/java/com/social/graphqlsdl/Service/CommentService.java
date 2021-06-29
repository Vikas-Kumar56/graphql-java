package com.social.graphqlsdl.Service;

import com.social.graphqlsdl.dto.CommentDto;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    List<CommentDto> getFirstFewCommentsByAuthorId(UUID authorId, Integer count);
    List<CommentDto> getFirstFewCommentsByPostId(UUID postId, Integer count);

    List<CommentDto> getComments(Integer count, Integer offset);

    UUID createComment(CommentDto commentDto);
}
