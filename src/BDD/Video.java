/*
 * Cette classe permet de créer des langues
 */

package BDD;

/**
 *
 * @author akervadec
 */
public class Video {

	private int id;
	private String name;
	private String filePath;
	private String format;
	private int idLanguage;

	/**
	* Constructeur Audio.
	*/
	public Video(){
		this.name=new String();
		this.filePath=new String();
		this.format=new String();
		this.idLanguage=0;
	}

	/**
	* Constructeur Video.
	* 
	* @param id
	*            L'id de la "video".
	* @param name
	*            Le nom de la "video".
	* @param filePath
	*            Le chemin d'accès à la "video".
	* @param format
	*            Le format de la "video".
	* @param idLanguage
	*            L'id du langage de la "video".
	*/
	public Video(int id, String name, String filePath, String format, int idLanguage){
		this.id=id;
		this.name=new String(name);
		this.filePath=new String(filePath);
		this.format=new String(format);
		this.idLanguage=idLanguage;
	}

	/**
	* Constructeur Audio.
	* 
	* @param audio
	*            L'audio de l'"audio" (clonage d'un audio).
	*/
	public Video(Video video){
		this.id=video.getId();
		this.name=new String(video.getName());
		this.filePath=new String(video.getFilePath());
		this.format=new String(video.getFormat());
		this.idLanguage=video.getIdLanguage();
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
	* @param id L'id de la vidéo
	*/
	public void setId(int id){this.id=id;}

	/**
	* Setter de name
	* @param name Le nom de la vidéo
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

	public String toString(){
		return("Details about the video :"
			+ "\n\tid : " + this.id
			+ "\n\tname : " + this.name
			+ "\n\tformat : " + this.format
			+ "\n\tfile path : " + this.filePath
			+ "\n\tid language : " + this.idLanguage);
	}
}
