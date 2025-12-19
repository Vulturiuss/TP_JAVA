TP1:
Mohammad Belhouchat
Saadallah Ouaziz
On a commencé avec la classe Aeroport. À partir du diagramme UML, on a ajouté l’ensemble des attributs privés indiqués par les carrés (on le déduit aussi car ils sont locaux, etc.).
On a ensuite surchargé la méthode toString() : on la surcharge car elle existe déjà pour toutes les classes, mais on veut un fonctionnement différent pour pouvoir tester la classe.
On la teste, et ça fonctionne.

Pour la classe World, on avait déjà la base du constructeur dans l’énoncé. On a vérifié, pour chaque ligne du fichier CSV représentant la liste des aéroports, si l’aéroport est un grand aéroport.
À partir de cela, on a complété le code de sorte à ajouter l’ensemble des grands aéroports dans une liste.

Ensuite, on a ajouté dans la classe Aeroport, à l’aide de l’énoncé du TP, une méthode calculDistance publique (car on va l’utiliser dans la classe World) dans une nouvelle méthode findNearestAirport, elle aussi publique, qui — comme son nom l’indique — va nous permettre de trouver l’aéroport le plus proche de celui qu’on a choisi.

TP2:
Dans cette deuxième partie, on a ajouté l’IHM qui permet d’afficher la Terre en 3D et d’interagir avec elle.

On a d’abord créé la classe Earth, où on affiche une sphère de rayon 300 avec la texture de la Terre. On a aussi ajouté une rotation automatique grâce à un AnimationTimer, ce qui fait tourner la planète en continu.

Ensuite, dans la classe Interface, on a installé la caméra 3D et un zoom simple en utilisant le clic gauche et le déplacement de la souris.

On a aussi géré le clic droit sur la Terre : grâce à PickResult, on récupère la position sur la texture, puis on convertit ces valeurs en latitude et longitude avec les formules données dans le TP. À partir de ces coordonnées, on utilise notre classe World (faite au TP1) pour trouver l’aéroport le plus proche, et on l’affiche dans la console.

Enfin, on a ajouté l’affichage d’une petite sphère rouge sur la Terre à l’endroit où se trouve l’aéroport cliqué, ce qui montre visuellement la position sur le globe.

Cette partie nous a permis de relier l’IHM 3D avec les données des aéroports et d’interagir directement avec la Terre.

TP3 :

Dans cette troisième partie, on a travaillé sur l’accès à des données “live” afin de récupérer des informations de vols en temps réel à partir de l’API AviationStack.

On a d’abord étudié la documentation de l’API et testé une requête directement depuis un navigateur. Cela nous a permis de comprendre la structure de la réponse JSON, en particulier le tableau data qui contient la liste des vols, ainsi que les différents champs décrivant un vol (compagnie, aéroport de départ, aéroport d’arrivée, statut du vol, etc.).

Ensuite, on a créé la classe Flight en suivant le diagramme UML fourni. Cette classe contient les attributs nécessaires pour représenter un vol (identifiant du vol, compagnie aérienne, aéroports de départ et d’arrivée, statut), ainsi qu’un constructeur et des getters pour accéder aux données.

On a ensuite implémenté la classe JsonFlightFiller, dont le rôle est de transformer une réponse JSON en une liste d’objets Flight. Cette classe contient une ArrayList<Flight> privée. Dans son constructeur, on utilise la bibliothèque JSON fournie pour lire la chaîne JSON, extraire le tableau data, parcourir chaque enregistrement et créer autant d’objets Flight que de vols présents dans la réponse.

Afin de tester cette partie sans dépendre immédiatement du réseau, on a d’abord utilisé un fichier texte contenant une réponse JSON de l’API. Le contenu de ce fichier est lu et passé au constructeur de JsonFlightFiller, ce qui nous a permis de valider que le parsing JSON et la création des objets Flight fonctionnent correctement.

Une fois cette étape validée, on a remplacé le fichier local par une requête HTTP réelle vers l’API AviationStack, en utilisant les classes HttpClient, HttpRequest et HttpResponse. La réponse de l’API est récupérée sous forme de chaîne de caractères, puis analysée de la même manière par la classe JsonFlightFiller.

Enfin, les vols récupérés ont été intégrés à l’interface graphique. Pour chaque vol, on utilise la classe World pour retrouver les aéroports de départ et d’arrivée, puis on affiche visuellement ces vols sur la Terre sous forme de points ou de liaisons. Cette partie permet de relier les données temps réel de l’API avec la représentation 3D de la planète.

Cette troisième partie permet ainsi de compléter le projet en reliant l’IHM, les données statiques des aéroports et des données dynamiques issues d’une API externe, tout en manipulant des requêtes HTTP et du parsing JSON en Java.
