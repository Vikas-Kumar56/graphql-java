package com.social.graphqlsdl.resolver;

import com.social.graphqlsdl.dto.CustomerDto;
import com.social.graphqlsdl.dto.MessageDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class HelloWorldQueryResolver implements GraphQLQueryResolver {
    public String getHelloWorld() {
        return "Hello World! graphql is awesome";
    }

    public String getGreetingMessage(String firstName, String secondName) {
        return String.format("Hello %s %s", firstName, secondName);
    }

    public MessageDto getMessage() {
        return MessageDto.builder()
                .id(UUID.randomUUID())
                .text("graphql is awesome")
                .build();
    }

   public List<Integer> getRollDice() {
        return Arrays.asList(1,2,3,4);
   }

   public CustomerDto customer(String phoneNumber, String email) {
        return CustomerDto.builder()
                .birthDay(LocalDate.now())
                .bornAt(OffsetDateTime.now())
                .workStartTime(OffsetTime.now())
                .profileLink("some link")
                .build();
   }

}
