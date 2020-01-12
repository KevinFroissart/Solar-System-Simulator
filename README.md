# Mode2020-H1

## Equipe ##

- BOURDIN Maxence
- FROISSART Kévin
- BISSON Clément
- DEROUCK Lucas

## Responsabilités ##

##### DEROUCK Lucas 
* IHM
* Implémentation des informations détaillées sur la fenêtre
* Ajout des boutons, du CSS, des Labels

##### BISSON Clément
* Faire la vue vaisseau (pas implémenté)
	
##### BOURDIN Maxence
* Timer (non fonctionnel)
* Édition du .gitIgnore
* Interface méthodes d'intégration
* Édition des librairies
* Préparation du rendu

##### FROISSART Kévin
* Implémentation du cercle
* Implémentation de LeapFrog
* Implémentation du Cryo-sommeil
* DT adaptatif
* Ajout du chronomètre
* Préparation du rendu

## Fonctionnalités ##
	
	<Jalon 1>
	- Exécution d'un système depuis une console avec une ligne de commande.
	- Exécution d'un système depuis l'application via le bouton Ouvrir.
	- Possibilité de recommencer la simulation exécutée vie le bouton Reset.
	- Possibilité de cacher les éléments du système via les boutons Vaisseau, Planètes & Soleil.
	- Possibilité d'afficher la trajectoire des objets via le bouton Trajectoire.
	- Ouverture du tableau d'informations détaillées via le bouton Infos ou la touche <i>.
	- Nouveaux sprites à chaque simulation pour les objets simulés.
	- Modification de la vitesse de simulation via le slider Vitesse de la simulation.
	- Contrôle du vaisseau via les flèches du clavier.

	<Jalon 2>
	- Les Ellipses ne sont pas du tout suportées et serons considérées comme erreurs
	- Possibilité d'utiliser LeapFrog et Euler Explicite
	- Possibilité de simuler un cercle
	- Cryo-Sommeil via un bouton disponible dans la fenêtre principale
	- Possibilité de changer de méthode d'intégration sans quitter le programme
	- Possibilité de cacher le cercle en plus des anciennes fonctionnalités via le bouton Cercle
	- Tableau d'informations détaillées sur la fenêtre principale
	- Possiblité de consulter le temps écoulé dans la simulation via la fenêtre détaillée
	- DT adaptatif
	- Trajectoire du vaisseau ajoutée à celle des planètes via le bouton Trajectoire

## Exécution du code ##

* Version de java : [Java SE Development Kit 13.0.1](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html)
* Version de javaFx : [JavaFX 13](https://openjfx.io/)
* Ligne de commande pour exécuter le .jar : 
mode2020-h1> java -jar livrable2.jar exemples/fichier-a-executer.astro(txt)
