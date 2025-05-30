package com.jiofack.blog.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    @NotBlank(message = "Le contenu du commentaire est obligatoire.")
    @Size(min = 3, message = "Le commentaire doit contenir au moins 3 caractères.")
    private String content;

    @NotBlank(message = "L'auteur est obligatoire.")
    @Size(min = 2, message = "Le nom de l'auteur doit contenir au moins 2 caractères.")
    private String author;

}
