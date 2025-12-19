# TP Java – Partie 3 : Accès aux données "live" (AviationStack)

## Contexte
L’objectif est d’interroger l’API AviationStack afin de récupérer des vols en temps réel
et de les transformer en objets Java exploitables dans l’application.

La librairie JSON (javax.json) est déjà ajoutée au projet.

API utilisée : https://aviationstack.com  
API Access Key : 2676502335811ad4047ee5cb656cdafe

---

## 1️⃣ Compréhension du format de réponse AviationStack

### Objectif
Comprendre la structure JSON retournée par l’API `/v1/flights`.

### Actions à réaliser
- Effectuer une requête HTTP GET vers :
http://api.aviationstack.com/v1/flights?access_key=2676502335811ad4047ee5cb656cdafe&arr_iata=CDG

yaml
Copier le code
- Identifier précisément :
- l’objet racine
- le tableau `data`
- les champs utiles pour un vol :
  - flight.iata
  - departure.airport
  - departure.iata
  - arrival.airport
  - arrival.iata
  - airline.name
  - flight_status

---

## 2️⃣ Création de la classe Flight

### Objectif
Créer une classe Java `Flight` conforme au diagramme UML du projet.

### Contraintes
- Classe **POJO**
- Attributs privés
- Constructeur complet
- Getters (pas forcément de setters)

### Champs minimum attendus
- `String flightIata`
- `String airlineName`
- `String departureAirport`
- `String departureIata`
- `String arrivalAirport`
- `String arrivalIata`
- `String flightStatus`

---

## 3️⃣ Création de la classe JsonFlightFiller

### Objectif
Parser une réponse JSON AviationStack et générer une liste d’objets `Flight`.

### Structure imposée
- Attribut privé :
```java
private ArrayList<Flight> list = new ArrayList<>();
Constructeur à implémenter
java
Copier le code
public JsonFlightFiller(String jsonString, World w)
Fonctionnement attendu du constructeur
Transformer la jsonString en InputStream

Créer un JsonReader

Lire un JsonObject

Extraire le tableau data

Boucler sur chaque élément du tableau

Pour chaque élément :

Extraire les champs nécessaires

Créer un objet Flight

Ajouter l’objet à list

Gérer proprement les exceptions

4️⃣ Test avec un fichier JSON local
Objectif
Valider le parsing JSON sans dépendre du réseau.

Actions
Lire le fichier data/test.txt

Stocker son contenu dans une String

Créer une instance de JsonFlightFiller

Vérifier que des objets Flight sont bien générés

Code de test (fourni dans le TP)
java
Copier le code
public static void main(String[] args) {
    try {
        World w = new World("./data/airport-codes_no_comma.csv");
        BufferedReader br = new BufferedReader(new FileReader("data/test.txt"));
        String test = br.readLine();
        JsonFlightFiller jsonFlightFiller = new JsonFlightFiller(test, w);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
5️⃣ Interrogation réelle de l’API AviationStack
Objectif
Remplacer le fichier plat par une requête HTTP réelle.

Actions à réaliser
Utiliser HttpClient, HttpRequest et HttpResponse

Construire une requête GET vers :

bash
Copier le code
http://api.aviationstack.com/v1/flights
Paramètres :

access_key

arr_iata=CDG

Récupérer la réponse sous forme de String

Passer la réponse au JsonFlightFiller

6️⃣ Intégration graphique (JavaFX)
Objectif
Afficher les vols récupérés sur la sphère de la Terre.

Actions
Pour chaque Flight :

Identifier l’aéroport de départ via World

Identifier l’aéroport d’arrivée

Créer une liaison visuelle (boule jaune)

Utiliser la méthode existante displayYellowBall

Les vols doivent apparaître dynamiquement à l’écran

7️⃣ Gestion du gel de l’interface (BONUS)
Problème
L’appel API bloque le thread JavaFX.

Solution attendue
Créer une classe implémentant Runnable

Déplacer l’appel HTTP + parsing JSON dans ce thread

Lancer le thread depuis l’interface graphique

✅ Critères de validation
Aucune exception non gérée

Parsing JSON fonctionnel

Objets Flight correctement construits

Données affichées sur la carte

Code clair, structuré, commenté# TP Java – Partie 3 : Accès aux données "live" (AviationStack)

## Contexte
L’objectif est d’interroger l’API AviationStack afin de récupérer des vols en temps réel
et de les transformer en objets Java exploitables dans l’application.

La librairie JSON (javax.json) est déjà ajoutée au projet.

API utilisée : https://aviationstack.com  
API Access Key : 2676502335811ad4047ee5cb656cdafe

---

## 1️⃣ Compréhension du format de réponse AviationStack

### Objectif
Comprendre la structure JSON retournée par l’API `/v1/flights`.

### Actions à réaliser
- Effectuer une requête HTTP GET vers :
http://api.aviationstack.com/v1/flights?access_key=2676502335811ad4047ee5cb656cdafe&arr_iata=CDG

yaml
Copier le code
- Identifier précisément :
- l’objet racine
- le tableau `data`
- les champs utiles pour un vol :
  - flight.iata
  - departure.airport
  - departure.iata
  - arrival.airport
  - arrival.iata
  - airline.name
  - flight_status

---

## 2️⃣ Création de la classe Flight

### Objectif
Créer une classe Java `Flight` conforme au diagramme UML du projet.

### Contraintes
- Classe **POJO**
- Attributs privés
- Constructeur complet
- Getters (pas forcément de setters)

### Champs minimum attendus
- `String flightIata`
- `String airlineName`
- `String departureAirport`
- `String departureIata`
- `String arrivalAirport`
- `String arrivalIata`
- `String flightStatus`

---

## 3️⃣ Création de la classe JsonFlightFiller

### Objectif
Parser une réponse JSON AviationStack et générer une liste d’objets `Flight`.

### Structure imposée
- Attribut privé :
```java
private ArrayList<Flight> list = new ArrayList<>();
Constructeur à implémenter
java
Copier le code
public JsonFlightFiller(String jsonString, World w)
Fonctionnement attendu du constructeur
Transformer la jsonString en InputStream

