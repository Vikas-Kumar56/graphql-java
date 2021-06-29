package com.social.graphqlsdl.config;

import graphql.Scalars;
import graphql.scalars.ExtendedScalars;
import graphql.scalars.regex.RegexScalar;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Configuration
public class ScalarTypeConfig {

    @Bean
    public GraphQLScalarType date() {
        return ExtendedScalars.Date;
    }

    @Bean
    public GraphQLScalarType dateTime() {
        return ExtendedScalars.DateTime;
    }

    @Bean
    public GraphQLScalarType time() {
        return ExtendedScalars.Time;
    }

    @Bean
    public RegexScalar phoneNumber() {
        return ExtendedScalars.newRegexScalar("PhoneNumber")
                .addPattern(Pattern.compile("^([+]?\\d{1,2}[-\\s]?|)\\d{3}[-\\s]?\\d{3}[-\\s]?\\d{4}$"))
                .build();
    }

    @Bean
    public GraphQLScalarType socialMediaLink() {
        return ExtendedScalars.newAliasedScalar("SocialMediaLink")
                .aliasedScalar(Scalars.GraphQLString)
                .build();
    }
}
