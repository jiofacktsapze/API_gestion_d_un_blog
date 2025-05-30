package com.jiofack.blog.repository;

import com.jiofack.blog.models.Article;
import com.jiofack.blog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticle(Article article);
}
