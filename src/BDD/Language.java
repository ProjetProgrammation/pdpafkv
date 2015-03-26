/*
 * 
 */
package BDD;

/**
 *
 * Use this class to create a new Language.
 *
 * @author akervadec
 */
public class Language {

    private int id;
    private String name;

    /**
     * Constructs an empty Language object with a id = 0.
     */
    public Language() {
        this.name = new String();
    }

    /**
     * Constructs a Language object wih its name in parameter.
     *
     * @param name Language's name
     */
    public Language(String name) {
        this.name = name;
    }

    /**
     * Constructs a Language object with its name and id in parameter.
     *
     * @param id Language's id.
     * @param name Language's name.
     */
    public Language(int id, String name) {
        this.id = id;
        this.name = new String(name);
    }

    /**
     * Constructs a copy of the Language object in parameter.
     *
     * @param language The Language to copy into the new object.
     */
    public Language(Language language) {
        this.name = language.getName();
    }

    /**
     * Returns the id.
     *
     * @return int
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the name.
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the id.
     *
     * @param id Language's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set the name.
     *
     * @param name Language's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ("Details about the language :"
                + "\n\tid : " + this.id
                + "\n\tname : " + this.name);
    }

    /**
     * Returns a String with all the data necessary to the extraction.
     *
     * @return String
     */
    public String getLanguageExtract() {
        return "\"Language\": \"" + this.name + "\",";
    }
}
