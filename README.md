# Application Allo Monde

API REST développée avec Spring Boot pour exposer un point d'entrée simple de démonstration.

## Description

Cette application sert d'exemple de backend API REST sécurisé avec :
- Java 21
- Spring Boot 4.x
- Spring Security

L'objectif principal est de fournir un endpoint simple permettant de retourner la chaîne "allo" en réponse.

## Prérequis

Avant de lancer le projet, vérifie que tu as :
- JDK 21
- Un token JWT valide si l'API est protégée avec OAuth2
- Le serveur d'autorisation OAuth2 sert à obtenir les jetons signés pour les appels API

## Démarrage

Depuis la racine du projet :

```bash
./gradlew clean bootRun
```

L'application démarre par défaut sur :

```text
http://localhost:8080
```

## Endpoints

### POST /api/allo

Retourne une réponse "allo" si l'utilisateur possède les droits requis.

Exemple :

```bash
curl -X POST http://localhost:8080/api/allo \
  -H "Authorization: Bearer <token_jwt>"
```

La valeur de `token_jwt` est récupérée depuis le serveur d'autorisation OAuth2 du projet avant d'appeler cet endpoint.

Réponse attendue :

```text
allo
```

## Collection Postman

Une collection Postman est disponible dans `postman/Allo Monde.postman_collection.json` et elle nécessite l’environnement `postman/Environnement Exercise.postman_environment.json` pour fonctionner correctement.

Elle contient les requêtes suivantes :
- `1 Obtenir token avec corps de requête` : demande un jeton OAuth2 en envoyant `client_id`, `client_secret`, `grant_type` et `scope` dans le corps de la requête. Les réponses obtenues contiennent le champ `access_token`.
- `1 Obtenir token avec auth de base` : demande un jeton OAuth2 en utilisant l'authentification HTTP Basic avec `client_id` et `client_secret`. Les réponses obtenues contiennent aussi le champ `access_token`.
- `1 Obtenir token avec jwt signé` : génère un JWT signé localement avec `client_id_jwt` et `client_secret_jwt`, puis l'envoie comme `client_assertion` pour obtenir un accès OAuth2 via le flux `client_credentials`.
- `2 Dire Allo` : appelle le point d'entrée `POST http://localhost:8080/api/allo` avec le jeton JWT obtenu précédemment dans le header `Authorization`.