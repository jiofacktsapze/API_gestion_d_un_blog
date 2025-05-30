package com.jiofack.blog.exceptions;

public class ArticleNotFoundException extends RuntimeException{
    public ArticleNotFoundException(Long id) {
        super("Article with ID " + id + " not found.");
    }
}
