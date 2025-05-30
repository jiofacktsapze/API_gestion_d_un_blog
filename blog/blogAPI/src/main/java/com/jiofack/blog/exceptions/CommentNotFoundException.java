package com.jiofack.blog.exceptions;

public class CommentNotFoundException extends RuntimeException{

    public CommentNotFoundException(Long id){
        super("Comment with ID " + id + " not found.");
    }
}
