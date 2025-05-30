package com.jiofack.blog.services;

import com.jiofack.blog.exceptions.ArticleNotFoundException;
import com.jiofack.blog.exceptions.CommentNotFoundException;
import com.jiofack.blog.models.Article;
import com.jiofack.blog.models.Comment;
import com.jiofack.blog.repository.ArticleRepository;
import com.jiofack.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }
    // ✅ Créer un commentaire
    public Comment createComment(Long articleId, String content, String author) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException(articleId));

        Comment comment = Comment.builder()
                .content(content)
                .author(author)
                .commentDate(LocalDateTime.now())
                .article(article)
                .build();

        return commentRepository.save(comment);
    }

    // ✅ Lire un commentaire par ID
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

    // ✅ Lire tous les commentaires d’un article
    public List<Comment> getCommentsByArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException(articleId));
        return commentRepository.findByArticle(article);
    }

    // ✅ Supprimer un commentaire
    public void deleteComment(Long commentId) {
        Comment comment = getCommentById(commentId);
        commentRepository.delete(comment);
    }
}
