package com.rudy.ladl.repository;

import com.rudy.ladl.core.site.Comment;
import com.rudy.ladl.core.site.Tag;
import com.rudy.ladl.core.site.embeddable.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(CommentId commentId);
}
