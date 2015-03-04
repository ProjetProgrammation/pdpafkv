<H1>Base de données</H1>


<H2>A faire</H2>

<ul>
	<li>JavaDoc</li>
	<li><strike>Méthodes pour faire des recherches de média/langue/question</strike></li>
	<li>Gestion d'erreur : ajout de média dans une langue inexistante</li>
	<li>Gestion d'erreur : ajout de média sans langue</li>
	<li>Gestion d'erreur : rendre impossible la création de doublons</li>
</ul>

<H2>Informations</H2>

<p>
<strong>ATTENTION</strong> : la nomenclature n'est pas le même en BDD et en Java !<br/>
</p>
	<pre><code>BDD : file_path
	Java : filePath</code></pre>
<p>
Si utilisation en ligne de commande alors impossible d’ajouter sqlite-jdbc-VERSION.jar au CLASSPATH (en ligne de commande sans IDE).<br/>
</p>
<ul>
	<li>Solution provisoire au problème de CLASSPATH sur Ubuntu/Linux :<br/>
	<code>java -classpath ".:sqlite-jdbc-3.8.7.jar" Main</code></li> 

	<li>Solution provisoire au problème de CLASSPATH sur Windows :<br/>
	<code>java -classpath ".;sqlite-jdbc-3.7.2.jar" Main</code></li>
</ul>
<p>
Avec le fichier sqlite-jdbc-3.8.7.jar dans le répertoire courant.
</p>

<H2>Notes pour le rapport</H2>

<ul>
	<li>Mise en place de transactions</li>
	<li>Requêtes paramétrées</li>
</ul>
