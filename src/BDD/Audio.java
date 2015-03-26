/*
 * 
 */
package BDD;

/**
 *
 * Use this class to create a new Audio object (or instantiate a Media object).
 *
 * @author akervadec
 */
public class Audio extends Media {

    /**
     * Constructs an empty Audio object with an id and idLanguage equal to zero.
     * 
     */
    public Audio() {
        this.name = new String();
        this.filePath = new String();
        this.format = new String();
        this.idLanguage = 0;
    }

    /**
     * Constructs an Audio object with all attributes knowned.
     *
     * @param id Audio's id.
     * @param name Audio's name.
     * @param filePath Audio's file path.
     * @param format Audio's format.
     * @param idLanguage Audio's id of its Language.
     */
    public Audio(int id, String name, String filePath, String format, int idLanguage) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
        this.format = format;
        this.idLanguage = idLanguage;
    }

    /**
     * Contructs a copy of the Audio in parameter.
     *
     * @param audio Audio's object to copy in the new object.
     */
    public Audio(Audio audio) {
        this.id = audio.getId();
        this.name = new String(audio.getName());
        this.filePath = new String(audio.getFilePath());
        this.format = new String(audio.getFormat());
        this.idLanguage = audio.getIdLanguage();
    }

}
