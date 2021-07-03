package com.social.graphqlsdl.filter;

import graphql.kickstart.servlet.core.GraphQLServletListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
public class GraphQLHttpRequestFilter implements GraphQLServletListener {

    private final Clock clock = Clock.systemDefaultZone();

    @Override
    public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {

        Instant startTime = Instant.now();

        return new RequestCallback() {
            @Override
            public void onSuccess(HttpServletRequest request, HttpServletResponse response) {

            }

            @Override
            public void onError(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {

            }

            @Override
            public void onFinally(HttpServletRequest request, HttpServletResponse response) {
               log.info("request completed in {}", Duration.between(startTime, Instant.now()));
            }
        };
    }
}
