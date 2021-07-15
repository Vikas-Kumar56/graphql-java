package com.social.graphqlsdl.Service.impl;

import com.social.graphqlsdl.Service.AuthorService;
import com.social.graphqlsdl.dto.AuthorDto;
import com.social.graphqlsdl.exception.ResourceNotFound;
import com.social.graphqlsdl.model.Author;
import com.social.graphqlsdl.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDto> getAuthors() {
        List<Author> all = authorRepository.findAll();
        return all.stream()
                .map(author -> {
                    return AuthorDto.builder()
                            .id(author.getId())
                            .email(author.getEmail())
                            .name(author.getName())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public AuthorDto getAuthorById(UUID authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        Author author = authorOptional.orElseThrow(() -> new ResourceNotFound("User is not exist id:" + authorId));
        return AuthorDto.builder()
                .id(author.getId())
                .email(author.getEmail())
                .name(author.getName())
                .build();
    }

    @Override
    public UUID createAuthor(AuthorDto authorDto) {
        Author author = Author.builder()
                .email(authorDto.getEmail())
                .name(authorDto.getName())
                .build();

        Author createdAuthor = authorRepository.saveAndFlush(author);
        return createdAuthor.getId();
    }

    @Override
    public Map<UUID, AuthorDto> getAllAuthorByIds(Set<UUID> authorIds) {
        log.info("author ids : {}", authorIds);
        List<Author> allById = authorRepository.findAllById(authorIds);
        return allById.stream()
                .map(author -> AuthorDto.builder()
                        .id(author.getId())
                        .email(author.getEmail())
                        .name(author.getName())
                        .build())
                .collect(Collectors.toMap(AuthorDto::getId, author -> author));
    }

}
