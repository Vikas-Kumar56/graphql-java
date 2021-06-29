package com.social.graphqlsdl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private LocalDate birthDay;
    private OffsetTime workStartTime;
    private OffsetDateTime bornAt;
    private String profileLink;
}
