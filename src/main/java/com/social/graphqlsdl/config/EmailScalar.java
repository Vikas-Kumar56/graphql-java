package com.social.graphqlsdl.config;

import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Configuration
public class EmailScalar {

    @Bean
    public GraphQLScalarType email(){
        return new GraphQLScalarType("Email", "custom email scalar type", new Coercing() {
            @Override
            public Object serialize(Object dataFetcherResult) throws CoercingSerializeException {
                if(dataFetcherResult instanceof StringValue) {
                    return dataFetcherResult.toString();
                }

                throw new CoercingSerializeException("email is not valid");
            }

            @Override
            public Object parseValue(Object input) throws CoercingParseValueException {
                if(input instanceof String) {
                    String possibleEmail = input.toString();
                    if(isValidEmail(possibleEmail)){
                        return possibleEmail;
                    }
                }

                throw new CoercingParseValueException("email is not valid");
            }

            @Override
            public Object parseLiteral(Object input) throws CoercingParseLiteralException {
                if(input instanceof StringValue) {

                    String possibleEmailValue = input.toString();
                    if(isValidEmail(possibleEmailValue)) {
                        return possibleEmailValue;
                    }
                }

                throw new CoercingParseLiteralException("email is not valid");
            }

            private boolean isValidEmail(String possibleEmailValue) {
                return Pattern.matches(".+\\@.+\\..+",possibleEmailValue);
            }
        });
    }
}