Créer un JsonReader

Lire un JsonObject

Extraire le tableau data

Boucler sur chaque élément du tableau

Pour chaque élément :

Extraire les champs nécessaires

Créer un objet Flight

Ajouter l’objet à list

Gérer proprement les exceptions

4️⃣ Test avec un fichier JSON local
Objectif
Valider le parsing JSON sans dépendre du réseau.

Actions
Lire le fichier data/test.txt

Stocker son contenu dans une String

Créer une instance de JsonFlightFiller

Vérifier que des objets Flight sont bien générés

Code de test (fourni dans le TP)
java
Copier le code
public static void main(String[] args) {
    try {
        World w = new World("./data/airport-codes_no_comma.csv");
        BufferedReader br = new BufferedReader(new FileReader("data/test.txt"));
        String test = br.readLine();
        JsonFlightFiller jsonFlightFiller = new JsonFlightFiller(test, w);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
5️⃣ Interrogation réelle de l’API AviationStack
Objectif
Remplacer le fichier plat par une requête HTTP réelle.

Actions à réaliser
Utiliser HttpClient, HttpRequest et HttpResponse

Construire une requête GET vers :

bash
Copier le code
http://api.aviationstack.com/v1/flights
Paramètres :

access_key

arr_iata=CDG

Récupérer la réponse sous forme de String

Passer la réponse au JsonFlightFiller

6️⃣ Intégration graphique (JavaFX)
Objectif
Afficher les vols récupérés sur la sphère de la Terre.

Actions
Pour chaque Flight :

Identifier l’aéroport de départ via World

Identifier l’aéroport d’arrivée

Créer une liaison visuelle (boule jaune)

Utiliser la méthode existante displayYellowBall

Les vols doivent apparaître dynamiquement à l’écran

7️⃣ Gestion du gel de l’interface (BONUS)
Problème
L’appel API bloque le thread JavaFX.

Solution attendue
Créer une classe implémentant Runnable

Déplacer l’appel HTTP + parsing JSON dans ce thread

Lancer le thread depuis l’interface graphique

✅ Critères de validation
Aucune exception non gérée

Parsing JSON fonctionnel

Objets Flight correctement construits

Données affichées sur la carte

Code clair, structuré, commenté comme un etudiant sans trop en faire vraiment quelque chose de minimal