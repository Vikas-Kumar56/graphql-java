package com.social.graphqlsdl.config;

import com.social.graphqlsdl.directive.UppercaseDirective;
import graphql.kickstart.tools.boot.SchemaDirective;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DirectiveConfig {

    @Bean
    public SchemaDirective upperCaseDirective() {
        return new SchemaDirective("uppercase", new UppercaseDirective());
    }
}
