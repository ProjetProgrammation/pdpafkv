/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BDD.Language;
import Result.User;
import Result.Answer;
import Result.Extract;
import java.util.ArrayList;

/**
 *
 * @author Thibaut
 */
public class controlExtract {
    private MediaSelected medSelected;
    private Extract extract;
    
    public controlExtract(MediaSelected medSel) {
        this.medSelected=medSel;
    }
    
    public void Extract(){
        Extract.Extract(medSelected);
    }
    
    
    
}
