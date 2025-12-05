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
