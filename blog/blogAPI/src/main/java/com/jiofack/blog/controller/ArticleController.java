package com.jiofack.blog.controller;

import com.jiofack.blog.dto.ArticleDto;
import com.jiofack.blog.dto.CommentDto;
import com.jiofack.blog.models.Article;
import com.jiofack.blog.models.Comment;
import com.jiofack.blog.services.ArticleService;
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
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // Créer un article
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Créer un article", description = "Cette méthode permet d'enregistrer un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public ResponseEntity<Article> createArticle(@Valid @RequestBody ArticleDto articleDto) {
        Article created = articleService.createArticle(articleDto.getTitle(), articleDto.getContent(), articleDto.getAuthor());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Lire tous les articles
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lister les articles", description = "Renvoi tous les articles existant dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des articles")
    })
    public ResponseEntity<List<Article>> getAllArticles(){
        return ResponseEntity.ok(articleService.readAllArticles());
    }

    // Lire un article par ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lire un article par ID", description = "Renvoie un article spécifique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article trouvé"),
            @ApiResponse(responseCode = "404", description = "Article introuvable")
    })
    public ResponseEntity<Article> getArticleById(@PathVariable Long id){
        return ResponseEntity.ok(articleService.readArticle(id));
    }

    // Mettre à jour un article
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour un article", description = "Modifier le titre et le contenu de l'article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article mis à jour"),
            @ApiResponse(responseCode = "404", description = "Article introuvable")
    })
    public ResponseEntity<Article> updateArticle(
            @PathVariable Long id,
            @RequestBody ArticleDto articleDto
    ) {
        Article updated = articleService.updateArticle(id, articleDto.getTitle(), articleDto.getContent());
        return ResponseEntity.ok(updated);
    }

    // Supprimer un article
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Supprimer un article", description = "Cette méthode permet de supprimer un article par son ID")
    @ApiResponse(responseCode = "204", description = "Article supprimé")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
