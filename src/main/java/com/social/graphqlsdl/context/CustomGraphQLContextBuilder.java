package com.social.graphqlsdl.context;

import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

@Component
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {
    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        String userId = httpServletRequest.getHeader("user_id");

        DefaultGraphQLServletContext servletContext = DefaultGraphQLServletContext.createServletContext()
                .with(httpServletRequest)
                .with(httpServletResponse)
                .build();

        return new CustomGraphQLContext(userId,servletContext);
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        throw new IllegalStateException("UnSupported");
    }

    @Override
    public GraphQLContext build() {
        throw new IllegalStateException("UnSupported");
    }
}
