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

    private String thumbnailPicPath;
    private String thumbnailGifPath;

    /**
     * Returns the video's thumbnail picture path.
     * @return String
     */
    public String getThumbnailPicPath() {
        return thumbnailPicPath;
    }

    /**
     * Returns the video's thumbnail GIF path.
     * @return String
     */
    public String getThumbnailGifPath() {
        return thumbnailGifPath;
    }
    
    /**
     * Set the video's thumbnail picture path.
     * @param thumbnailPicPath 
     */
    public void setThumbnailPicPath(String thumbnailPicPath) {
        this.thumbnailPicPath = thumbnailPicPath;
    }

    /**
     * Set the video's thumbnail GIF path.
     * @param thumbnailGifPath 
     */
    public void setThumbnailGifPath(String thumbnailGifPath) {
        this.thumbnailGifPath = thumbnailGifPath;
    }
    

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
     * Constructs an Video object with all attributes knowned.
     *
     * @param id Video's id.
     * @param name Video's name.
     * @param filePath Video's file path.
     * @param format Video's format.
     * @param idLanguage Video's id of its Language.
     * @param thumbnailPicPath Video's thumbnail picture path.
     * @param thumbnailGifPath Video's thumbnail GIF path.
     */
    public Video(int id, String name, String filePath, String format, int idLanguage, String thumbnailPicPath, String thumbnailGifPath) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
        this.format = format;
        this.idLanguage = idLanguage;
        this.thumbnailPicPath = thumbnailPicPath;
        this.thumbnailGifPath = thumbnailGifPath;
    }

    /**
     * Contructs a copy of the Video in parameter.
     *
     * @param video Video's object to copy in the new object.
     */
    public Video(Video video) {
        this.id = video.getId();
        this.name = video.getName();
        this.filePath = video.getFilePath();
        this.format = video.getFormat();
        this.idLanguage = video.getIdLanguage();
        this.thumbnailPicPath = video.getThumbnailPicPath();
        this.thumbnailGifPath = video.getThumbnailGifPath();
    }

}
