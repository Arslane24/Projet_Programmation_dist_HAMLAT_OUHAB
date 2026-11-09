# GymBooking

Petite API REST de réservation de séances de sport (Java / Spring Boot), construite sur le même modèle que le projet carrental : couches `entity` / `service` / `web`, données en mémoire, pas de base de données.

## Lancer le projet

### Avec Gradle
```
./gradlew bootRun
```
L'API démarre sur `http://localhost:8080`.

### Avec Docker
```
docker build -t gymbooking .
docker run -p 8080:8080 gymbooking
```

Image publiée sur Docker Hub : [arslanebest/gymbooking](https://hub.docker.com/r/arslanebest/gymbooking)
```
docker pull arslanebest/gymbooking:1.0
docker run -p 8080:8080 arslanebest/gymbooking:1.0
```

## Endpoints

| Méthode | URL | Description |
|---|---|---|
| GET | `/sessions` | Liste des séances disponibles (non pleines) |
| GET | `/sessions/{sessionCode}` | Détail d'une séance |
| PUT | `/sessions/{sessionCode}?book=true` + body `{"memberName":"..."}` | Réserver une place |
| PUT | `/sessions/{sessionCode}?book=false` | Annuler une réservation |

## Séances pré-chargées

| Code | Nom | Capacité |
|---|---|---|
| YOGA18 | Yoga | 2 |
| CARDIO19 | Cardio | 1 |
| MUSCU09 | Musculation | 3 |

## Exemples de tests

```
GET  http://localhost:8080/sessions
GET  http://localhost:8080/sessions/YOGA18
PUT  http://localhost:8080/sessions/YOGA18?book=true   body: { "memberName": "Alice" }
PUT  http://localhost:8080/sessions/YOGA18?book=false
```

- Réserver une séance pleine → `409 Conflict`
- Réserver/annuler une séance inexistante → `404 Not Found`
- Réserver sans `memberName` dans le body → `400 Bad Request`
