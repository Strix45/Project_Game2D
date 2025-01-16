# Snake game
Un jeu créé par Dino Bijelic et Théo Delalay

## Fonctionnement
Cliquez sur le bouton START puis dirigez le snake avec les flèches directionnelles situées sur votre clavier à droite de enter.

Vous pouvez recommencer une partie en appuyant sur RESTART.

## Ressources utilisées
Pour tout ce qui est fonctions liées à FunGraphics : [FunGraphics Scaladoc](https://isc-hei.github.io/FunGraphics/hevs/graphics/FunGraphics.html)

Pour le lien du clavier et de la souris au jeu : [Documentation sur ISC Learn](https://isc.hevs.ch/learn/mod/page/view.php?id=2565)

## Détails du fonctionnement du jeu
### Le dessin général
Plusieurs fonctions telles que drawBackground(), drawGrid() et drawHeader() dessinent le jeu de base (tout dans le background sauf le header).

Ces fonctions ne sont appelées qu'une seule fois au départ puis sont ensuite remplacées par des fonctions plus précises pour "effacer" la queue du snake lorsqu'il bouge.

### Le snake en tant que classe
Par pure envie d'organisation, le snake a été créé en tant que classe puis objet dans la classe Grid.

La position de sa tête est sauvegardée dans le tableau position, sa direction dans 4 variables booléennes nommées up, down, right et left.

Le score, ainsi que sa taille sont aussi dans la classe Snake

### Le jeu en lui-même
Pour dessiner le snake, nous avons opté pour un tableau de Int en 2 dimensions qui représente toutes les cellules jouables (dans notre cas 15x15). Si la valeur d'une cellule > 0 alors le programme dessine une case bleue, signifiant la présence du snake.

La fonction principale est move() qui check quelle valeur booléenne est vraie (up, down, left, right) et ajuste la position du snake selon celle-ci. La manière de changer la position est ajoutée par un keyListener qui change quelle variable booléenne est vraie. Nous avons codé le mouvement ainsi pour ne pas pouvoir "accélérer" en appuyant plusieurs fois sur les flèches.

Nous avons aussi ajouté une contrainte qu'on ne peut pas revenir en arrière. Si par exemple le snake bouge à droite, on ne peut pas tourner directement à gauche.

La fonction drawGame() parcourt gridElement et dessine une cellule bleue en utilisant la fonction drawCell(x,y,Color), sinon elle dessine les cellules du damier en utilisant drawEmpty(x,y) (qui donc dessine les cellules vertes en alternant les couleurs)

#### La nourriture
Tout ce qui a un lien avec la nourriture est dans sa propre classe Food

Pour contrôler la nourriture, un tableau en 2 dimensions nommé foodGrid a été créé et il contient des Int.

Pour augmenter la taille du snake et le score du joueur nous avons deux possibilités, soit une fraise rouge (+1 size et +1 score) soit une fraise dorée (+2 size et +5 score). La fraise rouge représente une valeur de 1 dans foodGrid et la fraise dorée une valeur de 2.

La fonction eat() est utilisée pour vérifier si la tête du snake (gérée par le tableau snake.position) bouge sur une case où il y a une fraise. Si tel est le cas, alors snake.size += 1 et snake.score += 1 ou +2 et + 5 respectivement si c'est une fraise dorée.

Pour créer la nourriture nous utilisons les fonctions createFood() et createFood2() (qui auraient pu être optimisées autrement mais ma foi). Ces fonctions utilisent deux variables aléatoires multipliées par 15 et transformées en Int pour représenter les coordonnées de foodGrid. Les fonctions vérifient si la tête du snake est bien sur une fraise (pour en créer une nouvelle seulement lorsqu'il en mange une) et si la position choisie aléatoirement est vide (sans snake ni fraise) puis crée la fraise (en changeant la valeur dans foodGrid à 1 et en dessinant l'image de la fraise à sa position)

Nous utilisons createFood() et createFood2() pour donner un sentiment de "bonus". Dans Main une variable aléatoire multipliée par 5 et une condition if permettent d'influencer les chances qu'une fraise rouge ou dorée apparaisse. Dans le cas de notre code, la fraise dorée (createFood2()) a environ 20% de chances d'apparaître à chaque fois.

## Les menus
### Deathscreen
Ce menu apparaît après 2 situations : si tailDeath est true, càd si lorsque le snake move() il tente de passer sur une case où il y a déjà la snake. En effet, toute la boucle du jeu tourne seulement si tailDeath est false

Comme deuxième cas nous avons un try catch avec un ArrayIndexOutOfBoundsException qui arrive lorsque le snake tente de bouger en dehors du damier (car sa position est utilisée dans gridElement qui a une taille de 15x15)

### Start
Ce menu utilise le mouseListener et une zone d'activation pour lancer le programme



