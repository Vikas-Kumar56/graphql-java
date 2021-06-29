package com.social.graphqlsdl.Service.impl;

import com.social.graphqlsdl.Service.CommentService;
import com.social.graphqlsdl.dto.CommentDto;
import com.social.graphqlsdl.exception.ResourceNotFound;
import com.social.graphqlsdl.mapper.CommentMapper;
import com.social.graphqlsdl.model.Author;
import com.social.graphqlsdl.model.Comment;
import com.social.graphqlsdl.model.Post;
import com.social.graphqlsdl.repository.AuthorRepository;
import com.social.graphqlsdl.repository.CommentRepository;
import com.social.graphqlsdl.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, AuthorRepository authorRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDto> getFirstFewCommentsByAuthorId(UUID authorId, Integer count) {
        List<Comment> allByAuthor_id = commentRepository.findAllByAuthor_Id(authorId, PageRequest.of(0, count));

        return allByAuthor_id.stream()
                .map(commentMapper::convertCommentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getFirstFewCommentsByPostId(UUID postId, Integer count) {
        List<Comment> allByPost_id = commentRepository.
                findAllByPost_Id(postId, PageRequest.of(0, count));

        return allByPost_id.stream()
                .map(commentMapper::convertCommentToDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<CommentDto> getComments(Integer count, Integer offset) {
        Page<Comment> comments = commentRepository.findAll(PageRequest.of(offset, count));

        return comments.stream()
                .map(commentMapper::convertCommentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UUID createComment(CommentDto commentDto) {

        Optional<Author> authorOptional = authorRepository.findById(commentDto.getAuthorId());
        Optional<Post> postOptional = postRepository.findById(commentDto.getPostId());

        Author author = authorOptional.orElseThrow(() -> new ResourceNotFound("user is not exist!"));
        Post post = postOptional.orElseThrow(() -> new ResourceNotFound("Post is not exist!"));

        Comment comment = commentMapper.convertDtoToComment(commentDto, author, post);

        Comment createdComment = commentRepository.saveAndFlush(comment);

        return createdComment.getId();

    }
}
