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

Une collection Postman est disponible dans `postman/Allo Monde.postman_collection.json`.
Elle contient trois requêtes : deux pour obtenir le jeton OAuth2 et une dernière pour interroger le point d'entrée exposé par cette API.

## Sécurité

Le projet utilise Spring Security avec un mode API stateless.
La protection CSRF est désactivée car l'application est conçue comme une API REST sans session HTTP classique.

Les accès sont contrôlés via les autorités OAuth2 / JWT.
