/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Result.Extract;

/**
 * Extract results of user test.
 *
 * @author Thibaut
 */
public class controlExtract {

    private final MediaSelected medSelected;

    /**
     * Constructs a MediaSelected object.
     *
     * @param medSel Media selected.
     */
    public controlExtract(MediaSelected medSel) {
        this.medSelected = medSel;
    }

    /**
     * Extraction of media selected.
     *
     */
    public void Extract() {
        Extract.Extract(medSelected);
    }

}
