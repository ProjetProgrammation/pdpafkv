/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDD;

/**
 * Use this class to create object Media, which has to be instantiated with
 * Video or Audio constructor.
 *
 * @author akervadec
 */
public abstract class Media {

    protected int id;
    protected String name;
    protected String filePath;
    protected String format;
    protected int idLanguage;

    /**
     * Returns the id
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the filePath
     *
     * @return String
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Returns the format
     *
     * @return String
     */
    public String getFormat() {
        return format;
    }

    /**
     * Returns the idLanguage
     *
     * @return int
     */
    public int getIdLanguage() {
        return idLanguage;
    }

    /**
     * Set the id
     *
     * @param id L'id de l'audio
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set the name
     *
     * @param name Le nom de l'audio
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the filePath
     *
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Set the format
     *
     * @param format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Set the idLanguage
     *
     * @param idLanguage
     */
    public void setIdLanguage(int idLanguage) {
        this.idLanguage = idLanguage;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String
     */
    @Override
    public String toString() {
        return ("Details about the video :"
                + "\n\tid : " + this.id
                + "\n\tname : " + this.name
                + "\n\tformat : " + this.format
                + "\n\tfile path : " + this.filePath
                + "\n\tid language : " + this.idLanguage);
    }

}
