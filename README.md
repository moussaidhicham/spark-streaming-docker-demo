# Spark Streaming Demo

Démo Spark Streaming (Java) avec producteur Python, prête pour Docker.

Description:
Ce dépôt contient une petite application Spark Streaming écrite en Java (`com.example.StreamingApp`) et un producteur Python (`producer/producer.py`) qui simule l'envoi de données pour le traitement en flux. Le projet montre le cycle complet : build Maven, packaging en jar, et exécution via Spark (Docker images incluses).

Objectifs:
- Illustrer la création et le packaging d'une application Spark Streaming en Java.
- Fournir un producteur Python simple pour générer des événements de test.
- Montrer une configuration basique Docker / docker-compose pour tests locaux.

Structure principale:
- `src/main/java/com/example/StreamingApp.java` — application Spark Streaming
- `producer/producer.py` — script producteur
- `pom.xml` — configuration Maven
- `Dockerfile`, `docker-compose.yml` — conteneurs pour déploiement local

Usage rapide:
1) Construire le jar (local) :
```
mvn clean package
```

2) Lancer avec Docker Compose (si présent) :
```
docker-compose up --build
```

3) Exemple : si vous préférez créer le dépôt GitHub et pousser depuis la racine :
```
git remote add origin https://github.com/moussaidhicham/spark-streaming-docker-demo.git
git branch -M main
git push -u origin main
```

Licence suggérée:
- `Apache-2.0` (recommandée pour projets utilisant Spark) — ou `MIT` si vous préférez.

Topics recommandés:
- `spark`, `spark-streaming`, `java`, `python`, `docker`, `maven`, `big-data`, `streaming`

---
Fait avec attention par votre assistant.
