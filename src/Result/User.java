/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Result;

/**
 * Create an user.
 *
 * @author Thibaut
 */
public class User {

    private final String id;
    private final String lastName;
    private final String firstName;
    private final String birthday;
    private final String motherTongue;
    private final int yearStudying;

    /**
     * Constructs an User object.
     *
     * @param lastName Only letter.
     * @param firstName Only letter.
     * @param birthday format DD/MM/YYYY.
     * @param motherTongue Only letter.
     * @param yearStudying Number of years study.
     */
    public User(String lastName, String firstName, String birthday, String motherTongue, int yearStudying) {

        String format = "dd/MM/yy-H:mm";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();

        this.id = formater.format(date) + lastName + firstName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.motherTongue = motherTongue;
        this.yearStudying = yearStudying;
    }

    /**
     *
     * toString method for user.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", birthday=" + birthday + ", motherTongue=" + motherTongue + ", yearStudying=" + yearStudying + '}';
    }

    /**
     *
     * User name for file.
     *
     * @return String
     */
    public String getNameToFile() {
        return (this.lastName + "_" + this.firstName);
    }

    /**
     *
     * User last name.
     *
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * User first name.
     *
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * User birthday.
     *
     * @return String
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     *
     * User mother tongue.
     *
     * @return String
     */
    public String getMotherTongue() {
        return motherTongue;
    }

    /**
     *
     * User years study.
     *
     * @return int
     */
    public int getYearStudying() {
        return yearStudying;
    }

}
