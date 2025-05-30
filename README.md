# API_gestion_d_un_blog
Cette API permet aux utilisateurs de créer des articles et d'ajouter des commentaires: création, lecture, mise à jour, suppression et ajout de commentaire sur un article.

## 🔧 Prérequis

- **Java 17+**
- **Maven 3.6+**
- **IDE** (IntelliJ, Eclipse, VS Code, etc.)
- **PostgreSQL** ou autre SGBD si utilisé

## 🚀 Lancement du projet

1. **Cloner le dépôt :**
   git clone https://github.com/ton-utilisateur/blogAPI.git
   cd blogAPI

2. **Configurer la base de données dans `application.properties` :**
   Exemple :
   spring.datasource.url=jdbc:postgresql://localhost:5443/blog-app
   spring.datasource.username=postgres
   spring.datasource.password=motdepasse
   spring.jpa.hibernate.ddl-auto=update

3. **Lancer l’application :**

   ./mvnw spring-boot:run

   L’application sera disponible à l’adresse :  
   ➤ `http://localhost:8080`
   
   ## 📚 Documentation Swagger

Une fois l’application démarrée, la documentation Swagger est disponible à :

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Ou (si redirigé automatiquement) :

👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

📮 Points de terminaison API
Articles
Méthode	  URL            	                Description
POST	    /articles        	              Créer un article
GET	      /articles    	                  Récupérer tous les articles
GET	      /articles/{id}	                Récupérer un article spécifique
PUT	      /articles/{id}	                Mettre à jour un article
DELETE	  /articles/{id}	                Supprimer un article

Commentaires
Méthode	  URL	                            Description
POST	    /article/{articleId}/comments	  Ajouter un commentaire à un article
GET	      /article/{articleId}/comments	  Récupérer les commentaires d’un article
GET       /comments/{id}                  Rechercher un commentaire spécifique 
DELETE    /comments/{id}                  Supprimer un commentaire
