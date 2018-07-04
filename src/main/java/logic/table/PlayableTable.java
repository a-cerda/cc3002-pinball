package logic.table;

import controller.Game;
import logic.gameelements.Hittable;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;

import java.util.List;
import java.util.Observable;

public class PlayableTable implements Table{
    private String name;
    private List<DropTarget> dropTargets;
    private List<SpotTarget> spotTargets;
    private List<KickerBumper> kickerBumpers;
    private List<PopBumper> popBumpers;
    private Game game;


    public PlayableTable(){}


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
        List<Bumper> newList = ;

        return newList;
    }

    /**
     * Gets the {@link List} of {@link Target}s in the table.
     *
     * @return the targets in the table
     */
    @Override
    public List<Target> getTargets() {
        return null;
    }

    /**
     * Resets all {@link DropTarget} in the table. Make them active.
     */
    @Override
    public void resetDropTargets() {

    }

    /**
     * Upgrade all {@link Bumper}s in the table.
     */
    @Override
    public void upgradeAllBumpers() {

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
        if(arg instanceof Hittable)
        {
            ((Hittable)arg).accept(this);
        }
    }


    /**
     * A method for visiting a bumper and invoking the corresponding ExtraBallBonus
     *
     * @param bumper
     */
    @Override
    public void visitBumper(Bumper bumper) {
        game.triggerExtraBallBonus();
    }

    /**
     * A method for visiting a spotTarget and invoking a JackPotBonus
     *
     * @param spotTarget
     */
    @Override
    public void visitSpotTarget(SpotTarget spotTarget) {
        game.triggerJackPotBonus();
    }

    /**
     * A method for visiting a DropTarget and invoking a DropTargetBonus
     *
     * @param dropTarget
     */
    @Override
    public void visitDropTarget(DropTarget dropTarget) {
        game.triggerDropTargetBonus();
    }
}
