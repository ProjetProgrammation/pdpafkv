/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BDD.*;
import Result.*;
import java.util.ArrayList;

/**
 * Retrieve answers of the user for questions.
 *
 * @author thibaut
 */
public class MediaSelected {

    private final User userSel;
    private final Language langSel;
    private final ArrayList<Answer> answersList;

    /**
     * Constructs a MediaSelected object.
     *
     * @param userSel The current user.
     * @param langSel The language choose by the user.
     */
    public MediaSelected(User userSel, Language langSel) {
        this.userSel = userSel;
        this.langSel = langSel;
        this.answersList = new ArrayList<>();
    }

    /**
     * Add answer in the list of answers.
     *
     * @param answerToAdd Answer to add in the list.
     */
    public void addAnswer(Answer answerToAdd) {
        this.answersList.add(answerToAdd);
    }

    /**
     * Get the user.
     *
     * @return User
     */
    public User getUserSel() {
        return userSel;
    }

    /**
     * Get the language.
     *
     * @return Language
     */
    public Language getLangSel() {
        return langSel;
    }

    /**
     * Get the arraylist of answers.
     *
     * @return ArrayList<Answer>
     */
    public ArrayList<Answer> getAnswersList() {
        return answersList;
    }

}
