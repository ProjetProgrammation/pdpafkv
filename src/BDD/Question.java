/*
 * Cette classe permet de créer des questions
 */

package BDD;

/**
 *
 * @author akervadec
 */
public class Question {

	private int id;
	private String content;
	private int idVideo;
	private int idAudio;

	public Question(){
		this.content=new String();
	}

	public Question(String content){
		this.content=content;
	}
	
	/**
	* Getter de id
	* @return L'attribut id
	*/
	public int getId(){return this.id;}

	/**
	* Getter de content.
	* @return L'attribut content (question en elle-même)
	*/
	public String getContent(){return this.content;}

	/**
	* Setter de id
	* @param id L'id de la vidéo
	*/
	public void setId(int id){this.id=id;}

	/**
	* Setter de content.
	* @param content Le contenu de la question.
	*/
	public void setContent(String content){this.content=content;}
}
