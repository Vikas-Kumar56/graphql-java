package com.social.graphqlsdl.resolver.author;

import com.social.graphqlsdl.Service.AuthorService;
import com.social.graphqlsdl.dto.AuthorDto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthorMutationResolver implements GraphQLMutationResolver {

    private final AuthorService authorService;

    public AuthorMutationResolver(AuthorService authorService) {
        this.authorService = authorService;
    }

    public UUID createAuthor(AuthorDto authorDto) {
        return authorService.createAuthor(authorDto);
    }
}
