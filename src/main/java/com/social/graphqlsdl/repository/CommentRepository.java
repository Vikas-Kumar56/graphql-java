package com.social.graphqlsdl.repository;

import com.social.graphqlsdl.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findAllByAuthor_Id(UUID authorId, Pageable pageable);
    List<Comment> findAllByPost_Id(UUID postId, Pageable pageable);
}
