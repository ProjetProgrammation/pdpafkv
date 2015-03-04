/*
 * Cette classe permet de créer des langues
 */

package BDD;

/**
 *
 * @author akervadec
 */
public class Language {

	private int id;
	private String name;

	/**
	* Constructeur Language.
	*/
	public Language(){
		this.name=new String();
	}

	/**
	* Constructeur Language.
	* 
	* @param name
	*            Le nom du "langage".
	*/
	public Language(String name){
		this.name=name;
	}

	/**
	* Constructeur Language.
	* 
	* @param id
	*            L'id' du "langage".
	* @param name
	*            Le nom du "langage".
	*/
	public Language(int id, String name){
		this.id=id;
		this.name=new String(name);
	}

	/**
	* Constructeur Language.
	* 
	* @param language
	*            Le langage du "langage" (clonage d'un autre langage).
	*/
	public Language(Language language){
		this.name=language.getName();
	}
	
	/**
	* Getter de id
	* @return L'attribut id
	*/
	public int getId(){return this.id;}

	/**
	* Getter de name
	* @return L'attribut name
	*/
	public String getName(){return this.name;}

	/**
	* Setter de id
	* @param id L'id de la vidéo
	*/
	public void setId(int id){this.id=id;}

	/**
	* Setter de name
	* @param name Le nom de la langue
	*/
	public void setName(String name){this.name=name;}

	public String toString(){
		return ("Details about the language :"
			+ "\n\tid : " + this.id
			+ "\n\tname : " + this.name);
	}
}
