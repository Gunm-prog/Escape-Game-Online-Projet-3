

# Escape-Game-Online-Projet-3

Il s’agit d’une commande pour la réalisation d’un jeu numérique basé sur le concept des Escape Games.
Plus précisémment, ce projet consiste en la mise en place de la brique “mécanisme de recherche d’une combinaison à X chiffres”.

État du projet : Terminé

Dans ce jeu, il est possible d'accéder à trois modes de jeu:

1- Mode Challenger:

L'intelligence artificielle de l’ordinateur joue le rôle de défenseur. Elle définit une combinaison de X chiffres aléatoirement. Le joueur a le rôle d’attaquant et doit faire une proposition d’une combinaison de X chiffres. L'intelligence artificielle de l’ordinateur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=). Il y a un nombre limité d’essais.

2- Mode Défenseur:

Le joueur (cette fois dans le rôle de défenseur) définit une combinaison de X chiffres aléatoirement. L'intelligence artificielle de l’ordinateur doit faire une proposition d’une combinaison de X chiffres (c’est le rôle attaquant). Le joueur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=). L’intelligence artificielle fait une autre proposition en se basant sur la réponse fournit par le joueur. Il y a un nombre limité d’essais.

3- Mode duel:

Le joueur et l’intelligence artificielle de l’ordinateur jouent tour à tour. Le premier à trouver la combinaison secrète de l'autre a gagné.


FONCTIONNALITÉS SUPPLÉMENTAIRES :

- MODE DÉVELOPPEUR: le mode développeur peut s'activer via le fichier configProperties en le passant à "True".

- LOGS utilisation du fichier LOG4J2 avec prise en compte de deux niveau d'affichage: Log Info et Log Error pour l'affichage des exceptions.

- PROPERTIES: le fichier de configuration des properties contient des variables concernant: la longueur de la clé secrète (secretKeyLength), le mode développeur (lorsqu'il est activé) permet d'afficher la combinaison secrète que l'adversaire doit trouver (selon le mode de jeu)


COMPILATION

Adresse url du projet:
https://github.com/Gunm-prog/Escape-Game-Online-Projet-3/
Sélectionnez "Clone or Download" afin de cloner ou de télécharger le repository de ce projet depuis Github.

Chargement du projet depuis Intellij:
-Ouvrir Intellij;
-Effectuez "new Project from Version Control" et choisissez "Git".
-Saisissez l'url du repository que vous venez de cloner. Vous pouvez lancer l'application depuis votre IDE

Execution du programme:
Dans le menu principal, cliquez sur Run, puis dans le menu déroulant de Run, cliquez sur Run 'Main.main()'.
Votre code va alors s'exécuter et le résultat apparaît dans la console.


Copyright : Emilie BALSEN
