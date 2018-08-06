package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;

import java.util.List;
import java.util.Observable;

/**
 * Class for instantiating a non playable table, implementing the null object pattern.
 * Every one of it's methods modifies nothing and/or does nothing.
 */
public class NullTable implements Table{
    private String name;


    /**
     * Constructor for the NullTable class, returns a new NullTable with the name set to "null".
     */
    public NullTable(){
        name = "null";
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
        return 0;
    }

    /**
     * Gets the number of {@link DropTarget} that are currently dropped or inactive.
     *
     * @return the number of DropTargets that are currently inactive
     */
    @Override
    public int getCurrentlyDroppedDropTargets() {
        return 0;
    }

    /**
     * Gets the {@link List} of {@link Bumper}s in the table.
     *
     * @return the bumpers in the table
     */
    @Override
    public List<Bumper> getBumpers() {
        return null;
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
     * does nothing
     */
    @Override
    public void resetDropTargets() {

    }

    /**
     * Upgrade all {@link Bumper}s in the table.
     * does nothing.
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
        return false;
    }


    /**
     * Mehtod to update based on a notification, does nothing.
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg){

    }

    /**
     * A method for visiting a bumper and invoking the corresponding ExtraBallBonus
     * does nothing
     * @param bumper
     */
    @Override
    public void visitBumper(Bumper bumper) {

    }

    /**
     * A method for visiting a spotTarget and invoking a JackPotBonus
     * does nothing
     * @param spotTarget
     */
    @Override
    public void visitSpotTarget(SpotTarget spotTarget) {

    }

    /**
     * A method for visiting a DropTarget and invoking a DropTargetBonus
     * does nothing
     * @param dropTarget
     */
    @Override
    public void visitDropTarget(DropTarget dropTarget) {

    }
}
