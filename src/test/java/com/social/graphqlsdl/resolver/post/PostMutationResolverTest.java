package com.social.graphqlsdl.resolver.post;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.social.graphqlsdl.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
class PostMutationResolverTest {

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void shouldAbleToCreatePost() throws IOException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource("request/create-post-mutation.graphqls");

        String uuid = graphQLResponse.get("$.data.createPost");

        assertThat(uuid, is(notNullValue()));
    }
}
