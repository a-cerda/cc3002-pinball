package controller;

import logic.bonus.Bonus;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.NullTable;
import logic.table.Table;

import java.util.List;

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
    private Table currentTable;
    private int balls;

    /*
    *Constructor for the Game class
    * the default values are score = 0 and balls = 3
    * the default table is a NullTable, which is not playable, a table has to be set through the
    * setCurrentTable() method.
    */
    private Game(){
        extraBallBonus = ExtraBallBonus.getUniqueInstance();
        jackPotBonus = JackPotBonus.getUniqueInstance();
        dropTargetBonus = DropTargetBonus.getUniqueInstance();
        balls = 3;
        score = 0;
        currentTable = new NullTable();
    }

    /**Method for getting the unique instance of game
     *
     * @return Game uniqueGame the unique instance of Game
     */
    public static Game getUniqueGame(){
        if(uniqueGame == null){
            uniqueGame = new Game();
        }
        return uniqueGame;
    }

    /**
     * Method to trigger an ExtraBallBonus
     */
    public void triggerExtraBallBonus(){
        this.extraBallBonus.trigger(this);
    }

    /**
     * Method to trigger a JackPotBonus
     */
    public void triggerJackPotBonus(){
        this.jackPotBonus.trigger(this);
    }

    /**
     * Method to trigger a DropTargetBonus
     */
    public void triggerDropTargetBonus(){
        this.dropTargetBonus.trigger(this);
    }

    /**
     * Getter method for the current score of the game
     *
     * @return int score -> the current score of the game
     */
    public int getScore() {
        return score;
    }

    /**Method to sum points to the score
     *
     * @param points -> the points to be added to the current score
     */
    public void addToScore(int points){
        this.score += points;
    }

    /**
     * Method to upgrade all the bumpers on the current table, triggered by DropTargetBonus
     * @see DropTargetBonus
     */
    public void upgradeAllBumpers() {
        currentTable.upgradeAllBumpers();
    }


    /**
     * Method to add a ball to the current game, triggered by ExtraBallBonus
     * @see ExtraBallBonus
     */
    public void addBall() {
        this.balls++;
    }

    public int dropBall() {
        this.balls--;
        return balls;
    }

    public boolean gameOver() {
        if(balls <= 0) return true;
        return false;
    }

    public void setCurrentTable(Table newTable) {
        currentTable = newTable;
    }

    public Table getCurrentTable() {
        return currentTable;
    }

    public int getAvailableBalls() {
        return balls;
    }

    public String getTableName() {
        return this.currentTable.getTableName();
    }

    public List<Target> getTargets() {
        return this.currentTable.getTargets();
    }

    public List<Bumper> getBumpers() {
        return this.currentTable.getBumpers();
    }

    public Bonus getJackPotBonus() {
        return this.jackPotBonus;
    }

    public Bonus getExtraBallBonus() {
        return this.extraBallBonus;
    }

    public Bonus getDropTargetBonus() {
        return this.dropTargetBonus;
    }

    public boolean isCurrentTablePlayable() {
        return this.currentTable.isPlayableTable();
    }
}
