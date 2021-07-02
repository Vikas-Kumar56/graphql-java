package com.social.graphqlsdl.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class MessageMutationResolver implements GraphQLMutationResolver {

    public UUID createMessage(UUID id, String title, Integer[] luckyNumbers, Integer value) {
        return UUID.randomUUID();
    }
}
