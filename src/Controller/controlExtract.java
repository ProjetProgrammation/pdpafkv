/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Result.Answers;
import Result.Extract;

/**
 *
 * @author Thibaut
 */
public class controlExtract {
    private Answers answersToExtract;
    private Extract extract;
    
    public controlExtract(Answers answers) {
        this.answersToExtract = answers;
    }
    
    public void Extract(){
        this.extract=new Extract("..\\pdpafkv\\src\\Result\\"+this.answersToExtract.getUser().getNameToFile()+".txt");
        
    }
    
    
    
}
