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
	private int idLanguage;

	/**
	* Constructeur Question.
	*/
	public Question(){
		this.content=new String();
	}

	/**
	* Constructeur Question.
	* 
	* @param id
	*            L'id de la "question".
	* @param content
	*            Le contenu de la "question".
	* @param iVideo
	*            L'id de la vidéo de la "question".
	* @param idAudio
	*            L'id de l'audio de la "question".
	* @param idLanguage
	*            L'id du langage de la "question".
	*/
	public Question(int id, String content, int idVideo, int idAudio, int idLanguage){
		this.id=id;
		this.content=new String(content);
		this.idVideo=idVideo;
		this.idAudio=idAudio;
		this.idLanguage=idLanguage;
	}

	/**
	* Constructeur Question.
	* 
	* @param question
	*            La question de la "question" (clonage d'une question).
	*/
	public Question(Question question){
		this.id = question.getId();
		this.content = new String (question.getContent());
		this.idVideo = question.getIdVideo();
		this.idAudio = question.getIdAudio();
		this.idLanguage = question.getIdLanguage();
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
	* Getter de idVideo
	* @return L'attribut idVideo
	*/
	public int getIdVideo(){return this.idVideo;}

	/**
	* Getter de idAudio
	* @return L'attribut idAudio
	*/
	public int getIdAudio(){return this.idAudio;}

	/**
	* Getter de idLanguage
	* @return L'attribut idLanguage
	*/
	public int getIdLanguage(){return this.idLanguage;}

	/**
	* Setter de id
	* @param id L'id de la question
	*/
	public void setId(int id){this.id=id;}

	/**
	* Setter de content.
	* @param content Le contenu de la question.
	*/
	public void setContent(String content){this.content=content;}

	/**
	* Setter de idVideo
	* @param id L'idVideo de la question
	*/
	public void setIdVideo(int idVideo){this.idVideo=idVideo;}

	/**
	* Setter de idAudio
	* @param id L'idAudio de la question
	*/
	public void setIdAudio(int idAudio){this.idAudio=idAudio;}

	/**
	* Setter de idLanguage
	* @param id L'idLanguage de la question
	*/
	public void setIdLanguage(int idLanguage){this.idLanguage=idLanguage;}

	public String toString(){
		return("Details about the question :"
			+"\n\tid : " + this.id
			+"\n\tquestion : " + this.content
			+"\n\tid video : " + this.idVideo
			+"\n\tid audio : " + this.idAudio);
	}
}
