/*
 * Cette classe permet de créer des langues
 */

//package BDD;

/**
 *
 * @author akervadec
 */
public class Language {

	public Language(){
		this.name=new String();
	}

	public Language(String name){
		this.name=name;
	}

	private String name;

	/**
	* Getter de name.
	* @return L'attribut name
	*/
	public String getName(){return this.name}

	/**
	* Setter de name.
	* @param name Le nom de la langue.
	*/
	public void setName(String name){this.name=name;}
}
