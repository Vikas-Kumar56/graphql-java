package com.social.graphqlsdl.mapper;

import com.social.graphqlsdl.dto.CommentDto;
import com.social.graphqlsdl.model.Author;
import com.social.graphqlsdl.model.Comment;
import com.social.graphqlsdl.model.Post;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentMapper {

    public CommentDto convertCommentToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .authorId(comment.getAuthor().getId())
                .postId(comment.getPost().getId())
                .build();
    }

    public Comment convertDtoToComment(CommentDto commentDto, Author author, Post post) {
        return Comment.builder()
                .text(commentDto.getText())
                .author(author)
                .post(post)
                .build();
    }
}
