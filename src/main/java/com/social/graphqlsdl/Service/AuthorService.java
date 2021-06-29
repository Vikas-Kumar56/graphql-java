package com.social.graphqlsdl.Service;

import com.social.graphqlsdl.dto.AuthorDto;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    List<AuthorDto> getAuthors();

    AuthorDto getAuthorById(UUID authorId);

    UUID createAuthor(AuthorDto authorDto);
}
