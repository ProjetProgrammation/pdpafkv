ATTENTION : la nomenclature n'est pas le même en BDD et en Java !


	BDD : file_path
	Java : filePath

Si utilisation en ligne de commande alors :

Impossible d’ajouter sqlite-jdbc-VERSION.jar au CLASSPATH (en ligne de commande sans IDE).
	
	Solution provisoire au problème de CLASSPATH sur Ubuntu/Linux :
	java -classpath ".:sqlite-jdbc-3.8.7.jar" Main 

	Solution provisoire au problème de CLASSPATH sur Windows :
	java -classpath ".;sqlite-jdbc-3.7.2.jar" Main

Avec le fichier sqlite-jdbc-3.8.7.jar dans le répertoire courant.

*Penser à mettre en place les requêtes pour lister les videos/audios et faire des recherches dans ces listes.
*Faire en sorte que soit retournée une erreur quand un media est ajouté avec une langue n'existant pas.

Pour le rapport
===============
Spécificité du code :


	- Mise en place de transactions
	- Requêtes paramétrées
