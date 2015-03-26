/*
 * 
 */
package BDD;

/**
 *
 * Use this class to create a new Video object (or instantiate a Media object).
 *
 * @author akervadec
 */
public class Video extends Media {

    /**
     * Constructs an empty Video object with an id and idLanguage equal to zero.
     *
     */
    public Video() {
        this.name = new String();
        this.filePath = new String();
        this.format = new String();
        this.idLanguage = 0;
    }

    /**
     * Constructs an Video object with all attributes knowned.
     *
     * @param id Video's id.
     * @param name Video's name.
     * @param filePath Video's file path.
     * @param format Video's format.
     * @param idLanguage Video's id of its Language.
     */
    public Video(int id, String name, String filePath, String format, int idLanguage) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
        this.format = format;
        this.idLanguage = idLanguage;
    }

    /**
     * Contructs a copy of the Video in parameter.
     *
     * @param video Video's object to copy in the new object.
     */
    public Video(Video video) {
        this.id = video.getId();
        this.name = new String(video.getName());
        this.filePath = new String(video.getFilePath());
        this.format = new String(video.getFormat());
        this.idLanguage = video.getIdLanguage();
    }

}
