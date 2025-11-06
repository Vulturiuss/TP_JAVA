Mohammad Belhouchat
Saadallah Ouaziz
On a commencé avec la classe Aeroport. À partir du diagramme UML, on a ajouté l’ensemble des attributs privés indiqués par les carrés (on le déduit aussi car ils sont locaux, etc.).
On a ensuite surchargé la méthode toString() : on la surcharge car elle existe déjà pour toutes les classes, mais on veut un fonctionnement différent pour pouvoir tester la classe.
On la teste, et ça fonctionne.

Pour la classe World, on avait déjà la base du constructeur dans l’énoncé. On a vérifié, pour chaque ligne du fichier CSV représentant la liste des aéroports, si l’aéroport est un grand aéroport.
À partir de cela, on a complété le code de sorte à ajouter l’ensemble des grands aéroports dans une liste.

Ensuite, on a ajouté dans la classe Aeroport, à l’aide de l’énoncé du TP, une méthode calculDistance publique (car on va l’utiliser dans la classe World) dans une nouvelle méthode findNearestAirport, elle aussi publique, qui — comme son nom l’indique — va nous permettre de trouver l’aéroport le plus proche de celui qu’on a choisi.
