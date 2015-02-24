/*
 * Cette classe permet de créer des langues
 */

//package BDD;

/**
 *
 * @author akervadec
 */
public class Video {

	private String name;
	private String file_path;
	private String format;
	private Language language;

	public Video(){
		this.name=new String();
		this.filePath=new String();
		this.format=new String();
		this.language=new Language();
	}

	public Video(String name, String file_path, String format, Language language){
		this.name=new String(name);
		this.filePath=new String(filePath);
		this.format=new String(format);
		this.language=new Language(language);
	}

	/**
	* Getter de name.
	* @return L'attribut name
	*/
	public String getName(){return this.name}

	/**
	* Getter de filePath.
	* @return L'attribut filePath
	*/
	public String getFilePath(){return this.filePath}

	/**
	* Getter de format.
	* @return L'attribut format
	*/
	public String getFormat(){return this.format}

	/**
	* Getter de language.
	* @return L'attribut language
	*/
	public Language getLanguage(){return this.language.getLanguage()}


	/**
	* Setter de name.
	* @param name Le nom de la langue.
	*/
	public void setName(String name){this.name=name;}

	/**
	* Setter de filePath.
	* @return L'attribut filePath
	*/
	public String getFilePath(String filePath){this.filePath=filePath}

	/**
	* Setter de format.
	* @return L'attribut format
	*/
	public String getFormat(String format){this.format=format}

	/**
	* Setter de language.
	* @return L'attribut language
	*/
	public Language getLanguage(Language language){this.language.setLanguage(language)}
}
