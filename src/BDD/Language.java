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

	public Language(){
		this.name=new String();
	}

	public Language(String name){
		this.name=name;
	}

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
}
