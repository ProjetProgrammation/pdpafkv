/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Result;

/**
 *
 * @author Thibaut
 */
public class User {
    
    private String id;
    private String lastName;
    private String firstName;
    private String birthday;
    private String motherTongue;
    private int yearStudying;

    
    //Constructeur User avec comme id unique la date plus son nom et son prénom
    public User(String lastName, String firstName, String birthday, String motherTongue, int yearStudying) {
        
        //Initialisation du format de sortie
        String format = "dd/MM/yy-H:mm";
        //Ajout de notre format pour la sortie de la future date
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
        //Récupération de la date courante
        java.util.Date date = new java.util.Date();
        //Affection donnée
        this.id = formater.format(date)+lastName+firstName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.motherTongue = motherTongue;
        this.yearStudying = yearStudying;
    }
    
    //Méthode toString pour
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", birthday=" + birthday + ", motherTongue=" + motherTongue + ", yearStudying=" + yearStudying + '}';
    }
    
    public String getNameToFile(){
        return (this.id+"_"+this.lastName+"_"+this.firstName+"_"+this.birthday);
    }
    
    public String getUserExtract() {
        return "lastName=" + lastName + ", firstName=" + firstName + ", birthday=" + birthday + ", motherTongue=" + motherTongue + ", yearStudying=" + yearStudying;
    }
}
