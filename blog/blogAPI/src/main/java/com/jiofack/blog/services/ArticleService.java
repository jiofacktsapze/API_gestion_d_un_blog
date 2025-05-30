package com.jiofack.blog.services;

import com.jiofack.blog.exceptions.ArticleNotFoundException;
import com.jiofack.blog.models.Article;
import com.jiofack.blog.models.Comment;
import com.jiofack.blog.repository.ArticleRepository;
import com.jiofack.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;


@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }


    public Article createArticle(String title, String content, String author){
        Article article = Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .publicationDate(LocalDateTime.now())
                .build();
        return articleRepository.save(article);
    }

    public List<Article> readAllArticles(){
        return articleRepository.findAll();
    }

    public Article readArticle(Long id){
        return getArticleById(id);
    }

    public Article updateArticle(Long id, String newTitle, String newContent){
        Article article = getArticleById(id);
        article.setTitle(newTitle);
        article.setContent(newContent);
        return articleRepository.save(article);
    }

    public void deleteArticle(Long id){
        Article article = getArticleById(id);
        articleRepository.delete(article);
    }

    public Comment addCommentToArticle(Long articleId, String content, String author) {
        Article article = getArticleById(articleId);
        Comment comment = Comment.builder()
                .article(article)
                .content(content)
                .author(author)
                .commentDate(LocalDateTime.now())
                .build();
        return commentRepository.save(comment);
    }

    // Méthode utilitaire interne
    private Article getArticleById(Long id){
        return articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));
    }
}
