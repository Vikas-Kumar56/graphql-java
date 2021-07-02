package com.social.graphqlsdl.directive;

import graphql.kickstart.tools.boot.SchemaDirective;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectiveConfig {
    @Bean
    public SchemaDirective myCustomDirective() {
        return new SchemaDirective("uppercase", new UppercaseDirective());
    }
}
