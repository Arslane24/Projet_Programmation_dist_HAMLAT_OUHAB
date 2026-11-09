# GymBooking

Application de réservation de séances de sport, organisée en microservices (Java / Spring Boot), avec un frontend React.

## Architecture

```
Navigateur (React)
   │
   ├──> member-service (8081)  — gestion des adhérents
   └──> gymbooking (8080)      — gestion des séances et réservations
              │
              └──> appelle member-service pour valider chaque réservation
```

- **member-service** : CRUD des adhérents (`Member`), vérifie si leur abonnement est actif.
- **gymbooking** : gère les séances (`Session`) et les réservations (`Reservation`). Avant de confirmer une réservation, il appelle `member-service` en REST pour vérifier que le membre existe et a un abonnement actif.
- **frontend** (dans `gymbooking/frontend`) : interface React qui consomme les deux APIs.

Chaque service suit la même structure : `entity` / `service` (+ exceptions custom) / `web`, données en mémoire (pas de base de données).

## Lancer le projet en local

### 1. member-service (port 8081)
```
cd member-service
./gradlew bootRun
```

### 2. gymbooking (port 8080)
```
cd gymbooking
./gradlew bootRun
```

### 3. frontend (port 5173)
```
cd gymbooking/frontend
npm install
npm run dev
```
Ouvre `http://localhost:5173`.

## Avec Docker

```
cd member-service
docker build -t member-service .
docker run -p 8081:8081 member-service
```
```
cd gymbooking
docker build -t gymbooking .
docker run -p 8080:8080 gymbooking
```

Image gymbooking publiée sur Docker Hub : [arslanebest/gymbooking](https://hub.docker.com/r/arslanebest/gymbooking)

## Avec Kubernetes (Minikube)

Manifestes dans `gymbooking/k8s/` : `deployment.yaml`, `service.yaml`, `ingress.yaml`.

```
minikube start
minikube addons enable ingress
kubectl apply -f gymbooking/k8s/deployment.yaml
kubectl apply -f gymbooking/k8s/service.yaml
kubectl apply -f gymbooking/k8s/ingress.yaml
```

## Endpoints

### member-service
| Méthode | URL | Description |
|---|---|---|
| GET | `/members` | Liste des membres |
| GET | `/members/{id}` | Détail d'un membre |
| POST | `/members` | Créer un membre |

### gymbooking
| Méthode | URL | Description |
|---|---|---|
| GET | `/sessions` | Liste des séances disponibles |
| GET | `/sessions/{sessionCode}` | Détail d'une séance |
| PUT | `/sessions/{sessionCode}?book=true` + body `{"memberId": 1}` | Réserver une place |
| PUT | `/sessions/{sessionCode}?book=false` | Annuler une réservation |

## Membres et séances pré-chargés

| Membre | Abonnement |
|---|---|
| Alice Martin | actif |
| Bob Durand | actif |
| Chloé Petit | inactif |

| Séance | Capacité |
|---|---|
| YOGA18 | 2 |
| CARDIO19 | 1 |
| MUSCU09 | 3 |

## Cas de test

- Réserver avec un membre actif → `200 OK`
- Réserver avec un membre à l'abonnement inactif → `409 Conflict`
- Réserver avec un membre inconnu → `404 Not Found`
- Réserver une séance pleine → `409 Conflict`
- Réserver/annuler une séance inexistante → `404 Not Found`
