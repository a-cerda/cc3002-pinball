package controller;

import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game {

    //Private paramenter uniqueGame lets us instantiate a single instance of the game class
    //at any given time (Singleton Pattern)
    private static Game uniqueGame;
    private ExtraBallBonus extraBallBonus;
    private JackPotBonus jackPotBonus;
    private DropTargetBonus dropTargetBonus;
    private int score; //The current score of the player

    /*
    Constructor for the Game class


     */
    private Game(){
        extraBallBonus = ExtraBallBonus.getUniqueInstance();
        jackPotBonus = JackPotBonus.getUniqueInstance();
        dropTargetBonus = DropTargetBonus.getUniqueInstance();
    }

    public static Game getUniqueGame(){
        if(uniqueGame == null){
            uniqueGame = new Game();
        }
        return uniqueGame;
    }


}
