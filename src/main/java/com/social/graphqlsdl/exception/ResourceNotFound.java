package com.social.graphqlsdl.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public class ResourceNotFound extends RuntimeException implements GraphQLError {
    private final String message;
    public ResourceNotFound(String msg) {
        super(msg);
        message = msg;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }

    @Override
    public List<Object> getPath() {
        return null;
    }

    @Override
    public Map<String, Object> toSpecification() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return null;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return null;
    }
}
