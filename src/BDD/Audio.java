/*
 * Cette classe permet de créer des langues
 */

package BDD;

/**
 *
 * @author akervadec
 */
public class Audio {

	private int id;
	private String name;
	private String filePath;
	private String format;
	private int idLanguage;


	public Audio(){
		this.name=new String();
		this.filePath=new String();
		this.format=new String();
		this.idLanguage=0;
	}

	public Audio(int id, String name, String file_path, String format, int idLanguage){
		this.id=id;
		this.name=new String(name);
		this.filePath=new String(filePath);
		this.format=new String(format);
		this.idLanguage=idLanguage;
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
	* Getter de filePath
	* @return L'attribut filePath
	*/
	public String getFilePath(){return this.filePath;}

	/**
	* Getter de format
	* @return L'attribut format
	*/
	public String getFormat(){return this.format;}

	/**
	* Getter de idLanguage
	* @return L'attribut idLanguage
	*/
	public int getIdLanguage(){return this.idLanguage;}


	/**
	* Setter de id
	* @param id L'id de l'audio
	*/
	public void setId(int id){this.id=id;}

	/**
	* Setter de name
	* @param name Le nom de l'audio
	*/
	public void setName(String name){this.name=name;}

	/**
	* Setter de filePath
	* @return L'attribut filePath
	*/
	public void setFilePath(String filePath){this.filePath=filePath;}

	/**
	* Setter de format
	* @return L'attribut format
	*/
	public void setFormat(String format){this.format=format;}

	/**
	* Setter de idLanguage
	* @return L'attribut idLanguage
	*/
	public void setIdLanguage(int idLanguage){this.idLanguage=idLanguage;}
}
