package logic.table;

import controller.Game;
import logic.gameelements.Hittable;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import logic.updates.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class PlayableTable implements Table{
    private String name;
    private List<DropTarget> dropTargets;
    private List<SpotTarget> spotTargets;
    private List<KickerBumper> kickerBumpers;
    private List<PopBumper> popBumpers;
    private Game game;
    private static Random rand = new Random();

    /**
     * Creates a new table with the given parameters with no targets.
     *
     * @param name            the name of the table
     * @param numberOfBumpers the number of bumpers in the table
     * @param probabilityOfPopBumper            the probability a {@link logic.gameelements.bumper.PopBumper}
     * @return a new table determined by the parameters
     */


    public PlayableTable(String name, int numberOfBumpers, double probabilityOfPopBumper){
        this.name = name;
        this.popBumpers = new ArrayList<PopBumper>();
        this.kickerBumpers = new ArrayList<KickerBumper>();
        this.spotTargets = new ArrayList<SpotTarget>();
        this.dropTargets = new ArrayList<DropTarget>();
        for (int i = 0; i< numberOfBumpers; ++i){
            if(randInt(1,10)<= probabilityOfPopBumper*10){
                this.popBumpers.add(new PopBumper());
                this.popBumpers.get(popBumpers.size() - 1).addObserver(this);
            }
            else{
                this.kickerBumpers.add(new KickerBumper());
                this.kickerBumpers.get(kickerBumpers.size() - 1).addObserver(this);
            }
        }
    }

    @Override
    public PlayableTable setGame(Game game){
        this.game = game;
        return this;
    }


    public PlayableTable setTargets(int numberOfSpotTargets, int numberOfDropTargets){
        for(int i = 0; i<numberOfDropTargets; i++){
            this.dropTargets.add(new DropTarget());
            this.dropTargets.get(i).addObserver(this);
        }
        for (int i = 0; i< numberOfSpotTargets; i++){
            this.spotTargets.add(new SpotTarget());
            this.spotTargets.get(i).addObserver(this);
        }
        return this;
    }
    /**
     * randInt: Given a minimum and a maximum, returns a random integer between the two.
     * @param min the minimum value of the random number
     * @param max the maximum value of the random number
     * @return Random integer between min and max
     */

    public static int randInt(int min, int max) {

        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * Method used for the setting of the seed of the Random instance on the bumper
     * It is here to mantain the encapsulation when testing
     *
     * @param seed
     */

    public void setSeed(long seed){
        this.rand.setSeed(seed);
    }

    /**
     * Gets the table name.
     *
     * @return the table's name
     */
    @Override
    public String getTableName() {
        return name;
    }

    /**
     * Gets the number of {@link DropTarget} in the table.
     *
     * @return the number of DropTargets in the table
     */
    @Override
    public int getNumberOfDropTargets() {
        return dropTargets.size();
    }

    /**
     * Gets the number of {@link DropTarget} that are currently dropped or inactive.
     *
     * @return the number of DropTargets that are currently inactive
     */
    @Override
    public int getCurrentlyDroppedDropTargets() {
        int dropped = 0;
        for (DropTarget target: dropTargets) {
            if(!target.isActive()) dropped++;

        }
        return dropped;
    }

    /**
     * Gets the {@link List} of {@link Bumper}s in the table.
     *
     * @return the bumpers in the table
     */
    @Override
    public List<Bumper> getBumpers() {
        List<Bumper> newList = new ArrayList<Bumper>();
        newList.addAll(kickerBumpers);
        newList.addAll(popBumpers);

        return newList;
    }

    /**
     * Gets the {@link List} of {@link Target}s in the table.
     *
     * @return the targets in the table
     */
    @Override
    public List<Target> getTargets() {
        List<Target> newList = new ArrayList<Target>();
        newList.addAll(dropTargets);
        newList.addAll(spotTargets);

        return newList;
    }

    /**
     * Resets all {@link DropTarget} in the table. Make them active.
     */
    @Override
    public void resetDropTargets() {
        for(DropTarget d : dropTargets){
            d.reset();
        }
    }

    /**
     * Upgrade all {@link Bumper}s in the table.
     */
    @Override
    public void upgradeAllBumpers() {
        for (PopBumper b :
                popBumpers) {
            b.upgrade();
        }
        for (KickerBumper k:
             kickerBumpers) {
            k.upgrade();
        }
    }

    /**
     * Gets whether the table is playable or not.
     *
     * @return true if the table is playable, false otherwise
     */
    @Override
    public boolean isPlayableTable() {
        return true;
    }



    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Update)
        {
            ((Update)arg).accept(this);
        }
    }

/*
    *//**
     * A method for visiting a bumper and invoking the corresponding ExtraBallBonus
     *
     * @param bumper
     *//*
    @Override
    public void visitBumper(Bumper bumper) {
        game.addToScore(bumper.getScore());

        int chance = randInt(1,100);
        //This gives us a 10% chance of getting the ExtraBallBonus
        if(chance <= 10)
        {

            //Call the ExtraBallBonus
            game.triggerExtraBallBonus();
        }

    }

    *//**
     * A method for visiting a spotTarget and invoking a JackPotBonus
     *
     * @param spotTarget
     *//*
    @Override
    public void visitSpotTarget(SpotTarget spotTarget)
    {
        game.addToScore(spotTarget.getScore());
        game.triggerJackPotBonus();
    }

    *//**
     * A method for visiting a DropTarget and invoking a DropTargetBonus
     *
     * @param dropTarget
     *//*
    @Override
    public void visitDropTarget(DropTarget dropTarget) {
        game.addToScore(dropTarget.getScore());
        if(randInt(1,100) < 30){
            game.triggerExtraBallBonus();
        }

        if(this.getCurrentlyDroppedDropTargets() == dropTargets.size()){
            game.triggerDropTargetBonus();
        }
    }*/

    @Override
    public void visitHitUpdate(HitUpdate hitUpdate) {
       game.addToScore(hitUpdate.getPoints());
        if(this.getCurrentlyDroppedDropTargets() == dropTargets.size()){
           visitDropTargetUpdate(new DropTargetUpdate());
        }
    }

    @Override
    public void visitSpotTargetUpdate(SpotTargetUpdate spotTargetUpdate) {
        game.triggerJackPotBonus();
    }

    @Override
    public void visitUpgradeBumperUpdate(UpgradeBumperUpdate upgradeBumperUpdate) {
        int randint = randInt(1,100);
        if(randint <= 10){
            game.triggerExtraBallBonus();
        }
    }

    @Override
    public void visitDropTargetUpdate(DropTargetUpdate dropTargetUpdate) {
        game.triggerDropTargetBonus();
    }
}
