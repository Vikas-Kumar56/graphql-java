package com.social.graphqlsdl.Service.impl;

import com.social.graphqlsdl.Service.PostService;
import com.social.graphqlsdl.dto.AuthorDto;
import com.social.graphqlsdl.dto.CommentDto;
import com.social.graphqlsdl.dto.PostDto;
import com.social.graphqlsdl.exception.ResourceNotFound;
import com.social.graphqlsdl.model.Author;
import com.social.graphqlsdl.model.Post;
import com.social.graphqlsdl.repository.AuthorRepository;
import com.social.graphqlsdl.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final AuthorRepository authorRepository;

    public PostServiceImpl(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<PostDto> getAllPostByAuthorId(UUID authorId) {
        List<Post> allByAuthor_id = postRepository.findAllByAuthor_Id(authorId);
        return allByAuthor_id.stream()
                .map(post -> {
                    return PostDto.builder()
                            .id(post.getId())
                            .authorId(authorId)
                            .description(post.getDescription())
                            .title(post.getTitle())
                            .category(post.getCategory())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getRecentPosts(int count, int offset) {
        PageRequest of = PageRequest.of(offset, count);
        Page<Post> all = postRepository.findAll(of);

        return all.stream()
                .map(post -> {
                    return PostDto.builder()
                            .id(post.getId())
                            .authorId(post.getAuthor().getId())
                            .description(post.getDescription())
                            .title(post.getTitle())
                            .category(post.getCategory())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public UUID cratePost(PostDto postDto) {

        Optional<Author> author = authorRepository.findById(postDto.getAuthorId());

        if (!author.isPresent()) {
            throw new RuntimeException("Author is not exist!");
        }

        Post build = Post.builder()
                .category(postDto.getCategory())
                .description(postDto.getDescription())
                .title(postDto.getTitle())
                .author(author.get())
                .build();

        Post post = postRepository.saveAndFlush(build);
        return post.getId();
    }

    @Override
    public Integer getPostCountByAuthorId(UUID id) {
        return postRepository.findAllByAuthor_Id(id).size();
    }

    @Override
    public PostDto getPostById(UUID postId) {
        Optional<Post> postOptional = postRepository.findById(postId);

        Post post = postOptional.orElseThrow(() -> new ResourceNotFound("Post is not exist: " + postId));

        return PostDto.builder()
                .id(post.getId())
                .category(post.getCategory())
                .title(post.getTitle())
                .description(post.getDescription())
                .authorId(post.getAuthor().getId())
                .build();
    }

    @Override
    public AuthorDto getAuthorById(UUID authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);

        Author author = authorOptional.orElseThrow(() -> new ResourceNotFound("User is not exist: " + authorId));

        return AuthorDto.builder()
                .name(author.getName())
                .email(author.getEmail())
                .id(authorId)
                .build();
    }

}
