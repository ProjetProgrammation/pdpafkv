Si utilisation en ligne de commande alors :

Impossible d’ajouter sqlite-jdbc-VERSION.jar au CLASSPATH (en ligne de commande sans IDE).
	
	Solution provisoire au problème de CLASSPATH sur Ubuntu/Linux :
	java -classpath ".:sqlite-jdbc-3.8.7.jar" Main 

	Solution provisoire au problème de CLASSPATH sur Windows :
	java -classpath ".;sqlite-jdbc-3.7.2.jar" Main

Avec le fichier sqlite-jdbc-3.8.7.jar dans le répertoire courant.

Penser à mettre en place les requêtes pour lister les videos/audios et faire des recherches dans ces listes.
