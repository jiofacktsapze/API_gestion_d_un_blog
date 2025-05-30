package com.jiofack.blog.controller;

import com.jiofack.blog.dto.CommentDto;
import com.jiofack.blog.models.Comment;
import com.jiofack.blog.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CommentController {
    public final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    // ✅ Ajouter un commentaire à un article
    @PostMapping(value = "/articles/{articleId}/comments", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajouter un commentaire", description = "Créer un commentaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commentaire crée avec succès"),
            @ApiResponse(responseCode = "400", description = "L'objet commentaire n'est pas valide")
    })
    public ResponseEntity<Comment> createComment(@PathVariable Long articleId, @Valid @RequestBody CommentDto commentDto) {
        Comment created = commentService.createComment(articleId, commentDto.getContent(), commentDto.getAuthor());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ✅ Obtenir tous les commentaires d’un article
    @GetMapping(value = "/articles/{articleId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Afficher les commentaires d'un article par son ID", description = "Cette méthode permet d'obtenir tous les commentaires d'un article donné par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le ou les commentaires de l'article ont été trouvé dans la BDD"),
            @ApiResponse(responseCode = "404", description = "Aucun commentaire n'existe dans la BDD avec l'ID de l'article fourni")
    })
    public ResponseEntity<List<Comment>> getCommentsByArticle(@PathVariable Long articleId) {
        return ResponseEntity.ok(commentService.getCommentsByArticle(articleId));
    }

    // ✅ Obtenir un commentaire par ID
    @GetMapping(value = "/comments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un commentaire par ID", description = "Cette méthode permet de chercher un commentaire par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le commentaire a été trouvé dans la BDD"),
            @ApiResponse(responseCode = "404", description = "Aucun commentaire n'existe dans la BDD avec l'ID fourni")
    })
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    // ✅ Supprimer un commentaire
    @DeleteMapping(value = "/comments/{id}")
    @Operation(summary = "Supprimer un commentaire par ID", description = "Cette méthode permet de supprimer un commentaire par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le commentaire a été supprimé")
    })
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
