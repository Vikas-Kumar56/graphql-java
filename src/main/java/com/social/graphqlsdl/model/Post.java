package com.social.graphqlsdl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "post")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;
    private String title;
    private String category;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Set<Comment> comments;
}
